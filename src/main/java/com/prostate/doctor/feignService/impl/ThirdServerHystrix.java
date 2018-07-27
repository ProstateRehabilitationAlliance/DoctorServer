package com.prostate.doctor.feignService.impl;

import com.prostate.doctor.feignService.ThirdServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ThirdServerHystrix extends BaseServerHystrix implements ThirdServer {


    @Override
    public Map<String, Object> idCard(String url) {
        return resultMap;
    }
}
