package com.prostate.doctor.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.doctor.entity.DoctorDetail;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDetailBean {

    private String doctorName;

    private String titleId;

    private String hospitalId;

    private String headImg;

    private String doctorResume;

    private String doctorStrong;

    private String lableInquiry;

    private String fansCount;

    private String patientCount;

    private String hitsCount;


    public void setDoctorDetail(DoctorDetail doctorDetail) {

        this.doctorName = doctorDetail.getDoctorName() == null ? null : doctorDetail.getDoctorName().trim();
        this.titleId = doctorDetail.getTitleId() == null ? null : doctorDetail.getTitleId().trim();
        this.hospitalId = doctorDetail.getHospitalId() == null ? null : doctorDetail.getHospitalId().trim();
        this.headImg = doctorDetail.getHeadImg() == null ? null : doctorDetail.getHeadImg().trim();
        this.doctorResume = doctorDetail.getDoctorResume() == null ? null : doctorDetail.getDoctorResume().trim();
        this.doctorStrong = doctorDetail.getDoctorStrong() == null ? null : doctorDetail.getDoctorStrong().trim();
        this.lableInquiry = doctorDetail.getLableInquiry() == null ? null : doctorDetail.getLableInquiry().trim();
    }

}
