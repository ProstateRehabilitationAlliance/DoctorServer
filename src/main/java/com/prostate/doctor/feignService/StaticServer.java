package com.prostate.doctor.feignService;


import com.prostate.doctor.feignService.impl.StaticServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 调用 StaticServer 中的 API
 */
@FeignClient(value = "stata-server",fallback = StaticServerHystrix.class)
public interface StaticServer {

    @GetMapping(value = "/anamnesisAllergyDrug/getById")
    Map getAnamnesisAllergyDrugById(@RequestParam("id") String id);

    @GetMapping(value = "/anamnesisEatingDrug/getById")
    Map getAnamnesisEatingDrugById(@RequestParam("id") String id);

    @GetMapping(value = "/anamnesisIllness/getById")
    Map getAnamnesisIllnessById(@RequestParam("id") String id);

    @GetMapping(value = "/surgicalHistory/getById")
    Map getSurgicalHistoryById(@RequestParam("id") String id);

    @GetMapping(value = "/city/getCityDetail")
    Map getCityDetail(@RequestParam("cityId") String cityId);

    @GetMapping(value = "/education/getById")
    Map getEducationById(@RequestParam("id") String id);

    @GetMapping(value = "/bloodGroup/getById")
    Map getBloodGroupById(@RequestParam("id") String id);

    @GetMapping(value = "/nation/getById")
    Map getNationById(@RequestParam("id") String id);

    @GetMapping(value = "/profession/getById")
    Map getProfessionById(@RequestParam("id") String id);

    @GetMapping(value = "/hospital/getById")
    Map getHospitalById(@RequestParam("id") String id);

    @GetMapping(value = "/branch/getById")
    Map getBranchById(@RequestParam("id") String id);

    @GetMapping(value = "/title/getById")
    Map getTitleById(@RequestParam("id") String id);

    @GetMapping(value = "cache/static/getHospitalJson")
    Map<String,Object> hospitalJson();

    @GetMapping(value = "cache/static/getBranchServiceJson")
    Map<String,Object> branchServiceJson();

    @GetMapping(value = "cache/static/getDoctorTitleJson")
    Map<String,Object> doctorTitleJson();
}
