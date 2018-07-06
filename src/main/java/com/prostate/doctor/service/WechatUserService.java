package com.prostate.doctor.service;

import com.prostate.doctor.entity.WechatUser;
import org.springframework.stereotype.Service;

@Service
public interface WechatUserService extends BaseService<WechatUser>{
    WechatUser selectByOpenid(String openid);
}
