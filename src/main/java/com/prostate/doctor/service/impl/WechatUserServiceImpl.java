package com.prostate.doctor.service.impl;

import com.prostate.doctor.entity.WechatUser;
import com.prostate.doctor.mapper.master.WechatUserWriteMapper;
import com.prostate.doctor.mapper.slaver.WechatUserReadMapper;
import com.prostate.doctor.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    private WechatUserWriteMapper wechatUserWriteMapper;
    @Autowired
    private WechatUserReadMapper wechatUserReadMapper;

    @Override
    public int insertSelective(WechatUser wechatUser) {
        return wechatUserWriteMapper.insertSelective(wechatUser);
    }

    @Override
    public int updateSelective(WechatUser wechatUser) {
        return 0;
    }

    @Override
    public WechatUser selectById(String id) {
        return wechatUserReadMapper.selectById(id);
    }

    @Override
    public List<WechatUser> selectByParams(WechatUser wechatUser) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public WechatUser selectByOpenid(String openid) {
        return wechatUserReadMapper.selectByOpenid(openid);
    }
}
