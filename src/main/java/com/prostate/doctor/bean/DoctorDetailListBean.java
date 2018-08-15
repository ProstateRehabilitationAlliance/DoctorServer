package com.prostate.doctor.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class DoctorDetailListBean implements Serializable{

    private String id;

    private String doctorName;

    @JsonIgnore
    private String titleId;

    @JsonIgnore
    private String hospitalId;

    private String titleName;

    private String hospitalName;

    private String headImg;

    private boolean areFans;


}
