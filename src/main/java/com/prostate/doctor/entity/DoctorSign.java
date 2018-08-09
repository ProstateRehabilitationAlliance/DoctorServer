package com.prostate.doctor.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorSign implements Serializable{

    @Null
    private String id;
    @Null
    private String doctorId;

    @NotNull
    @Size(min=32, max=32,message = "医院ID错误!")
    private String hospitalId;
    @NotNull
    @Size(min=32, max=32,message = "科室ID错误!")
    private String branchId;

    @NotNull
    @Size(min=32, max=32,message = "职称ID错误!")
    private String titleId;
    @NotNull
    @Size(min=50, max=120,message = "身份证正面照片地址错误!")
    private String idCardFront;
    @NotNull
    @Size(min=50, max=120,message = "身份证反面照片地址错误!")
    private String idCardContrary;
    @NotNull
    @Size(min=50, max=120,message = "医师执业证正面照片地址错误!")
    private String doctorCardFront;
    @NotNull
    @Size(min=50, max=120,message = "医师执业证反面照片地址错误!")
    private String doctorCardContrary;
    @NotNull
    @Size(min=50, max=120,message = "手持工牌照片地址错误")
    private String workCard;

    @Null
    private String approveStatus;
    @Null
    private Date createTime;
    @Null
    private String createUser;
    @Null
    private Date updateTime;
    @Null
    private String updateUser;
    @Null
    private Date deleteTime;
    @Null
    private String deleteUser;
    @Null
    private String delFlag;

    @Override
    public String toString() {
        return "DoctorSign{" +
                "id='" + id + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", idCardFront='" + idCardFront + '\'' +
                ", idCardContrary='" + idCardContrary + '\'' +
                ", doctorCardFront='" + doctorCardFront + '\'' +
                ", doctorCardContrary='" + doctorCardContrary + '\'' +
                ", workCard='" + workCard + '\'' +
                ", approveStatus='" + approveStatus + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", deleteTime=" + deleteTime +
                ", deleteUser='" + deleteUser + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}