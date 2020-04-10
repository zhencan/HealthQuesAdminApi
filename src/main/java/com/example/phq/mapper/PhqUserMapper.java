package com.example.phq.mapper;

import com.example.phq.pojo.PhqUser;
import com.example.phq.pojo.PhqUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqUserMapper {
    long countByExample(PhqUserExample example);

    int deleteByExample(PhqUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqUser record);

    int insertSelective(PhqUser record);

    List<PhqUser> selectByExample(PhqUserExample example);

    PhqUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqUser record, @Param("example") PhqUserExample example);

    int updateByExample(@Param("record") PhqUser record, @Param("example") PhqUserExample example);

    int updateByPrimaryKeySelective(PhqUser record);

    int updateByPrimaryKey(PhqUser record);
}