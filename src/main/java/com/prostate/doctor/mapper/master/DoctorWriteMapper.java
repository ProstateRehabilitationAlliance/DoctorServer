package com.prostate.doctor.mapper.master;

import com.prostate.doctor.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DoctorWriteMapper extends BaseWriteMapper<Doctor>{

    int insert(Doctor record);

    int insertSelective(Doctor record);


}