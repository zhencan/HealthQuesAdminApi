package com.example.phq.mapper;

import com.example.phq.pojo.PhqQuestion;
import com.example.phq.pojo.PhqQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqQuestionMapper {
    long countByExample(PhqQuestionExample example);

    int deleteByExample(PhqQuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqQuestion record);

    int insertSelective(PhqQuestion record);

    List<PhqQuestion> selectByExample(PhqQuestionExample example);

    PhqQuestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqQuestion record, @Param("example") PhqQuestionExample example);

    int updateByExample(@Param("record") PhqQuestion record, @Param("example") PhqQuestionExample example);

    int updateByPrimaryKeySelective(PhqQuestion record);

    int updateByPrimaryKey(PhqQuestion record);
}