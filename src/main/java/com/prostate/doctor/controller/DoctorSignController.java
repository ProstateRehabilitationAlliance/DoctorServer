package com.prostate.doctor.controller;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.DoctorSign;
import com.prostate.doctor.feignService.ThirdServer;
import com.prostate.doctor.service.DoctorDetailService;
import com.prostate.doctor.service.DoctorSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "doctor/sign")
public class DoctorSignController extends BaseController {

    @Autowired
    private DoctorSignService doctorSignService;

    @Autowired
    private DoctorDetailService doctorDetailService;

    @Autowired
    private ThirdServer thirdServer;


    /**
     * 医生提交认证资料
     *
     * @param doctorSign
     * @return
     */
    @PostMapping(value = "add")
    public Map add(@Valid DoctorSign doctorSign) {

        log.info(doctorSign.toString());

        Doctor doctor = redisSerive.getDoctor();

        doctorSign.setDoctorId(doctor.getId());
        doctorSign.setId(doctor.getId());
        int i = doctorSignService.insertSelective(doctorSign);
        if (i > 0) {
            return insertSuccseeResponse("认证资料提交成功");
        }
        return insertFailedResponse("认证资料提交失败");
    }


    /**
     * 查询认证信息
     * @return
     */
    @GetMapping(value = "get")
    public Map get() {
        Doctor doctor = redisSerive.getDoctor();

        DoctorSign doctorSign = doctorSignService.selectById(doctor.getId());

        if (doctorSign == null) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(doctorSign);
    }


    /**
     * 修改认证资料
     * @param doctorSign
     * @return
     */
    @PostMapping(value = "update")
    public Map update(DoctorSign doctorSign) {
        Doctor doctor = redisSerive.getDoctor();

        doctorSign.setId(doctor.getId());
        int i = doctorSignService.updateSelective(doctorSign);
        if (i > 0) {
            return updateSuccseeResponse("认证资料提交成功");
        }
        return updateFailedResponse("认证资料提交失败");
    }

}
