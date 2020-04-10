package com.example.phq.mapper;

import com.example.phq.pojo.PhqAdmin;
import com.example.phq.pojo.PhqAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqAdminMapper {
    long countByExample(PhqAdminExample example);

    int deleteByExample(PhqAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqAdmin record);

    int insertSelective(PhqAdmin record);

    List<PhqAdmin> selectByExample(PhqAdminExample example);

    PhqAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqAdmin record, @Param("example") PhqAdminExample example);

    int updateByExample(@Param("record") PhqAdmin record, @Param("example") PhqAdminExample example);

    int updateByPrimaryKeySelective(PhqAdmin record);

    int updateByPrimaryKey(PhqAdmin record);
}