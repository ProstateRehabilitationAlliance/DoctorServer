package com.prostate.doctor.controller;

import com.prostate.doctor.cache.redis.RedisSerive;
import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.DoctorDetail;
import com.prostate.doctor.entity.DoctorSign;
import com.prostate.doctor.feignService.ThirdServer;
import com.prostate.doctor.service.DoctorDetailService;
import com.prostate.doctor.service.DoctorSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RedisSerive redisSerive;

    @Autowired
    private ThirdServer thirdServer;


    /**
     * 医生提交认证资料
     *
     * @param doctorSign
     * @param token
     * @return
     */
    @PostMapping(value = "add")
    public Map add(@Valid DoctorSign doctorSign, String token) {

        log.info(doctorSign.toString());
        Doctor doctor = redisSerive.getDoctor(token);

        doctorSign.setDoctorId(doctor.getId());
        doctorSign.setId(doctor.getId());
        int i = doctorSignService.insertSelective(doctorSign);
        if (i > 0) {
            return insertSuccseeResponse("认证资料提交成功");
        }
        return insertFailedResponse("认证资料提交失败");
    }

    /**
     * 认证信息审核通过
     */
    @PostMapping(value = "agree")
    public Map agree(String doctorSignId) {

        //修改认证状态
        DoctorSign doctorSign = new DoctorSign();
        doctorSign.setId(doctorSignId);
        doctorSign.setApproveStatus("1");
        doctorSignService.updateSelective(doctorSign);
        //查询认证信息
        doctorSign = doctorSignService.selectById(doctorSignId);
        //识别身份证信息
        Map<String,Object> idCardMap = thirdServer.idCard(doctorSign.getIdCardFront());
        //添加医生个人信息
        DoctorDetail doctorDetail = new DoctorDetail();
        doctorDetail.setHospitalId(doctorSign.getHospitalId());
        doctorDetail.setBranchId(doctorSign.getBranchId());
        doctorDetail.setTitleId(doctorSign.getTitleId());
        doctorDetail.setId(doctorSign.getId());
        log.info( idCardMap.toString());
        Map<String, Object> idCardInfo = (Map<String, Object>) idCardMap.get("result");

        doctorDetail.setDoctorCardNumber( idCardInfo.get("id").toString());
        doctorDetail.setDoctorName(idCardInfo.get("name").toString());
        doctorDetail.setDoctorSex(idCardInfo.get("sex").toString());
        doctorDetail.setDoctorAddress(idCardInfo.get("address").toString());

        int i = doctorDetailService.insertSelective(doctorDetail);
        if (i > 0) {
            return updateSuccseeResponse("认证信息处理成功!");
        }
        return updateFailedResponse("认证信息处理失败!");
    }

    /**
     * 认证信息审核失败
     */

    @PostMapping(value = "refuse")
    public Map refuse(String doctorSignId) {

        DoctorSign doctorSign = new DoctorSign();
        doctorSign.setId(doctorSignId);
        doctorSign.setApproveStatus("0");

        int i = doctorSignService.updateSelective(doctorSign);
        if (i > 0) {
            return updateSuccseeResponse("认证信息处理成功!");
        }
        return updateFailedResponse("认证信息处理失败!");
    }


}
