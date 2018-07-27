package com.prostate.doctor.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class UpdateDoctorDetailParams implements Serializable {

    @Size(min = 32, max = 32, message = "ID错误")
    private String id;

    @Size(min = 50, max = 240, message = "头像地址错误!")
    private String headImg;

    @Size(max = 300, message = "简介信息错误")
    private String doctorResume;

    @Size(max = 300, message = "简介信息错误")
    private String doctorStrong;

    @Size(min = 3, max = 3, message = "会诊类型标签错误")
    private String lableInquiry;
}
