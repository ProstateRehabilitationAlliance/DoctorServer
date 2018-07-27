package com.prostate.doctor.service;

import com.prostate.doctor.entity.FansStar;
import org.springframework.stereotype.Service;

@Service
public interface FansStarService extends BaseService<FansStar> {
    int deleteByParams(FansStar fansStar);

    String getFansCount(String doctorId);
}
