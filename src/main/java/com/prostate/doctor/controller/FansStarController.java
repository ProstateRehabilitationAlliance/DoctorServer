package com.prostate.doctor.controller;

import com.prostate.doctor.bean.DoctorDetailListBean;
import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.FansStar;
import com.prostate.doctor.entity.WeChatUser;
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
     * 医生 关注医生
     *
     * @param doctorId
     * @return
     */
    @PostMapping(value = "focus")
    public Map<String, Object> focus(String doctorId) {

        Doctor doctor = redisSerive.getDoctor();
        FansStar fansStar = new FansStar();

        fansStar.setFansId(doctor.getId());
        fansStar.setStarId(doctorId);

        int result = fansStarService.insertSelective(fansStar);

        if (result > 0) {
            new Thread(() -> {
                recordServer.addDoctorFocus(doctorId);
                Thread.currentThread().interrupt();
            }).start();
            return insertSuccseeResponse("关注成功");
        }
        return insertFailedResponse("关注失败");
    }

    /**
     * 医生 取消关注医生
     *
     * @param doctorId
     * @return
     */
    @PostMapping(value = "unFocus")
    public Map<String, Object> unFocus(String doctorId) {

        Doctor doctor = redisSerive.getDoctor();

        FansStar fansStar = new FansStar();

        fansStar.setFansId(doctor.getId());
        fansStar.setStarId(doctorId);

        int result = fansStarService.deleteByParams(fansStar);

        if (result > 0) {
            new Thread(() -> {
                recordServer.addDoctorUnFocus(doctorId);
                Thread.currentThread().interrupt();
            }).start();
            return deleteSuccseeResponse("取消关注成功");
        }
        return deleteFailedResponse("取消关注失败");
    }



/****************=================微信公众号接口==============*****************/
    /**
     * 微信用户 关注医生
     *
     * @param doctorId
     * @return
     */
    @PostMapping(value = "weChatFocus")
    public Map<String, Object> weChatFocus(String doctorId) {
        WeChatUser weChatUser = redisSerive.getWechatUser();
        FansStar fansStar = new FansStar();

        fansStar.setFansId(weChatUser.getId());
        fansStar.setStarId(doctorId);

        int result = fansStarService.insertSelective(fansStar);

        if (result > 0) {
            new Thread(() -> {
                recordServer.addPatientFocus(doctorId);
                Thread.currentThread().interrupt();
            }).start();
            return insertSuccseeResponse("关注成功");
        }
        return insertFailedResponse("关注失败");
    }

    /**
     * 微信用户 取消关注医生
     *
     * @param doctorId
     * @return
     */
    @PostMapping(value = "weChatUnFocus")
    public Map<String, Object> weChatUnFocus(String doctorId) {

        WeChatUser weChatUser = redisSerive.getWechatUser();

        FansStar fansStar = new FansStar();

        fansStar.setFansId(weChatUser.getId());
        fansStar.setStarId(doctorId);

        int result = fansStarService.deleteByParams(fansStar);

        if (result > 0) {
            new Thread(() -> {
                recordServer.addPatientUnFocus(doctorId);
                Thread.currentThread().interrupt();
            }).start();
            return deleteSuccseeResponse("取消关注成功");
        }
        return deleteFailedResponse("取消关注失败");
    }

    /**
     * 微信用户 查询 关注的医生
     *
     * @return
     */
    @GetMapping(value = "weChatFindStar")
    public Map<String, Object> weChatFindStar() {

        WeChatUser weChatUser = redisSerive.getWechatUser();

        FansStar fansStar = new FansStar();

        fansStar.setFansId(weChatUser.getId());

        //查询已关注的 医生列表
        List<FansStar> fansStarList = fansStarService.selectByParams(fansStar);
        if (fansStarList.isEmpty()) {
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

