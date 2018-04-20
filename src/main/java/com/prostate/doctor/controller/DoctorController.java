package com.prostate.doctor.controller;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 10:23 2018/4/19
 * @Modified By:
 */
@Slf4j
@RestController
public class DoctorController extends BaseController{

    @Autowired
    private DoctorService doctorService;

     /**
         *    @Description:  检查数据接口
         *    @Date:  9:40  2018/4/20
         *    @Params:   * @param null
         */

    @RequestMapping(value = "/doctor/check",method = RequestMethod.POST)
     public Map checkDoctor(@RequestParam("param") String param,@RequestParam("type") Integer type){
        //1.判断手机号是否可用
        if (type == 1) {
            List<Doctor> list = doctorService.selectByPhone(param);
            if (list==null||list.size()==0){
                resultMap.put("status",200);
                resultMap.put("msg","OK,数据可用");
                resultMap.put("data",false);
            }else {
                resultMap.put("status",400);
                resultMap.put("msg","数据不可用");
                resultMap.put("data",false);
            }
        }else{
            resultMap.put("status",400);
            resultMap.put("msg","该数据暂时没有");
            resultMap.put("data",false);
        }
        return resultMap;
    }


    /**
    *    @Author: feng
    *    @Description:   注册接口
    *    @Date:  12:58  2018/4/19
    *    @Params:   * @param null
    */
    
    @RequestMapping(value = "/doctor/register",method = RequestMethod.POST)
    public Map registerDoctor(Doctor doctor){
        if(doctor!=null){
            //生成盐
            Random r = new Random();
            StringBuilder sb = new StringBuilder(16);
            sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
            int len = sb.length();
            if (len < 16) {
                for (int i = 0; i < 16 - len; i++) {
                    sb.append("0");
                }
            }
            String salt = sb.toString();
            //设置盐
            doctor.setSalt(salt);
            //设置创建时间
            doctor.setCreateTime(new Date());
            //同时设置上次登录时间
            doctor.setLastLoginTime(doctor.getCreateTime());
            //md5加密
            doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((doctor.getDoctorPassword()+salt).getBytes()));
            //这里做一次数据检查
            List<Doctor> list = doctorService.selectByPhone(doctor.getDoctorPhone());
            if(list==null|list.size()==0){
                int result=doctorService.insertSelective(doctor);
                if(result>0){
                    log.debug(doctor.getDoctorPhone()+doctor.getDoctorPassword());
                    resultMap.put("status",200);
                    resultMap.put("msg","注册成功");
                    resultMap.put("data",false);
                }else{
                    resultMap.put("status",500);
                    resultMap.put("msg","数据写入失败");
                    resultMap.put("data",false);
                }
            }else if (list.size()==1){
                log.debug(doctor.getDoctorPhone()+doctor.getDoctorPassword());
                resultMap.put("status",400);
                resultMap.put("msg","注册失败,用户数据已经存在");
                resultMap.put("data",false);
            }

        }else {
            resultMap.put("status",400);
            resultMap.put("msg","传过来的数据为空");
            resultMap.put("data",false);
        }
        return resultMap;
    }



/**
*    @Author: feng
*    @Description: 2. 登陆接口
*    @Date:  13:58  2018/4/19
*    @Params:   * @param null
*/

    @RequestMapping(value = "/doctor/login",method = RequestMethod.POST)
    public Map loginDoctor(String doctorPhone,String doctorPassword){
        List<Doctor> list=doctorService.selectByPhone(doctorPhone);
        if (list==null){
            resultMap.put("status",500);
            resultMap.put("msg","没有数据");
            resultMap.put("data",null);
        }else if (list.size()==1){
            String salt=list.get(0).getSalt();
            if (list.get(0).getDoctorPassword().equals(DigestUtils.md5DigestAsHex((doctorPassword+salt).getBytes()))){
                resultMap.put("status",200);
                resultMap.put("msg","数据获取成功");
                resultMap.put("data",list.get(0));
            }else {
                resultMap.put("status",400);
                resultMap.put("msg","密码不正确");
                resultMap.put("data",null);
            }
        }
        return resultMap;
    }

/**
*    @Author: feng
*    @Description: 修改密码
*    @Date:  16:45  2018/4/19
*    @Params:   * @param null
*/

    @RequestMapping(value = "/doctor/updDoctorPassword",method = RequestMethod.POST)
    public Map updDoctorPassword(String doctorPhone,String doctorPassword
                    ,@RequestParam("newPassword") String newPassword){
        List<Doctor> list=doctorService.selectByPhone(doctorPhone);
        if (list==null){
            resultMap.put("status",500);
            resultMap.put("msg","没有数据");
            resultMap.put("data",null);
        }else if (list.size()==1){
            String salt=list.get(0).getSalt();
            if (list.get(0).getDoctorPassword().equals(DigestUtils.md5DigestAsHex((doctorPassword+salt).getBytes()))){
                Doctor doctor=list.get(0);

                System.out.println("===>"+newPassword);
                doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((newPassword+list.get(0).getSalt()).getBytes()));
                doctorService.updDoctorPassword(doctor);
                resultMap.put("status",200);
                resultMap.put("msg","密码修改成功");
                resultMap.put("data",null);
            }else {
                resultMap.put("status",400);
                resultMap.put("msg","密码不正确");
                resultMap.put("data",null);
            }
        }
        return resultMap;
    }



}
