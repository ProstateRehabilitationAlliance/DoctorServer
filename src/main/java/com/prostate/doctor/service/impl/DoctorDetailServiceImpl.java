package com.prostate.doctor.service.impl;

import com.prostate.doctor.bean.DoctorDetailListBean;
import com.prostate.doctor.entity.DoctorDetail;
import com.prostate.doctor.mapper.master.DoctorDetailWriteMapper;
import com.prostate.doctor.mapper.slaver.DoctorDetailReadMapper;
import com.prostate.doctor.service.DoctorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorDetailServiceImpl implements DoctorDetailService {

    @Autowired
    private DoctorDetailWriteMapper doctorDetailWriteMapper;

    @Autowired
    private DoctorDetailReadMapper doctorDetailReadMapper;


    @Override
    public int insertSelective(DoctorDetail doctorDetail) {
        return doctorDetailWriteMapper.insertSelective(doctorDetail);
    }

    @Override
    public int deleteById(String id) {
        return doctorDetailWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(DoctorDetail doctorDetail) {
        return doctorDetailWriteMapper.updateSelective(doctorDetail);
    }

    @Override
    public DoctorDetail selectById(String id) {
        return doctorDetailReadMapper.selectById(id);
    }

    @Override
    public List<DoctorDetail> selectByParams(DoctorDetail doctorDetail) {
        return doctorDetailReadMapper.selectByParams(doctorDetail);
    }

    @Override
    public List<DoctorDetailListBean> getDoctorDetailByArrayParams(List<String> stringList) {
        return doctorDetailReadMapper.getDoctorDetailByArrayParams(stringList);
    }

    @Override
    public List<DoctorDetailListBean> selectDetailListByParams(DoctorDetail doctorDetail) {
        return doctorDetailReadMapper.selectDetailListByParams(doctorDetail);
    }


}
