package com.prostate.doctor.service.impl;

import com.prostate.doctor.entity.FansStar;
import com.prostate.doctor.mapper.master.FansStarWriteMapper;
import com.prostate.doctor.mapper.slaver.FansStarReadMapper;
import com.prostate.doctor.service.FansStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FansStarServiceImpl implements FansStarService {

    @Autowired
    private FansStarWriteMapper fansStarWriteMapper;

    @Autowired
    private FansStarReadMapper fansStarReadMapper;

    @CacheEvict(value = "FANS_STAR", key = "'FANS_STAR_'+#fansStar.getFansId()")
    @Override
    public int insertSelective(FansStar fansStar) {
        return fansStarWriteMapper.insertSelective(fansStar);
    }

    @CacheEvict(value = "FANS_STAR", key = "'FANS_STAR_'+#fansStar.getFansId()")
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

    @CacheEvict(value = "FANS_STAR", key = "'FANS_STAR_'+#fansStar.getFansId()")
    @Override
    public int deleteById(String id) {
        return fansStarWriteMapper.deleteById(id);
    }

    @CacheEvict(value = "FANS_STAR", key = "'FANS_STAR_'+#fansStar.getFansId()")
    @Override
    public int deleteByParams(FansStar fansStar) {
        return fansStarWriteMapper.deleteByParams(fansStar);
    }

    @Override
    public String getFansCount(String doctorId) {
        return fansStarReadMapper.getFansCount(doctorId);
    }

    @Cacheable(value = "FANS_STAR", key = "'FANS_STAR_'+#id", unless = "#result == null")
    @Override
    public Map<String, String> starJson(String id) {
        List<FansStar> fansStarList = fansStarReadMapper.starJson(id);
        if (fansStarList.isEmpty()) {
            return null;
        } else {
            Map<String, String> starMap = new HashMap<>();
            for (FansStar fansStar : fansStarList) {
                starMap.put(fansStar.getStarId(), fansStar.getStarId());
            }
            return starMap;
        }
    }

}
