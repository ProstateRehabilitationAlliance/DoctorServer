package com.prostate.doctor.service.impl;

import com.prostate.doctor.entity.DoctorSign;
import com.prostate.doctor.mapper.master.DoctorSignWriteMapper;
import com.prostate.doctor.mapper.slaver.DoctorSignReadMapper;
import com.prostate.doctor.service.DoctorSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorSignServiceImpl implements DoctorSignService {

    @Autowired
    private DoctorSignWriteMapper doctorSignWriteMapper;

    @Autowired
    private DoctorSignReadMapper doctorSignReadMapper;

    @Override
    public int insertSelective(DoctorSign doctorSign) {
        return doctorSignWriteMapper.insertSelective(doctorSign);
    }

    @Override
    public int deleteById(String id) {
        return doctorSignWriteMapper.deleteById(id);
    }

    @Override
    public int updateSelective(DoctorSign doctorSign) {
        return doctorSignWriteMapper.updateSelective(doctorSign);
    }

    @Override
    public DoctorSign selectById(String id) {
        return doctorSignReadMapper.selectById(id);
    }

    @Override
    public List<DoctorSign> selectByParams(DoctorSign doctorSign) {
        return doctorSignReadMapper.selectByParams(doctorSign);
    }

    @Override
    public DoctorSign selectByToken(String id) {
        return doctorSignReadMapper.selectByToken(id);
    }

    @Override
    public String selectSignStatus(String id) {
        return doctorSignReadMapper.selectSignStatus(id);
    }
}
