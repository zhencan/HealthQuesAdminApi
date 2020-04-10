package com.example.phq.mapper;

import com.example.phq.pojo.PhqSchool;
import com.example.phq.pojo.PhqSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqSchoolMapper {
    long countByExample(PhqSchoolExample example);

    int deleteByExample(PhqSchoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqSchool record);

    int insertSelective(PhqSchool record);

    List<PhqSchool> selectByExample(PhqSchoolExample example);

    PhqSchool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqSchool record, @Param("example") PhqSchoolExample example);

    int updateByExample(@Param("record") PhqSchool record, @Param("example") PhqSchoolExample example);

    int updateByPrimaryKeySelective(PhqSchool record);

    int updateByPrimaryKey(PhqSchool record);
}