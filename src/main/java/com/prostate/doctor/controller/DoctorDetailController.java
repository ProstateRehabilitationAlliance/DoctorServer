package com.prostate.doctor.controller;

import com.prostate.doctor.cache.redis.RedisSerive;
import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.DoctorDetail;
import com.prostate.doctor.service.DoctorDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 11:33 2018/4/19
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/doctordetail")
public class DoctorDetailController extends BaseController{
    @Autowired
    private DoctorDetailService doctorDetailService;

    @Autowired
    private RedisSerive redisSerive;


/**
*    @Author: feng
*    @Description: 医生个人信息添加
*    @Date:  15:09  2018/4/19
*    @Params:   * @param null
*/

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map addDoctorInfo(DoctorDetail doctorDetail,String token){
        //保证表中个人信息唯一性
        Doctor doctor = redisSerive.getDoctor(token);
        List<DoctorDetail> list=doctorDetailService.selectByDoctorId(doctor.getId());
        if (list.size()==1){
            resultMap.put("code",20001);
            resultMap.put("msg","个人信息添加失败");
            resultMap.put("result",false);
            return resultMap;
        }
        doctorDetail.setDoctorId(doctor.getId());
        int result=doctorDetailService.insertSelective(doctorDetail);
        if(result>0){
            resultMap.put("code",20000);
            resultMap.put("msg","个人信息添加成功");
            resultMap.put("result",false);
        }else{
            resultMap.put("code",20005);
            resultMap.put("msg","个人信息添加失败");
            resultMap.put("result",false);
        }

        return resultMap;
    }
/**
*    @Author: feng
*    @Description:  医生个人信息修改
*    @Date:  15:11  2018/4/19
*    @Params:   * @param null
*/

    @RequestMapping(value = "/upd",method = RequestMethod.POST)
    public Map updDoctorInfo(DoctorDetail doctorDetail){
        int result=doctorDetailService.updateSelective(doctorDetail);
        if(result>0){
            resultMap.put("code",20000);
            log.info(doctorDetail.getDoctorName()+"医生个人信息修改成功"+new Date());
            resultMap.put("msg","医生详情更新成功");
            resultMap.put("result",false);
        }else{
            resultMap.put("code",20005);
            resultMap.put("msg","医生详情更新失败");
            resultMap.put("result",false);
        }

        return resultMap;
    }
/**
*    @Author: feng
*    @Description:
*    @Date:  15:12  2018/4/19
*    @Params:   * @param null
*/

/**
*    @Author: feng
*    @Description: 医生个人信息查询
*    @Date:  15:12  2018/4/19
*    @Params:   * @param null
*/

    @GetMapping(value = "/select")
    public Map findDoctorInfo(String token){
       List<DoctorDetail> list=doctorDetailService.selectByDoctorId(redisSerive.getDoctor(token).getId());

        if (list==null){
            resultMap.put("code",20004);
            resultMap.put("msg","没有数据");
            resultMap.put("result",null);

        }else if(list.size()==1){
            DoctorDetail doctorDetail=list.get(0);
            log.info(doctorDetail.getDoctorName()+"医生个人信息查询成功"+new Date());
            resultMap.put("code",20000);
            resultMap.put("msg","数据获取成功");
            resultMap.put("result",doctorDetail);

        }else{
            resultMap.put("code",20006);
            resultMap.put("msg","数据获取不只一条");
            resultMap.put("result",null);
        }

        return resultMap;
    }

}