package com.prostate.doctor.service;

import com.prostate.doctor.entity.DoctorSign;
import org.springframework.stereotype.Service;

@Service
public interface DoctorSignService extends BaseService<DoctorSign> {
    DoctorSign selectByToken(String id);

    String selectSignStatus(String id);
}
