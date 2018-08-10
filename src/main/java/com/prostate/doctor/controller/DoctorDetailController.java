package com.prostate.doctor.controller;

import com.prostate.doctor.bean.DoctorDetailBean;
import com.prostate.doctor.bean.DoctorDetailListBean;
import com.prostate.doctor.bean.DoctorOwnDetailBean;
import com.prostate.doctor.common.SignStatus;
import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.entity.DoctorDetail;
import com.prostate.doctor.entity.DoctorSign;
import com.prostate.doctor.feignService.StaticServer;
import com.prostate.doctor.param.UpdateDoctorDetailParams;
import com.prostate.doctor.service.DoctorDetailService;
import com.prostate.doctor.service.DoctorSignService;
import com.prostate.doctor.service.FansStarService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "doctor/detail")
public class DoctorDetailController extends BaseController {

    @Autowired
    private DoctorDetailService doctorDetailService;

    @Autowired
    private StaticServer staticServer;

    @Autowired
    private FansStarService fansStarService;

    @Autowired
    private DoctorSignService doctorSignService;


    /**
     * 医生查询个人信息
     *
     * @return
     */
    @GetMapping(value = "getDoctorDetail")
    public Map get() {

        Doctor doctor = redisSerive.getDoctor();


        //根据TOKEN 信息 查询医生个人信息
        DoctorDetail doctorDetail = doctorDetailService.selectById(doctor.getId());

        if (doctorDetail == null) {
            DoctorSign doctorSign = doctorSignService.selectByToken(doctor.getId());
            if (doctorSign == null || !doctorSign.getApproveStatus().equals(SignStatus.AUTHENTICATION_SUCCESS.toString())) {
                return queryEmptyResponse();
            }
            //识别身份证信息
            Map<String, Object> idCardMap = thirdServer.idCard(doctorSign.getIdCardFront());
            //添加医生个人信息
            doctorDetail = new DoctorDetail();
            doctorDetail.setHospitalId(doctorSign.getHospitalId());
            doctorDetail.setBranchId(doctorSign.getBranchId());
            doctorDetail.setTitleId(doctorSign.getTitleId());
            doctorDetail.setId(doctor.getId());
            Map<String, Object> idCardInfo = (Map<String, Object>) idCardMap.get("result");

            doctorDetail.setDoctorCardNumber(idCardInfo.get("id").toString());
            doctorDetail.setDoctorName(idCardInfo.get("name").toString());
            doctorDetail.setDoctorSex(idCardInfo.get("sex").toString());
            doctorDetail.setDoctorAddress(idCardInfo.get("address").toString());
            int i = doctorDetailService.insertSelective(doctorDetail);
            if (i <= 0) {
                return queryEmptyResponse();
            }
        }

        DoctorOwnDetailBean doctorOwnDetailBean = new DoctorOwnDetailBean();
        doctorOwnDetailBean.setDoctorDetail(doctorDetail);

        //
        String hospitalName = staticServer.getHospitalById(doctorDetail.getHospitalId()).get("result").toString();
        String branchName = staticServer.getBranchById(doctorDetail.getBranchId()).get("result").toString();
        String titleName = staticServer.getTitleById(doctorDetail.getTitleId()).get("result").toString();

        doctorOwnDetailBean.setHospitalName(hospitalName);
        doctorOwnDetailBean.setBranchName(branchName);
        doctorOwnDetailBean.setTitleName(titleName);

        return querySuccessResponse(doctorOwnDetailBean);
    }

    /**
     * 查询 身份证信息
     * @return
     */
    @GetMapping(value = "getIdCardInfo")
    public Map getIdCardInfo() {

        Doctor doctor = redisSerive.getDoctor();

        //根据TOKEN 信息 查询医生个人信息
        DoctorDetail doctorDetail = doctorDetailService.selectIdCardInfo(doctor.getId());

        if (doctorDetail == null) {
            return queryEmptyResponse();
        }
        String cardNo = doctorDetail.getDoctorCardNumber();
        doctorDetail.setDoctorCardNumber(cardNo.substring(0,3)+"********"+cardNo.substring(cardNo.length()-4,cardNo.length()));
        return querySuccessResponse(doctorDetail);
    }
    /**
     * 修改 医生个人信息
     *
     * @param updateDoctorDetailParams
     * @return
     */
    @PostMapping(value = "updateDoctorDetail")
    public Map updateDoctorDetail(@Valid UpdateDoctorDetailParams updateDoctorDetailParams) {

        Doctor doctor = redisSerive.getDoctor();

        DoctorDetail doctorDetail = new DoctorDetail();

        doctorDetail.setId(doctor.getId());
        doctorDetail.setHeadImg(updateDoctorDetailParams.getHeadImg());
        doctorDetail.setDoctorResume(updateDoctorDetailParams.getDoctorResume());
        doctorDetail.setDoctorStrong(updateDoctorDetailParams.getDoctorStrong());

        int i = doctorDetailService.updateSelective(doctorDetail);
        if (i > 0) {
            return updateSuccseeResponse("个人信息修改成功");
        }
        return updateFailedResponse("个人信息修改失败!");
    }


    /**
     * 根据条件查询医生列表信息
     *
     * @param doctorName
     * @param hositalId
     * @return
     */
    @GetMapping(value = "findDoctorList")
    public Map findDoctorList(String doctorName, String hositalId) {


        DoctorDetail doctorDetail = new DoctorDetail();

        doctorDetail.setDoctorName(doctorName);
        doctorDetail.setHospitalId(hositalId);

        int count = doctorDetailService.selectDetailListCountByParams(doctorDetail);

        if (count <= 0) {
            return queryEmptyResponse();
        }
        List<DoctorDetailListBean> doctorDetailListBeans = doctorDetailService.selectDetailListByParams(doctorDetail);
        if (doctorDetailListBeans.isEmpty()) {
            return queryEmptyResponse();
        } else {
            return querySuccessResponse(doctorDetailListBeans, String.valueOf(count));
        }
    }

    /**
     * 根据ID查询医生信息
     *
     * @param doctorId
     * @return
     */
    @GetMapping(value = "getDoctorDetailById")
    public Map getDoctorDetailById(String doctorId) {
        if (StringUtils.isBlank(doctorId) || doctorId.length() != 32) {
            return failedParamResponse("医生ID格式错误");
        }

        //查询医生信息
        DoctorDetail doctorDetail = doctorDetailService.selectById(doctorId);
        if (doctorDetail==null){
            return queryEmptyResponse();
        }
        //查询呗关注数量
        String fansCount = fansStarService.getFansCount(doctorId);
        //查询帮助的患者数量
        String patientCount = "0";
        //查询访问量
        String hitsCount = "0";

        DoctorDetailBean doctorDetailBean = new DoctorDetailBean();

        doctorDetailBean.setDoctorDetail(doctorDetail);
        doctorDetailBean.setFansCount(fansCount);
        doctorDetailBean.setPatientCount(patientCount);
        doctorDetailBean.setHitsCount(hitsCount);

        if (doctorDetailBean == null) {
            return queryEmptyResponse();
        } else {
            return querySuccessResponse(doctorDetailBean);
        }
    }
}
