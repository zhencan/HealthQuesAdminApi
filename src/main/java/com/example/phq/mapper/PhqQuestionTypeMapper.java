package com.example.phq.mapper;

import com.example.phq.pojo.PhqQuestionType;
import com.example.phq.pojo.PhqQuestionTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqQuestionTypeMapper {
    long countByExample(PhqQuestionTypeExample example);

    int deleteByExample(PhqQuestionTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqQuestionType record);

    int insertSelective(PhqQuestionType record);

    List<PhqQuestionType> selectByExample(PhqQuestionTypeExample example);

    PhqQuestionType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqQuestionType record, @Param("example") PhqQuestionTypeExample example);

    int updateByExample(@Param("record") PhqQuestionType record, @Param("example") PhqQuestionTypeExample example);

    int updateByPrimaryKeySelective(PhqQuestionType record);

    int updateByPrimaryKey(PhqQuestionType record);
}