package com.prostate.doctor.service.impl;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.mapper.DoctorMapper;
import com.prostate.doctor.service.BaseService;
import com.prostate.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 10:30 2018/4/19
 * @Modified By:
 */
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorMapper doctorMapper;


    /**
    *    @Author: feng
    *    @Description: 医生的注册信息dadd
    *    @Date:  13:47  2018/4/19
    *    @Params:   * @param null
    */
    @Override
    public int insertSelective(Doctor doctor) {

        return doctorMapper.insertSelective(doctor);
    }

    @Override
    public int updateSelective(Doctor doctor) {
        return 0;
    }

    @Override
    public Doctor selectById(String id) {
        return null;
    }

    @Override
    public List<Doctor> selectByParams(Doctor doctor) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }


    @Override
    public List<Doctor> selectByPhone(String phone) {
        List<Doctor> list= doctorMapper.selectByPhone(phone);
        return list;
    }
          /**
              *    @Description:   数据更新
              *    @Date:  18:35  2018/4/19
              *    @Params:   * @param null
              */

      
    @Override
    public int updDoctorPassword(Doctor doctor) {

        return doctorMapper.updDoctorPassword(doctor);
    }
}
