package com.prostate.doctor.service;

import com.prostate.doctor.bean.DoctorSignBean;
import com.prostate.doctor.entity.DoctorSign;
import org.springframework.stereotype.Service;

@Service
public interface DoctorSignService extends BaseService<DoctorSign> {
    DoctorSignBean selectByToken(String id);

    String selectSignStatus(String id);
}
