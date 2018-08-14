package com.prostate.doctor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDetail extends BaseEntity implements Serializable{

    private String id;

    @JsonIgnore
    private String doctorId;

    private String doctorName;
    @JsonIgnore
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

    @JsonIgnore
    private String lableInquiry;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private String createUser;
    @JsonIgnore
    private Date updateTime;
    @JsonIgnore
    private Date deleteTime;
    @JsonIgnore
    private String updateUser;
    @JsonIgnore
    private String deleteUser;

    @JsonIgnore
    private String delFlag;


    @Override
    public String toString() {
        return "DoctorDetail{" +
                "id='" + id + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorAge='" + doctorAge + '\'' +
                ", doctorSex='" + doctorSex + '\'' +
                ", doctorAddress='" + doctorAddress + '\'' +
                ", doctorCardNumber='" + doctorCardNumber + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", branchId='" + branchId + '\'' +
                ", titleId='" + titleId + '\'' +
                ", headImg='" + headImg + '\'' +
                ", doctorResume='" + doctorResume + '\'' +
                ", doctorStrong='" + doctorStrong + '\'' +
                ", lableInquiry='" + lableInquiry + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                ", updateUser='" + updateUser + '\'' +
                ", deleteUser='" + deleteUser + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}