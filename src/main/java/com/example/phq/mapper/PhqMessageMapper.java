package com.example.phq.mapper;

import com.example.phq.pojo.PhqMessage;
import com.example.phq.pojo.PhqMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqMessageMapper {
    long countByExample(PhqMessageExample example);

    int deleteByExample(PhqMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqMessage record);

    int insertSelective(PhqMessage record);

    List<PhqMessage> selectByExample(PhqMessageExample example);

    PhqMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqMessage record, @Param("example") PhqMessageExample example);

    int updateByExample(@Param("record") PhqMessage record, @Param("example") PhqMessageExample example);

    int updateByPrimaryKeySelective(PhqMessage record);

    int updateByPrimaryKey(PhqMessage record);
}