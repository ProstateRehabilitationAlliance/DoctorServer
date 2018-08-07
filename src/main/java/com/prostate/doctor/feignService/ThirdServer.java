package com.prostate.doctor.feignService;

import com.prostate.doctor.feignService.impl.ThirdServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@FeignClient(value = "third-server",fallback = ThirdServerHystrix.class)
public interface ThirdServer {

    @GetMapping(value = "ocr/idCard")
    Map<String, Object> idCard(@RequestParam("url") String url);


    @PostMapping(value = "sms/sendRegisterCode")
    Map<String, Object> sendRegisterCode(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "sms/sendLoginCode")
    Map<String, Object> sendLoginCode(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "sms/sendPasswordReplaceCode")
    Map<String, Object> sendPasswordReplaceCode(@RequestParam("phoneNumber") String phoneNumber);
}
