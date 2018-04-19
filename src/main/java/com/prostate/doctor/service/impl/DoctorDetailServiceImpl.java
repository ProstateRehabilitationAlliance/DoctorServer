package com.prostate.doctor.service.impl;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.DoctorDetail;
import com.prostate.doctor.mapper.DoctorDetailMapper;
import com.prostate.doctor.service.BaseService;
import com.prostate.doctor.service.DoctorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 10:42 2018/4/19
 * @Modified By:
 */
@Service
public class DoctorDetailServiceImpl implements DoctorDetailService {

    @Autowired
    private DoctorDetailMapper doctorDetailMapper;


    @Override
    public int insertSelective(DoctorDetail doctorDetail) {
        return doctorDetailMapper.insertSelective(doctorDetail);
    }

    @Override
    public int updateSelective(DoctorDetail doctorDetail) {
        return 0;
    }

    @Override
    public DoctorDetail selectById(String id) {
        return null;
    }

    @Override
    public List<DoctorDetail> selectByParams(DoctorDetail doctorDetail) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public List<DoctorDetail> selectByDoctorId(String doctorId) {

        return null;
    }
}
