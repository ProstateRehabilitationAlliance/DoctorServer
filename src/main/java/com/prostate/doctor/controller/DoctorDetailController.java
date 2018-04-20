package com.prostate.doctor.controller;

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
public class DoctorDetailController extends BaseController{
    @Autowired
    private DoctorDetailService doctorDetailService;



/**
*    @Author: feng
*    @Description: 医生个人信息添加
*    @Date:  15:09  2018/4/19
*    @Params:   * @param null
*/

    @RequestMapping(value = "/doctordetail/add",method = RequestMethod.POST)
    public Map addDoctorInfo(DoctorDetail doctorDetail){
        //保证表中个人信息唯一性
        List<DoctorDetail> list=doctorDetailService.selectByDoctorId(doctorDetail.getDoctorId());
        if (list.size()==1){
            DoctorDetail doctorDetail01=list.get(0);

            resultMap.put("status",400);
            resultMap.put("msg","数据已经存在");
            resultMap.put("data",false);
            return resultMap;
        }

        int result=doctorDetailService.insertSelective(doctorDetail);
        if(result>0){
            log.info(doctorDetail.getDoctorName()+"医生个人信息添加成功"+new Date());
            resultMap.put("status",200);
            resultMap.put("msg","注册成功");
            resultMap.put("data",false);
        }else{
            resultMap.put("status",500);
            resultMap.put("msg","注册失败");
            resultMap.put("data",false);
        }

        return resultMap;
    }
/**
*    @Author: feng
*    @Description:  医生个人信息修改
*    @Date:  15:11  2018/4/19
*    @Params:   * @param null
*/

    @RequestMapping(value = "/doctordetail/upd",method = RequestMethod.POST)
    public Map updDoctorInfo(DoctorDetail doctorDetail){
        int result=doctorDetailService.updateSelective(doctorDetail);
        if(result>0){
            resultMap.put("status",200);
            log.info(doctorDetail.getDoctorName()+"医生个人信息修改成功"+new Date());
            resultMap.put("msg","医生详情更新成功");
            resultMap.put("data",false);
        }else{
            resultMap.put("status",400);
            resultMap.put("msg","医生详情更新失败");
            resultMap.put("data",false);
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

    @RequestMapping(value = "/doctordetail/select",method = RequestMethod.GET)
    public Map findDoctorInfo(@RequestParam("doctorId") String doctorId){
       List<DoctorDetail> list=doctorDetailService.selectByDoctorId(doctorId);
        if (list==null){
            resultMap.put("status",400);
            resultMap.put("msg","没有数据");
            resultMap.put("data",null);

        }else if(list.size()==1){
            DoctorDetail doctorDetail=list.get(0);
            log.info(doctorDetail.getDoctorName()+"医生个人信息查询成功"+new Date());
            resultMap.put("status",200);
            resultMap.put("msg","数据获取成功");
            resultMap.put("data",doctorDetail);

        }else{
            resultMap.put("status",500);
            resultMap.put("msg","数据获取不只一条");
            resultMap.put("data",null);
        }

        return resultMap;
    }

}