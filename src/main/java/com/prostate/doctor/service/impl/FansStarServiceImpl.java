package com.prostate.doctor.service.impl;

import com.prostate.doctor.entity.FansStar;
import com.prostate.doctor.mapper.master.FansStarWriteMapper;
import com.prostate.doctor.mapper.slaver.FansStarReadMapper;
import com.prostate.doctor.service.FansStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FansStarServiceImpl implements FansStarService {

    @Autowired
    private FansStarWriteMapper fansStarWriteMapper;

    @Autowired
    private FansStarReadMapper fansStarReadMapper;

    @Override
    public int insertSelective(FansStar fansStar) {
        return fansStarWriteMapper.insertSelective(fansStar);
    }

    @Override
    public int updateSelective(FansStar fansStar) {
        return fansStarWriteMapper.updateSelective(fansStar);
    }

    @Override
    public FansStar selectById(String id) {
        return fansStarReadMapper.selectById(id);
    }

    @Override
    public List<FansStar> selectByParams(FansStar fansStar) {
        return fansStarReadMapper.selectByParams(fansStar);
    }

    @Override
    public int deleteById(String id) {
        return fansStarWriteMapper.deleteById(id);
    }

    @Override
    public int deleteByParams(FansStar fansStar) {
        return fansStarWriteMapper.deleteByParams(fansStar);
    }

    @Override
    public String getFansCount(String doctorId) {
        return fansStarReadMapper.getFansCount(doctorId);
    }

}
