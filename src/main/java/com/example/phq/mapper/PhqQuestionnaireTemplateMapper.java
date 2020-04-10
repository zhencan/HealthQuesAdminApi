package com.example.phq.mapper;

import com.example.phq.pojo.PhqQuestionnaireTemplate;
import com.example.phq.pojo.PhqQuestionnaireTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhqQuestionnaireTemplateMapper {
    long countByExample(PhqQuestionnaireTemplateExample example);

    int deleteByExample(PhqQuestionnaireTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhqQuestionnaireTemplate record);

    int insertSelective(PhqQuestionnaireTemplate record);

    List<PhqQuestionnaireTemplate> selectByExample(PhqQuestionnaireTemplateExample example);

    PhqQuestionnaireTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhqQuestionnaireTemplate record, @Param("example") PhqQuestionnaireTemplateExample example);

    int updateByExample(@Param("record") PhqQuestionnaireTemplate record, @Param("example") PhqQuestionnaireTemplateExample example);

    int updateByPrimaryKeySelective(PhqQuestionnaireTemplate record);

    int updateByPrimaryKey(PhqQuestionnaireTemplate record);
}