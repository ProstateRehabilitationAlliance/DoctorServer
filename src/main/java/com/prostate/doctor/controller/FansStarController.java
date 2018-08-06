package com.prostate.doctor.controller;

import com.prostate.doctor.bean.DoctorDetailListBean;
import com.prostate.doctor.entity.DoctorDetail;
import com.prostate.doctor.entity.FansStar;
import com.prostate.doctor.service.DoctorDetailService;
import com.prostate.doctor.service.FansStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "fansStar")
public class FansStarController extends BaseController {

    @Autowired
    private FansStarService fansStarService;

    @Autowired
    private DoctorDetailService doctorDetailService;

    /**
     * 关注医生
     *
     * @param doctorId
     * @param token
     * @return
     */
    @PostMapping(value = "focus")
    public Map<String, Object> focus(String doctorId, String token) {

        FansStar fansStar = new FansStar();

        fansStar.setFansId(token);
        fansStar.setStarId(doctorId);

        int result = fansStarService.insertSelective(fansStar);

        if (result > 0) {
            return insertSuccseeResponse("关注成功");
        }
        return insertFailedResponse("关注失败");
    }

    /**
     * 取消关注医生
     *
     * @param doctorId
     * @param token
     * @return
     */
    @PostMapping(value = "unFocus")
    public Map<String, Object> unFocus(String doctorId, String token) {

        FansStar fansStar = new FansStar();

        fansStar.setFansId(token);
        fansStar.setStarId(doctorId);

        int result = fansStarService.deleteByParams(fansStar);

        if (result > 0) {
            return deleteSuccseeResponse("取消关注成功");
        }
        return deleteFailedResponse("取消关注失败");
    }

    /**
     * 查询 关注的医生
     *
     * @param token
     * @return
     */
    @GetMapping(value = "findStar")
    public Map<String, Object> findStar(String token) {

        FansStar fansStar = new FansStar();

        fansStar.setFansId(token);

        List<FansStar> fansStarList = fansStarService.selectByParams(fansStar);
        if (fansStarList.isEmpty()){
            return queryEmptyResponse();
        }
        List<String> stringList = new ArrayList<>();
        for (FansStar star : fansStarList) {
            stringList.add(star.getStarId());
        }

        List<DoctorDetailListBean> doctorDetailListBeans = doctorDetailService.getDoctorDetailByArrayParams(stringList);
        if (doctorDetailListBeans.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(doctorDetailListBeans);
    }

}

