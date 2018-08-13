package com.prostate.doctor.feignService;


import com.prostate.doctor.feignService.impl.RecordServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "record-server", fallback = RecordServerHystrix.class)
public interface RecordServer {

    @PostMapping(value = "inquiryCount/addOrderSuccess")
    Map<String, Object> addOrderSuccess(@RequestParam("userId") String userId);

    @PostMapping(value = "focusCount/addDoctorFocus")
    Map<String, Object> addDoctorFocus(@RequestParam("userId") String userId);

    @PostMapping(value = "focusCount/addPatientFocus")
    Map<String, Object> addPatientFocus(@RequestParam("userId") String userId);

    @PostMapping(value = "clickCount/addDoctorClick")
    Map<String, Object> addDoctorClick(@RequestParam("userId") String userId);

    @PostMapping(value = "clickCount/addPatientClick")
    Map<String, Object> addPatientClick(@RequestParam("userId") String userId);
}
