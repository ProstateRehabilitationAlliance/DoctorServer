package com.prostate.doctor.mapper.slaver;

import com.prostate.doctor.entity.FansStar;

import java.util.List;

public interface FansStarReadMapper extends BaseReadMapper<FansStar>{

    String getFansCount(String doctorId);

    List<FansStar> starJson(String id);
}