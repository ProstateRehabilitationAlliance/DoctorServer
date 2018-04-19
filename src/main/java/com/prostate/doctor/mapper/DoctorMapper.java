package com.prostate.doctor.mapper;

import com.prostate.doctor.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorMapper {
    int deleteByPrimaryKey(String id);

    int insert(Doctor record);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);

/**
*    @Author: feng
*    @Description: 根据id查盐值
*    @Date:  14:12  2018/4/19
*    @Params:   * @param null
*/
    public List<Doctor> selectByPhone(String id);

/**
*    @Author: feng
*    @Description:  医生更新密码
*    @Date:  17:52  2018/4/19
*    @Params:   * @param null
*/

    public int updDoctorPassword(Doctor doctor);
}