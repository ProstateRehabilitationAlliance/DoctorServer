//package com.prostate.doctor.service.impl;
//
//import com.prostate.doctor.entity.DoctorDetail;
//import com.prostate.doctor.mapper.master.DoctorDetailWriteMapper;
//import com.prostate.doctor.mapper.slaver.DoctorDetailReadMapper;
//import com.prostate.doctor.service.DoctorDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @Author: developerfengrui
// * @Description:
// * @Date: Created in 10:42 2018/4/19
// * @Modified By:
// */
//@Service
//public class DoctorDetailServiceImpl implements DoctorDetailService {
//
//    @Autowired
//    private DoctorDetailWriteMapper doctorDetailWriteMapper;
//
//    @Autowired
//    private DoctorDetailReadMapper doctorDetailReadMapper;
//
///**
//*    @Author: feng
//*    @Description:  医生详情表添加一条数据
//*    @Date:  18:12  2018/4/19
//*    @Params:   * @param null
//*/
//
//    @Override
//    public int insertSelective(DoctorDetail doctorDetail) {
//        return doctorDetailWriteMapper.insertSelective(doctorDetail);
//    }
///**
//*    @Author: feng
//*    @Description:  更新医生的详情信息
//*    @Date:  18:25  2018/4/19
//*    @Params:   * @param null
//*/
//
//    @Override
//    public int updateSelective(DoctorDetail doctorDetail) {
//        return doctorDetailWriteMapper.updateSelective(doctorDetail);
//    }
//
//    @Override
//    public DoctorDetail selectById(String id) {
//        return null;
//    }
//
//    @Override
//    public List<DoctorDetail> selectByParams(DoctorDetail doctorDetail) {
//        return null;
//    }
//
//    @Override
//    public int deleteById(String id) {
//        return 0;
//    }
//
//}
