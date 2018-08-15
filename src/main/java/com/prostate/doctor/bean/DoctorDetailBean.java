package com.prostate.doctor.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.doctor.entity.DoctorDetail;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDetailBean {

    private String headImg; //头像

    private String doctorName; //姓名

    private String doctorSex; //性别

    private String titleName; //职称

    private String hospitalName; //所在医院名称

    private String branchName; //所属科室名称

    private String doctorResume; //简介

    private String doctorStrong; //擅长

    private int fansCount; //粉丝数量

    private int patientCount; //诊疗患者数量

    private int hitsCount; //点击量

    private int picturePrice; //图文问诊费用

    private int phonePrice; //电话问诊费用

    private int videoPrice; //视频会诊费用

    private boolean areFans; //是否已经关注

    @JsonIgnore
    private String hospitalId;

    @JsonIgnore
    private String titleId;

    @JsonIgnore
    private String branchId;



    public void setDoctorDetail(DoctorDetail doctorDetail) {
        this.hospitalId = doctorDetail.getHospitalId() == null ? null : doctorDetail.getHospitalId().trim();
        this.titleId = doctorDetail.getTitleId() == null ? null : doctorDetail.getTitleId().trim();
        this.branchId = doctorDetail.getBranchId() == null ? null : doctorDetail.getBranchId().trim();
        this.headImg = doctorDetail.getHeadImg() == null ? null : doctorDetail.getHeadImg().trim();
        this.doctorName = doctorDetail.getDoctorName() == null ? null : doctorDetail.getDoctorName().trim();
        this.doctorSex = doctorDetail.getDoctorSex() == null ? null : doctorDetail.getDoctorSex().trim();
        this.doctorResume = doctorDetail.getDoctorResume() == null ? null : doctorDetail.getDoctorResume().trim();
        this.doctorStrong = doctorDetail.getDoctorStrong() == null ? null : doctorDetail.getDoctorStrong().trim();

    }

    @Override
    public String toString() {
        return "DoctorDetailBean{" +
                "headImg='" + headImg + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSex='" + doctorSex + '\'' +
                ", titleName='" + titleName + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", doctorResume='" + doctorResume + '\'' +
                ", doctorStrong='" + doctorStrong + '\'' +
                ", fansCount='" + fansCount + '\'' +
                ", patientCount='" + patientCount + '\'' +
                ", hitsCount='" + hitsCount + '\'' +
                ", picturePrice=" + picturePrice +
                ", phonePrice=" + phonePrice +
                ", videoPrice=" + videoPrice +
                ", areFans=" + areFans +
                ", hospitalId='" + hospitalId + '\'' +
                ", titleId='" + titleId + '\'' +
                ", branchId='" + branchId + '\'' +
                '}';
    }
}
