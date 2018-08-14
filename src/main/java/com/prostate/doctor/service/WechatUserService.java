package com.prostate.doctor.service;

import com.prostate.doctor.entity.WeChatUser;
import org.springframework.stereotype.Service;

@Service
public interface WechatUserService extends BaseService<WeChatUser>{
    WeChatUser selectByOpenid(String openid);
}
