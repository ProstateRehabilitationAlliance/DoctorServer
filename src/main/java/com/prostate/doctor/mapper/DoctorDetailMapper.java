package com.prostate.doctor.mapper;

import com.prostate.doctor.entity.DoctorDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(DoctorDetail record);

    int insertSelective(DoctorDetail record);

    DoctorDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DoctorDetail record);

    int updateByPrimaryKey(DoctorDetail record);


    List<DoctorDetail> selectByDoctorId();
}