package com.prostate.doctor.controller;

import com.prostate.doctor.bean.DoctorSignBean;
import com.prostate.doctor.common.SignStatus;
import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.DoctorSign;
import com.prostate.doctor.service.DoctorSignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

    /**
     * 医生提交认证资料
     *
     * @param doctorSign
     * @return
     */
    @PostMapping(value = "add")
    public Map add(@Valid DoctorSign doctorSign) {

        Doctor doctor = redisSerive.getDoctor();

        //重复插入校验
        DoctorSign doctorSigns = doctorSignService.selectByToken(doctor.getId());
        if (doctorSigns != null) {
            return insertFailedResponse("认证信息已上传成功!");
        }
        //插入数据赋值
        doctorSign.setDoctorId(doctor.getId());
        doctorSign.setId(doctor.getId());

        int i = doctorSignService.insertSelective(doctorSign);

        //插入结果校验
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

        DoctorSignBean doctorSignBean = doctorSignService.selectByToken(doctor.getId());

        if (doctorSignBean == null) {
            return queryEmptyResponse();
        }

        String hospitalName = staticServer.getHospitalById(doctorSignBean.getHospitalId()).get("result").toString();
        String branchName = staticServer.getBranchById(doctorSignBean.getBranchId()).get("result").toString();
        String titleName = staticServer.getTitleById(doctorSignBean.getTitleId()).get("result").toString();

        doctorSignBean.setHospitalName(hospitalName);
        doctorSignBean.setBranchName(branchName);
        doctorSignBean.setTitleName(titleName);

        return querySuccessResponse(doctorSignBean);
    }

    /**
     * 查询认证状态
     *
     * @return
     */
    @GetMapping(value = "getSignStatus")
    public Map getSignStatus() {
        Doctor doctor = redisSerive.getDoctor();

        String signStatus = doctorSignService.selectSignStatus(doctor.getId());

        if (StringUtils.isBlank(signStatus)) {
            return authenticationEmptyResponse(SignStatus.AUTHENTICATION_EMPTY);
        }
        if (signStatus.equals(SignStatus.AUTHENTICATION_SUCCESS.toString())) {
            return authenticationSuccessResponse(signStatus);
        } else if (signStatus.equals(SignStatus.AUTHENTICATION_PROGRESS.toString())) {
            return authenticationResponse(signStatus);
        }
        return authenticationFailedResponse(signStatus);
    }

    /**
     * 修改认证资料
     * @param doctorSign
     * @return
     */
    @PostMapping(value = "update")
    public Map update(@Valid DoctorSign doctorSign) {
        Doctor doctor = redisSerive.getDoctor();

        doctorSign.setId(doctor.getId());
        doctorSign.setApproveStatus(SignStatus.AUTHENTICATION_PROGRESS.toString());
        int i = doctorSignService.updateSelective(doctorSign);
        if (i > 0) {
            return updateSuccseeResponse("认证资料修改成功");
        }
        return updateFailedResponse("认证资料修改失败");
    }

}
