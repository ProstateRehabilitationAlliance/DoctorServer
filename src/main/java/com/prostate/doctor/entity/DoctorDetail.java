package com.prostate.doctor.entity;

import java.util.Date;

public class DoctorDetail {
    private String id;

    private String doctorId;

    private String doctorPhotoPath;

    private String doctorName;

    private String doctorSex;

    private String hospitalDeptId;

    private String doctorType;

    private String doctorIdcard;

    private String doctorIntroduceOneself;

    private String doctorHonour;

    private String doctorPracticingExperience;

    private String doctorEducation;

    private String doctorDiploma;

    private String doctorSpecialty;

    private String doctorSchool;

    private String doctorConsultationFee;

    private String doctorTitleId;

    private Date createTime;

    private Date updateTime;

    private String iosDeviceToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getDoctorPhotoPath() {
        return doctorPhotoPath;
    }

    public void setDoctorPhotoPath(String doctorPhotoPath) {
        this.doctorPhotoPath = doctorPhotoPath == null ? null : doctorPhotoPath.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getDoctorSex() {
        return doctorSex;
    }

    public void setDoctorSex(String doctorSex) {
        this.doctorSex = doctorSex == null ? null : doctorSex.trim();
    }

    public String getHospitalDeptId() {
        return hospitalDeptId;
    }

    public void setHospitalDeptId(String hospitalDeptId) {
        this.hospitalDeptId = hospitalDeptId == null ? null : hospitalDeptId.trim();
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType == null ? null : doctorType.trim();
    }

    public String getDoctorIdcard() {
        return doctorIdcard;
    }

    public void setDoctorIdcard(String doctorIdcard) {
        this.doctorIdcard = doctorIdcard == null ? null : doctorIdcard.trim();
    }

    public String getDoctorIntroduceOneself() {
        return doctorIntroduceOneself;
    }

    public void setDoctorIntroduceOneself(String doctorIntroduceOneself) {
        this.doctorIntroduceOneself = doctorIntroduceOneself == null ? null : doctorIntroduceOneself.trim();
    }

    public String getDoctorHonour() {
        return doctorHonour;
    }

    public void setDoctorHonour(String doctorHonour) {
        this.doctorHonour = doctorHonour == null ? null : doctorHonour.trim();
    }

    public String getDoctorPracticingExperience() {
        return doctorPracticingExperience;
    }

    public void setDoctorPracticingExperience(String doctorPracticingExperience) {
        this.doctorPracticingExperience = doctorPracticingExperience == null ? null : doctorPracticingExperience.trim();
    }

    public String getDoctorEducation() {
        return doctorEducation;
    }

    public void setDoctorEducation(String doctorEducation) {
        this.doctorEducation = doctorEducation == null ? null : doctorEducation.trim();
    }

    public String getDoctorDiploma() {
        return doctorDiploma;
    }

    public void setDoctorDiploma(String doctorDiploma) {
        this.doctorDiploma = doctorDiploma == null ? null : doctorDiploma.trim();
    }

    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }

    public void setDoctorSpecialty(String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty == null ? null : doctorSpecialty.trim();
    }

    public String getDoctorSchool() {
        return doctorSchool;
    }

    public void setDoctorSchool(String doctorSchool) {
        this.doctorSchool = doctorSchool == null ? null : doctorSchool.trim();
    }

    public String getDoctorConsultationFee() {
        return doctorConsultationFee;
    }

    public void setDoctorConsultationFee(String doctorConsultationFee) {
        this.doctorConsultationFee = doctorConsultationFee == null ? null : doctorConsultationFee.trim();
    }

    public String getDoctorTitleId() {
        return doctorTitleId;
    }

    public void setDoctorTitleId(String doctorTitleId) {
        this.doctorTitleId = doctorTitleId == null ? null : doctorTitleId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIosDeviceToken() {
        return iosDeviceToken;
    }

    public void setIosDeviceToken(String iosDeviceToken) {
        this.iosDeviceToken = iosDeviceToken == null ? null : iosDeviceToken.trim();
    }
}