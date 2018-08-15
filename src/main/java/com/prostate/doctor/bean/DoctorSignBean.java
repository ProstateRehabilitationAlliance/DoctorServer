package com.prostate.doctor.bean;

import com.prostate.doctor.entity.DoctorSign;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class DoctorSignBean extends DoctorSign implements Serializable {

    private String hospitalName;
    private String branchName;
    private String titleName;
}
