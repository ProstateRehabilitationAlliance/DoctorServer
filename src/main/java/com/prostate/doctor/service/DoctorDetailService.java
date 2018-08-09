package com.prostate.doctor.service;

import com.prostate.doctor.bean.DoctorDetailListBean;
import com.prostate.doctor.entity.DoctorDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface DoctorDetailService extends BaseService<DoctorDetail>{

    List<DoctorDetailListBean> getDoctorDetailByArrayParams(List<String> stringList);

    List<DoctorDetailListBean> selectDetailListByParams(DoctorDetail doctorDetail);

    Map<String,String> selectStars(List<String> starList);

    int selectDetailListCountByParams(DoctorDetail doctorDetail);
}
