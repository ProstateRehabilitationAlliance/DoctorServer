package com.prostate.doctor.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.doctor.entity.DoctorDetail;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorOwnDetailBean implements Serializable {

    private String doctorName;

    private String doctorAge;

    private String doctorSex;

    private String doctorAddress;

    private String doctorCardNumber;

    private String hospitalName;

    private String branchName;

    private String titleName;

    private String headImg;

    private String doctorResume;

    private String doctorStrong;

    private String lableInquiry;

    public void setDoctorDetail(DoctorDetail doctorDetail) {
        this.doctorName = doctorDetail.getDoctorName() == null ? null : doctorDetail.getDoctorName().trim();
        this.doctorAge = doctorDetail.getDoctorAge() == null ? null : doctorDetail.getDoctorAge().trim();
        this.doctorSex = doctorDetail.getDoctorSex() == null ? null : doctorDetail.getDoctorSex().trim();
        this.doctorAddress = doctorDetail.getDoctorAddress() == null ? null : doctorDetail.getDoctorAddress().trim();
        this.doctorCardNumber = doctorDetail.getDoctorCardNumber() == null ? null : doctorDetail.getDoctorCardNumber().trim();
        this.headImg = doctorDetail.getHeadImg() == null ? null : doctorDetail.getHeadImg().trim();
        this.doctorResume = doctorDetail.getDoctorResume() == null ? null : doctorDetail.getDoctorResume().trim();
        this.doctorStrong = doctorDetail.getDoctorStrong() == null ? null : doctorDetail.getDoctorStrong().trim();
        this.lableInquiry = doctorDetail.getLableInquiry() == null ? null : doctorDetail.getLableInquiry().trim();
    }

}
