package com.prostate.doctor.service.impl;

import com.prostate.doctor.entity.WechatUser;
import com.prostate.doctor.mapper.WechatUserMapper;
import com.prostate.doctor.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    private WechatUserMapper wechatUserMapper;

    @Override
    public int insertSelective(WechatUser wechatUser) {
        return 0;
    }

    @Override
    public int updateSelective(WechatUser wechatUser) {
        return 0;
    }

    @Override
    public WechatUser selectById(String id) {
        return wechatUserMapper.selectById(id);
    }

    @Override
    public List<WechatUser> selectByParams(WechatUser wechatUser) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
