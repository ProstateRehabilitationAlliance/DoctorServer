package com.prostate.doctor.mapper.slaver;

import com.prostate.doctor.entity.DoctorSign;

public interface DoctorSignReadMapper extends BaseReadMapper<DoctorSign>{

    DoctorSign selectByToken(String id);

    String selectSignStatus(String id);
}