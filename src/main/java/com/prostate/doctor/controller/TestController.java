package com.prostate.doctor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class TestController extends BaseController{

    @ResponseBody
    @RequestMapping(value = "test")
    public Map<String,Object> testLog(){
        log.info(">>>>>>>>>>>>>>>LOG TEST");
        resultMap.put("error_code","000");
        resultMap.put("error_msg","SUCCESS");
        return resultMap;
    }



}
