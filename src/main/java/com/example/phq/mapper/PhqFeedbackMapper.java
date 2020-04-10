package com.example.phq.mapper;

import com.example.phq.pojo.PhqFeedback;
import com.example.phq.pojo.PhqFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqFeedbackMapper {
    long countByExample(PhqFeedbackExample example);

    int deleteByExample(PhqFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqFeedback record);

    int insertSelective(PhqFeedback record);

    List<PhqFeedback> selectByExample(PhqFeedbackExample example);

    PhqFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqFeedback record, @Param("example") PhqFeedbackExample example);

    int updateByExample(@Param("record") PhqFeedback record, @Param("example") PhqFeedbackExample example);

    int updateByPrimaryKeySelective(PhqFeedback record);

    int updateByPrimaryKey(PhqFeedback record);
}