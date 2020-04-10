package com.example.phq.mapper;

import com.example.phq.pojo.PhqHealthyMessage;
import com.example.phq.pojo.PhqHealthyMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqHealthyMessageMapper {
    long countByExample(PhqHealthyMessageExample example);

    int deleteByExample(PhqHealthyMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqHealthyMessage record);

    int insertSelective(PhqHealthyMessage record);

    List<PhqHealthyMessage> selectByExample(PhqHealthyMessageExample example);

    PhqHealthyMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqHealthyMessage record, @Param("example") PhqHealthyMessageExample example);

    int updateByExample(@Param("record") PhqHealthyMessage record, @Param("example") PhqHealthyMessageExample example);

    int updateByPrimaryKeySelective(PhqHealthyMessage record);

    int updateByPrimaryKey(PhqHealthyMessage record);
}