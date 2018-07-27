package com.prostate.doctor.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoctorDetail {
    private String id;

    private String doctorId;

    private String doctorName;

    private String doctorAge;

    private String doctorSex;

    private String doctorAddress;

    private String doctorCardNumber;

    private String hospitalId;

    private String branchId;

    private String titleId;

    private String headImg;

    private String doctorResume;

    private String doctorStrong;

    private String lableInquiry;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private Date deleteTime;

    private String updateUser;

    private String deleteUser;

    private String delFlag;


}