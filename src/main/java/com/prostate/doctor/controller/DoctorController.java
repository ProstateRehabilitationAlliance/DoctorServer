package com.prostate.doctor.controller;

import com.prostate.doctor.cache.redis.RedisSerive;
import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.param.DoctorRegisteParams;
import com.prostate.doctor.service.DoctorService;
import com.prostate.doctor.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
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
    private RedisSerive redisSerive;

//    /**
//     * @Description: 检查数据接口
//     * @Date: 9:40  2018/4/20
//     * @Params: * @param null
//     */
//
//    @RequestMapping(value = "check", method = RequestMethod.POST)
//    public Map checkDoctor(@RequestParam("param") String param, @RequestParam("type") Integer type) {
//        log.info("检查数据接口" + new Date());
//        //1.判断手机号是否可用
//        if (type == 1) {
//            List<Doctor> list = doctorService.selectByPhone(param);
//            if (list == null || list.size() == 0) {
//                return querySuccessResponse(true);
//            } else {
//                return querySuccessResponse(false);
//            }
//        }
//        return querySuccessResponse(false);
//    }


    /**
     * @Author: feng
     * @Description: 注册接口
     * @Date: 12:58  2018/4/19
     * @Params: * @param null
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Map registerDoctor(@Valid DoctorRegisteParams doctorRegisteParams) {

        log.info("doctorPhone=" + doctorRegisteParams.getDoctorPhone());
        log.info("doctorPassword=" + doctorRegisteParams.getDoctorPassword());
        log.info("smsCode=" + doctorRegisteParams.getSmsCode());
        //短信验证码校验
        String ck = redisSerive.getSmsCode(doctorRegisteParams.getSmsCode());
        if (StringUtils.isEmpty(ck)) {
            return failedRequest("验证码已过期!");
        }
        //手机号重复注册数据校验
        Doctor doctor = new Doctor();
        doctor.setDoctorPhone(doctorRegisteParams.getDoctorPhone());
        //生成盐
        String salt = RandomStringUtils.randomAlphanumeric(32).toLowerCase();
        //设置盐
        doctor.setSalt(salt);
        //md5密码加密
        doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((doctorRegisteParams.getDoctorPassword() + salt).getBytes()));

        //这里做一次数据检查
        List<Doctor> list = doctorService.selectByPhone(doctor.getDoctorPhone());
        if (list == null | list.size() == 0) {
            int result = doctorService.insertSelective(doctor);
            if (result > 0) {
                return registerSuccseeResponse("注册成功");
            }
        }
        return registerFiledResponse("注册失败,该手机号已被注册");
    }


    /**
     * @Author: feng
     * @Description: 2. 登陆接口
     * @Date: 13:58  2018/4/19
     * @Params: * @param null
     */

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map loginDoctor(String doctorPhone, String doctorPassword, HttpServletRequest request) {
        List<Doctor> list = doctorService.selectByPhone(doctorPhone);
        resultMap = new LinkedHashMap<>();
        if (list == null) {
            resultMap.put("code", 20005);
            resultMap.put("msg", "用户名或密码不正确");
            resultMap.put("result", null);
            return resultMap;

        } else if (list.size() == 1) {
            log.info("======获取成功====");
            Doctor doctor = list.get(0);
            String salt = doctor.getSalt();
            if (list.get(0).getDoctorPassword().equals(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()))) {
                String token = doctor.getId();
                redisSerive.insert(token, jsonUtil.objectToJsonStr(list.get(0)));
                resultMap.put("code", 20000);
                resultMap.put("msg", "登陆成功");
                resultMap.put("result", token);
                log.info("======登陆成功====");

                return resultMap;

            } else {
                log.info("======登陆失败====");

                resultMap.put("code", 20004);
                resultMap.put("msg", "用户名或密码不正确");
                resultMap.put("result", null);
                return resultMap;

            }
        }
        return resultMap;
    }

//    /**
//     * @Author: feng
//     * @Description: 修改密码
//     * @Date: 16:45  2018/4/19
//     * @Params: * @param null
//     */
//
//    @RequestMapping(value = "updDoctorPassword", method = RequestMethod.POST)
//    public Map updDoctorPassword(String doctorPhone, String doctorPassword
//            , @RequestParam("newPassword") String newPassword) {
//        List<Doctor> list = doctorService.selectByPhone(doctorPhone);
//        resultMap = new LinkedHashMap<>();
//
//        if (list == null) {
//            resultMap.put("code", 20005);
//            resultMap.put("msg", "没有数据");
//            resultMap.put("result", null);
//        } else if (list.size() == 1) {
//            String salt = list.get(0).getSalt();
//            if (list.get(0).getDoctorPassword().equals(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()))) {
//                Doctor doctor = list.get(0);
//                log.info(doctorPhone + "手机号密码修改成功" + new Date());
//                System.out.println("===>" + newPassword);
//                doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((newPassword + list.get(0).getSalt()).getBytes()));
//                doctorService.updDoctorPassword(doctor);
//
//                resultMap.put("code", 20000);
//                resultMap.put("msg", "密码修改成功");
//                resultMap.put("result", null);
//            } else {
//                resultMap.put("code", 20004);
//                resultMap.put("msg", "密码不正确");
//                resultMap.put("result", null);
//            }
//        }
//        return resultMap;
//    }


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
}
