package com.prostate.doctor.controller;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.feignService.ThirdServer;
import com.prostate.doctor.param.DoctorRegisteParams;
import com.prostate.doctor.service.DoctorService;
import com.prostate.doctor.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 10:23 2018/4/19
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("doctor")
public class DoctorController extends BaseController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private JsonUtils<Doctor> jsonUtil;


    @Autowired
    private ThirdServer thirdServer;


    /**
     * 手机号 短信验证码 注册 接口
     * @param doctorRegisteParams
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Map registerDoctor(@Valid DoctorRegisteParams doctorRegisteParams) {

        String doctorPhone = doctorRegisteParams.getDoctorPhone();
        String smsCode = doctorRegisteParams.getSmsCode();
        String doctorPassword = doctorRegisteParams.getDoctorPassword();


        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor != null) {
            return registerFiledResponse("手机号码已注册过");
        }
        //短信验证码校验
        String ck = redisSerive.getSmsCode(smsCode);
        if (StringUtils.isEmpty(ck)) {
            return failedRequest("验证码已过期!");
        } else if (!doctorPhone.equals(ck)) {
            return failedRequest("手机号码不一致");
        }
        //手机号重复注册数据校验
        doctor = new Doctor();
        doctor.setDoctorPhone(doctorPhone);
        //生成盐
        String salt = RandomStringUtils.randomAlphanumeric(32).toLowerCase();
        //设置盐
        doctor.setSalt(salt);
        //md5密码加密
        doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()));

        //这里做一次数据检查

        int result = doctorService.insertSelective(doctor);
        if (result > 0) {
            return registerSuccseeResponse("注册成功");
        }
        return registerFiledResponse("注册失败,该手机号已被注册");
    }


    /**
     * 手机号 密码 登陆 接口
     * @param doctorPhone
     * @param doctorPassword
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map loginDoctor(String doctorPhone, String doctorPassword) {
        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor == null) {
            return loginFailedResponse("用户名或密码不正确");
        }
        String salt = doctor.getSalt();
        if (doctor.getDoctorPassword().equals(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()))) {
            String token = doctor.getId();
            redisSerive.insert(token, jsonUtil.objectToJsonStr(doctor));
            log.info("======登陆成功====");
            return loginSuccessResponse(token);
        } else {
            log.info("======登陆失败====");
            return loginFailedResponse("用户名或密码不正确");
        }
    }


    /**
     * 手机号 短信验证码 登陆 接口
     *
     * @param doctorPhone
     * @param smsCode
     */
    @RequestMapping(value = "smsLogin", method = RequestMethod.POST)
    public Map smsLogin(String doctorPhone, String smsCode) {

        //短信验证码校验
        String cachePhone = redisSerive.getSmsCode(smsCode);
        if (StringUtils.isEmpty(cachePhone)) {
            return failedRequest("验证码已过期!");
        } else if (!doctorPhone.equals(cachePhone)) {
            return failedRequest("手机号码错误");
        }
        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor == null) {
            return loginFailedResponse("手机号或验证码错误");
        }
        String token = doctor.getId();
        redisSerive.insert(token, jsonUtil.objectToJsonStr(doctor));

        return loginSuccessResponse(token);
    }

    /**
     * 重设 登陆密码
     * @param doctorPhone
     * @param smsCode
     * @param doctorPassword
     * @return
     */
    @RequestMapping(value = "passwordReset", method = RequestMethod.POST)
    public Map passwordReset(String doctorPhone, String smsCode, String doctorPassword) {
        //短信验证码校验
        String cachePhone = redisSerive.getSmsCode(smsCode);
        if (StringUtils.isEmpty(cachePhone)) {
            return failedRequest("验证码已过期!");
        } else if (!doctorPhone.equals(cachePhone)) {
            return failedRequest("手机号码错误");
        }

        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor == null) {
            return loginFailedResponse("手机号或验证码错误");
        }
        //生成盐
        String salt = RandomStringUtils.randomAlphanumeric(32).toLowerCase();
        //设置盐
        doctor.setSalt(salt);
        //md5密码加密
        doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()));

        int i = doctorService.updateSelective(doctor);
        if (i > 0) {
            return updateSuccseeResponse("密码重置成功");
        }
        return updateFailedResponse("密码重置失败");
    }

    /**
     * 根据旧密码修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public Map updatePassword(String oldPassword, String newPassword) {

        if(StringUtils.isBlank(newPassword)||newPassword.length()<6){
            return updateFailedResponse("新密码格式不正确");
        }
        if (!this.equalsPassword(oldPassword)) {
            return updateFailedResponse("旧密码不正确");
        }
        if (oldPassword.equals(newPassword)) {
            return updateFailedResponse("新密码与旧密码一样");
        }

        Doctor doctor = redisSerive.getDoctor();
        //生成盐
        String salt = RandomStringUtils.randomAlphanumeric(32).toLowerCase();
        //设置盐
        doctor.setSalt(salt);
        //md5密码加密
        doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((newPassword + salt).getBytes()));

        int i = doctorService.updateSelective(doctor);
        if (i > 0) {
            redisSerive.insert(doctor.getId(), jsonUtil.objectToJsonStr(doctor));
            return updateSuccseeResponse("密码重置成功");
        }
        return updateFailedResponse("密码重置失败");
    }


    /**
     * 获取用户登陆信息
     * @return
     */
    @GetMapping(value = "getUsername")
    public Map<String, Object> getUsername(){

        Doctor doctor = redisSerive.getDoctor();

        return querySuccessResponse(doctor.getDoctorPhone());
    }

    /**
     * @param token
     * @return
     * @Author MaxCoder
     * @Description 用户 退出登陆 接口
     * @Date: 18:00 2018/4/24
     */
    @PostMapping(value = "logout")
    public Map<String, Object> logout(String token) {
        resultMap = new LinkedHashMap<>();
        boolean b = redisSerive.remove(token);
        if (b) {
            resultMap.put("code", 20000);
            resultMap.put("msg", "账号登出成功");
            resultMap.put("result", null);
        } else {
            resultMap.put("code", 20004);
            resultMap.put("msg", "账号登出失败");
            resultMap.put("result", null);
        }
        return resultMap;
    }


    /**
     * 获取注册 短信验证码
     *
     * @param registerPhone
     * @return
     */
    @GetMapping(value = "registerSms")
    public Map registerSms(String registerPhone) {

        Doctor doctor = doctorService.selectByPhone(registerPhone);

        if (doctor != null) {
            return registerFiledResponse("手机号码已注册过");
        }
        return thirdServer.sendRegisterCode(registerPhone);
    }


    /**
     * 获取 登陆 短信验证码
     *
     * @param loginPhone
     * @return
     */
    @GetMapping(value = "loginSms")
    public Map loginSms(String loginPhone) {

        Doctor doctor = doctorService.selectByPhone(loginPhone);

        if (doctor == null) {
            return loginFailedResponse("手机号码未注册!");
        }
        return thirdServer.sendLoginCode(loginPhone);
    }

    /**
     * 获取 修改密码 短信验证码
     *
     * @param passwordPhone
     * @return
     */
    @GetMapping(value = "passwordSms")
    public Map passwordSms(String passwordPhone) {

        Doctor doctor = doctorService.selectByPhone(passwordPhone);

        if (doctor == null) {
            return loginFailedResponse("手机号码未注册!");
        }
        return thirdServer.sendPasswordReplaceCode(passwordPhone);
    }

    /**
     * 校验密码是否是旧密码
     *
     * @param password
     * @return
     */
    private boolean equalsPassword(String password) {

        Doctor doctor = redisSerive.getDoctor();

        if (doctor == null) {
            return false;
        }
        String salt = doctor.getSalt();
        if (doctor.getDoctorPassword().equals(DigestUtils.md5DigestAsHex((password + salt).getBytes()))) {
            return true;
        } else {
            return false;
        }
    }
}
