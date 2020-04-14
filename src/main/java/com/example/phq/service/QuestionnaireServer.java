package com.example.phq.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.phq.mapper.PhqHealthyMessageMapper;
import com.example.phq.mapper.PhqQuestionRadioMapper;
import com.example.phq.mapper.PhqQuestionnaireTemplateMapper;
import com.example.phq.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class QuestionnaireServer {
    @Autowired
    PhqQuestionRadioMapper phqQuestionRadioMapper;
    @Autowired
    PhqQuestionnaireTemplateMapper phqQuestionnaireTemplateMapper;
    @Autowired
    PhqHealthyMessageMapper phqHealthyMessageMapper;

    /**
     * 增加问卷
     * @param jsonQuesTemp
     */
    @Transactional
    public boolean addQuestionnaireTemplate(JSONObject jsonQuesTemp){
        int time = (int)System.currentTimeMillis();
        PhqQuestionnaireTemplate template = new PhqQuestionnaireTemplate();
        String templateName = jsonQuesTemp.getString("templateName");
        template.setTemplateName(templateName);
        String templateDescription = jsonQuesTemp.getString("description");
        template.setDescription(templateDescription);
        template.setAtCreate(time);
        template.setAtUpdate(time);

        phqQuestionnaireTemplateMapper.insert(template);
        PhqQuestionnaireTemplateExample templateExample = new PhqQuestionnaireTemplateExample();
        templateExample.or().andTemplateNameEqualTo(templateName).andDescriptionEqualTo(templateDescription).
            andAtCreateEqualTo(time).andAtUpdateEqualTo(time);
        List<PhqQuestionnaireTemplate> templateList = phqQuestionnaireTemplateMapper.selectByExample(templateExample);
        if(templateList.size() == 0)
            return false;
        int quesTplId = templateList.get(0).getId();

        List<PhqQuestionRadio> questionList = new LinkedList<>();
        JSONArray questionJsonArr = jsonQuesTemp.getJSONArray("questions");
        for(int i=0; i<questionJsonArr.size(); ++i){
            JSONObject obj = questionJsonArr.getJSONObject(i);
            PhqQuestionRadio question = new PhqQuestionRadio();
            question.setTypeId(obj.getIntValue("typeId"));
            question.setQuestion((obj.getString("question")));
            JSONArray optionsJson = obj.getJSONArray("options");
            String options = optionsJson.toString();
            question.setOptions(options);
            question.setQuesTplId(quesTplId);
            question.setAtCreate(time);
            question.setAtUpdate(time);
            questionList.add(question);
        }

        for(PhqQuestionRadio question: questionList){
            int ok = phqQuestionRadioMapper.insert(question);
            if(ok != 1)return false;
        }

        return true;
    }

    /**
     * 根据模板id查找所有问题
     * @param templateId
     * @return
     */
    public Map<String, Object> getQuestionnaireAllQuestion(int templateId){
        PhqQuestionnaireTemplate questionnaireTemplate = phqQuestionnaireTemplateMapper.selectByPrimaryKey(templateId);
        PhqQuestionRadioExample example = new PhqQuestionRadioExample();
        example.or().andQuesTplIdEqualTo(templateId);
        List<PhqQuestionRadio> list = phqQuestionRadioMapper.selectByExample(example);
        Map<String, Object> map = new HashMap<>();
        map.put("templateName", questionnaireTemplate.getTemplateName());
        map.put("description", questionnaireTemplate.getDescription());
        map.put("id", templateId);

        List<Map<String, Object>> jsonQuestionList = new LinkedList<>();
        for(PhqQuestionRadio question: list){
            Map<String, Object> questionMap = new HashMap<>();
            questionMap.put("id", question.getId());
            questionMap.put("typeId", question.getTypeId());
            questionMap.put("question", question.getQuestion());
            JSONArray optionsJson = JSONObject.parseArray(question.getOptions());
            questionMap.put("options",  optionsJson);
            jsonQuestionList.add(questionMap);
        }
        map.put("questions", jsonQuestionList);
        return map;
    }

    /**
     * 获取所有模板信息，不包含模板里的问题
     * @return
     */
    public List<PhqQuestionnaireTemplate> getAllTemplate(){
        PhqQuestionnaireTemplateExample example = new PhqQuestionnaireTemplateExample();
        example.or().andIdIsNotNull();
        List<PhqQuestionnaireTemplate> templateList = phqQuestionnaireTemplateMapper.selectByExample(example);
        if(templateList == null){
            return null;
        }
        return templateList;
    }

}
