package com.prostate.doctor.mapper.master;

import com.prostate.doctor.entity.FansStar;

public interface FansStarWriteMapper extends BaseWriteMapper<FansStar>{

    int deleteByParams(FansStar fansStar);
}