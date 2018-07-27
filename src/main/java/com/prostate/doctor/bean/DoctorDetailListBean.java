package com.prostate.doctor.bean;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class DoctorDetailListBean implements Serializable{

    private String id;

    private String doctorName;

    private String titleId;

    private String hospitalId;

    private String headImg;
}
