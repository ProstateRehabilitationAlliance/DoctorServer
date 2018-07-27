package com.prostate.doctor.feignService;

import com.prostate.doctor.feignService.impl.ThirdServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "third-server",fallback = ThirdServerHystrix.class)
public interface ThirdServer {

    @GetMapping(value = "ocr/idCard")
    Map<String, Object> idCard(@RequestParam("url") String url);
}
