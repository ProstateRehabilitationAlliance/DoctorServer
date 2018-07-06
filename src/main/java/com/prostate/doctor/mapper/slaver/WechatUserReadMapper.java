package com.prostate.doctor.mapper.slaver;

import com.prostate.doctor.entity.WechatUser;

public interface WechatUserReadMapper extends BaseReadMapper<WechatUser>{

    WechatUser selectByOpenid(String openid);
}