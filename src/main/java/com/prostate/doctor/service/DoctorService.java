package com.prostate.doctor.service;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.mapper.DoctorMapper;
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
public interface DoctorService extends BaseService<Doctor>{


    public List<Doctor> selectByPhone(String phone);

   int  updDoctorPassword(Doctor doctor);


}
