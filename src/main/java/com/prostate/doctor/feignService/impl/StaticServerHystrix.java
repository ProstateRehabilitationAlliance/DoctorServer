package com.prostate.doctor.feignService.impl;

import com.prostate.doctor.feignService.StaticServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StaticServerHystrix extends BaseServerHystrix implements StaticServer {

    @Override
    public Map getAnamnesisAllergyDrugById(String id) {
        return resultMap;
    }

    @Override
    public Map getAnamnesisEatingDrugById(String id) {
        return resultMap;
    }

    @Override
    public Map getAnamnesisIllnessById(String id) {
        return resultMap;
    }

    @Override
    public Map getSurgicalHistoryById(String id) {
        return resultMap;
    }

    @Override
    public Map getCityDetail(String cityId) {
        return resultMap;
    }

    @Override
    public Map getEducationById(String id) {
        return resultMap;
    }

    @Override
    public Map getBloodGroupById(String id) {
        return resultMap;
    }

    @Override
    public Map getNationById(String id) {
        return resultMap;
    }

    @Override
    public Map getProfessionById(String id) {
        return resultMap;
    }

    @Override
    public Map getHospitalById(String id) {
        return resultMap;
    }

    @Override
    public Map getBranchById(String id) {
        return resultMap;
    }

    @Override
    public Map getTitleById(String id) {
        return resultMap;
    }

    @Override
    public Map<String, Object> hospitalJson() {
        return resultMap;
    }

    @Override
    public Map<String, Object> branchServiceJson() {
        return resultMap;
    }

    @Override
    public Map<String, Object> doctorTitleJson() {
        return resultMap;
    }
}
