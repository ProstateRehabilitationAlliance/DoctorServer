//package com.prostate.doctor.controller;
//
//import com.prostate.doctor.cache.redis.RedisSerive;
//import com.prostate.doctor.entity.Doctor;
//import com.prostate.doctor.entity.DoctorDetail;
//import com.prostate.doctor.service.DoctorDetailService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @Author: developerfengrui
// * @Description:
// * @Date: Created in 11:33 2018/4/19
// * @Modified By:
// */
//@Slf4j
//@RestController
//@RequestMapping("doctordetail")
//public class DoctorDetailController extends BaseController {
//    @Autowired
//    private DoctorDetailService doctorDetailService;
//
//    @Autowired
//    private RedisSerive redisSerive;
//
//
//    /**
//     * @Author: feng
//     * @Description: 医生个人信息添加
//     * @Date: 15:09  2018/4/19
//     * @Params: * @param null
//     */
//
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public Map addDoctorInfo(DoctorDetail doctorDetail, String token) {
//
//        //保证表中个人信息唯一性
//        Doctor doctor = redisSerive.getDoctor(token);
//        List<DoctorDetail> list = doctorDetailService.selectByDoctorId(doctor.getId());
//        if (list.size() == 1) {
//            return insertFailedResponse("个人信息添加失败");
//        }
//        doctorDetail.setDoctorId(doctor.getId());
//        int result = doctorDetailService.insertSelective(doctorDetail);
//        if (result > 0) {
//            return insertSuccseeResponse("个人信息添加成功");
//        }
//        return insertFailedResponse("个人信息添加失败");
//
//    }
//
//    /**
//     * @Author: feng
//     * @Description: 医生个人信息修改
//     * @Date: 15:11  2018/4/19
//     * @Params: * @param null
//     */
//
//    @RequestMapping(value = "upd", method = RequestMethod.POST)
//    public Map updDoctorInfo(DoctorDetail doctorDetail) {
//
//        int result = doctorDetailService.updateSelective(doctorDetail);
//        if (result > 0) {
//            return updateSuccseeResponse("医生详情更新成功");
//        }
//        return updateFailedResponse();
//
//    }
///**
// *    @Author: feng
// *    @Description:
// *    @Date: 15:12  2018/4/19
// *    @Params:   * @param null
// */
//
//    /**
//     * @Author: feng
//     * @Description: 医生个人信息查询
//     * @Date: 15:12  2018/4/19
//     * @Params: * @param null
//     */
//
//    @GetMapping(value = "select")
//    public Map findDoctorInfo(String token) {
//        List<DoctorDetail> list = doctorDetailService.selectByDoctorId(redisSerive.getDoctor(token).getId());
//
//        if (list == null) {
//            return queryEmptyResponse();
//        } else if (list.size() == 1) {
//            DoctorDetail doctorDetail = list.get(0);
//            return querySuccessResponse(doctorDetail);
//
//        }
//        return queryEmptyResponse();
//    }
//
//}