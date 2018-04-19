package com.prostate.doctor.service;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.DoctorDetail;
import com.prostate.doctor.mapper.DoctorDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 10:42 2018/4/19
 * @Modified By:
 */
@Service
public interface DoctorDetailService extends BaseService<DoctorDetail>{


     List<DoctorDetail> selectByDoctorId(String doctorId);

}
