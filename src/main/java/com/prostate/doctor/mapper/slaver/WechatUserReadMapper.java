package com.prostate.doctor.mapper.slaver;

import com.prostate.doctor.entity.WeChatUser;

public interface WechatUserReadMapper extends BaseReadMapper<WeChatUser>{

    WeChatUser selectByOpenid(String openid);
}