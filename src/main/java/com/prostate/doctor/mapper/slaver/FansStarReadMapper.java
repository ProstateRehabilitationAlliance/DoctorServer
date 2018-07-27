package com.prostate.doctor.mapper.slaver;

import com.prostate.doctor.entity.FansStar;

public interface FansStarReadMapper extends BaseReadMapper<FansStar>{

    String getFansCount(String doctorId);
}