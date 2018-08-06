package com.prostate.doctor.controller;


import com.prostate.doctor.entity.FansStar;
import com.prostate.doctor.service.DoctorDetailService;
import com.prostate.doctor.service.FansStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "cache/user")
public class CachDataController extends BaseController {

    @Autowired
    private FansStarService fansStarService;

    @Autowired
    private DoctorDetailService doctorDetailService;


    /**
     * 查询已关注的医生
     *
     * @param token
     * @return
     */
    @GetMapping(value = "getStarJson")
    public Map<String, Object> getStarJson(String token) {
        FansStar fansStar = new FansStar();

        List<FansStar> fansStarList = fansStarService.selectByParams(fansStar);
        if (fansStarList.isEmpty()) {
            return queryEmptyResponse();
        }
        List<String> starList = new ArrayList<>();
        for (FansStar star : fansStarList) {
            starList.add(star.getStarId());
        }
        Map<String, String> starMap = doctorDetailService.selectStars(starList);

        if (starMap == null || starMap.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(starMap);

    }


}
