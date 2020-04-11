package com.example.phq.mapper;

import com.example.phq.pojo.PhqQuestionRadio;
import com.example.phq.pojo.PhqQuestionRadioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqQuestionRadioMapper {
    long countByExample(PhqQuestionRadioExample example);

    int deleteByExample(PhqQuestionRadioExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqQuestionRadio record);

    int insertSelective(PhqQuestionRadio record);

    List<PhqQuestionRadio> selectByExample(PhqQuestionRadioExample example);

    PhqQuestionRadio selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqQuestionRadio record, @Param("example") PhqQuestionRadioExample example);

    int updateByExample(@Param("record") PhqQuestionRadio record, @Param("example") PhqQuestionRadioExample example);

    int updateByPrimaryKeySelective(PhqQuestionRadio record);

    int updateByPrimaryKey(PhqQuestionRadio record);
}