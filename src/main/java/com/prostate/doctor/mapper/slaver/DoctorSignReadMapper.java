package com.prostate.doctor.mapper.slaver;

import com.prostate.doctor.bean.DoctorSignBean;
import com.prostate.doctor.entity.DoctorSign;

public interface DoctorSignReadMapper extends BaseReadMapper<DoctorSign>{

    DoctorSignBean selectByToken(String id);

    String selectSignStatus(String id);
}