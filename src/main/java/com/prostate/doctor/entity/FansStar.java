package com.prostate.doctor.entity;

import java.io.Serializable;
import java.util.Date;

public class FansStar extends BaseEntity implements Serializable {
    private String id;

    private String fansId;

    private String starId;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFansId() {
        return fansId;
    }

    public void setFansId(String fansId) {
        this.fansId = fansId == null ? null : fansId.trim();
    }

    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId == null ? null : starId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}