package com.prostate.doctor.mapper.slaver;


import com.prostate.doctor.bean.DoctorDetailListBean;
import com.prostate.doctor.entity.DoctorDetail;

import java.util.List;

public interface DoctorDetailReadMapper extends BaseReadMapper<DoctorDetail>{

    List<DoctorDetailListBean> getDoctorDetailByArrayParams(List<String> stringList);

    List<DoctorDetailListBean> selectDetailListByParams(DoctorDetail doctorDetail);
}