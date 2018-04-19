package com.prostate.doctor.controller;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 10:23 2018/4/19
 * @Modified By:
 */
@RestController
public class DoctorController extends BaseController{

    @Autowired
    private DoctorService doctorService;



    /**
    *    @Author: feng
    *    @Description:   注册接口
    *    @Date:  12:58  2018/4/19
    *    @Params:   * @param null
    */
    
    @RequestMapping(value = "doctor/register",method = RequestMethod.POST)
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
            //md5加密
            doctor.setSalt(salt);
            doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((doctor.getDoctorPassword()+salt).getBytes()));
            int result=doctorService.insertSelective(doctor);
            if(result>0){
                resultMap.put("state","200");
                resultMap.put("message","注册成功");
            }else{
                resultMap.put("state","500");
                resultMap.put("message","注册失败");
            }
        }else {
            resultMap.put("state","500");
            resultMap.put("message","注册失败");
        }
        return resultMap;
    }



/**
*    @Author: feng
*    @Description: 2. 登陆接口
*    @Date:  13:58  2018/4/19
*    @Params:   * @param null
*/

    @RequestMapping(value = "doctor/login/{doctorPhone}/{doctorPassword}",method = RequestMethod.GET)
    public Map loginDoctor(@PathVariable  String doctorPhone,@PathVariable String doctorPassword){
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

    @RequestMapping(value = "doctor//{doctorPhone}/{doctorPassword}/{newPassword}",method = RequestMethod.GET)
    public Map updDoctorPassword(@PathVariable  String doctorPhone,@PathVariable String doctorPassword
                    ,@PathVariable String newPassword){
        List<Doctor> list=doctorService.selectByPhone(doctorPhone);
        if (list==null){
            resultMap.put("status",500);
            resultMap.put("msg","没有数据");
            resultMap.put("data",null);
        }else if (list.size()==1){
            String salt=list.get(0).getSalt();
            if (list.get(0).getDoctorPassword().equals(DigestUtils.md5DigestAsHex((doctorPassword+salt).getBytes()))){
                Doctor doctor=list.get(0);
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
