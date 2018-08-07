package com.prostate.doctor.mapper.slaver;

import com.prostate.doctor.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DoctorReadMapper extends BaseReadMapper<Doctor>{

    Doctor selectByPhone(String phone);
}