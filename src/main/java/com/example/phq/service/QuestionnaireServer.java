package com.example.phq.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.phq.mapper.PhqQuestionRadioMapper;
import com.example.phq.mapper.PhqQuestionnaireTemplateMapper;
import com.example.phq.pojo.PhqQuestionRadio;
import com.example.phq.pojo.PhqQuestionRadioExample;
import com.example.phq.pojo.PhqQuestionnaireTemplate;
import com.example.phq.pojo.PhqQuestionnaireTemplateExample;
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
        templateExample.or().andTemplateNameEqualTo(templateName).andDescriptionEqualTo(templateDescription);
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
            StringBuilder options = new StringBuilder("");
            //   json: ["1","2"]   ===>  string: "1//2"
            for(int j=0; j<optionsJson.size(); ++j){
                if(j != 0)options.append("//");
                options.append(optionsJson.get(j));
            }
            question.setOptions(options.toString());
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

    @Transactional
    public boolean modifyQuestionnaire(JSONObject jsonQuesTemp){
        int time = (int)System.currentTimeMillis();
        String templateName = jsonQuesTemp.getString("templateName");
        String templateDescription = jsonQuesTemp.getString("description");
        int templateId = jsonQuesTemp.getIntValue("id");
        PhqQuestionnaireTemplate template = phqQuestionnaireTemplateMapper.selectByPrimaryKey(templateId);
        if(templateName != null)template.setTemplateName(templateName);
        if(templateDescription != null)template.setDescription(templateDescription);
        template.setAtUpdate(time);
        phqQuestionnaireTemplateMapper.updateByPrimaryKey(template);

        PhqQuestionRadioExample questionExample = new PhqQuestionRadioExample();
        questionExample.or().andQuesTplIdEqualTo(templateId);
        List<PhqQuestionRadio> preQuestionList = phqQuestionRadioMapper.selectByExample(questionExample);

        JSONArray questJsonArr = jsonQuesTemp.getJSONArray("questions");
        int[] questionIdList = new int[50];
        int questionIdListIndex = 0;
        for(int i=0; i<questJsonArr.size(); ++i){
            PhqQuestionRadio question = new PhqQuestionRadio();
            JSONObject questionJson = questJsonArr.getJSONObject(i);
            Integer questionId = questionJson.getInteger("id");
            if(questionId != null)question.setId(questionId);
            question.setQuesTplId(templateId);

            String questionDescription = questionJson.getString("question");
            question.setQuestion(questionDescription);
            int typeId = Integer.parseInt(questionJson.getString("typeId"));
            question.setTypeId(typeId);

            StringBuilder options = new StringBuilder("");
            JSONArray optionArr = questionJson.getJSONArray("options");
            for(int j=0; j<optionArr.size(); ++j){
                if(j != 0)options.append("//");
                options.append(optionArr.getString(j));
            }
            question.setOptions(options.toString());

            if(questionId != null){
                PhqQuestionRadio preQuestion = phqQuestionRadioMapper.selectByPrimaryKey(questionId);
                question.setAtCreate(preQuestion.getAtCreate());
                question.setAtUpdate(time);
                phqQuestionRadioMapper.updateByPrimaryKey(question);
                questionIdList[questionIdListIndex++] = questionId;
            }else{
                question.setAtCreate(time);
                question.setAtUpdate(time);
                phqQuestionRadioMapper.insert(question);
            }
        }

        Arrays.sort(questionIdList);

        for(PhqQuestionRadio tmpQuestion: preQuestionList){
            if(0 >= Arrays.binarySearch(questionIdList, tmpQuestion.getId())){
                phqQuestionRadioMapper.deleteByPrimaryKey(tmpQuestion.getId());
            }
        }

        return true;
    }

    public Map<String, Object> getQuestionnaire(int questId){
        PhqQuestionnaireTemplate questionnaireTemplate = phqQuestionnaireTemplateMapper.selectByPrimaryKey(questId);
        PhqQuestionRadioExample example = new PhqQuestionRadioExample();
        example.or().andQuesTplIdEqualTo(questId);
        List<PhqQuestionRadio> list = phqQuestionRadioMapper.selectByExample(example);
        Map<String, Object> map = new HashMap<>();
        map.put("templateName", questionnaireTemplate.getTemplateName());
        map.put("description", questionnaireTemplate.getDescription());
        map.put("id", questId);

        List<Map<String, Object>> jsonQuestionList = new LinkedList<>();
        for(PhqQuestionRadio question: list){
            Map<String, Object> questionMap = new HashMap<>();
            questionMap.put("id", question.getId());
            questionMap.put("typeId", question.getTypeId());
            questionMap.put("question", question.getQuestion());
            questionMap.put("options", question.getOptions().split("//", 0));
            jsonQuestionList.add(questionMap);
        }
        map.put("questions", jsonQuestionList);
        return map;
    }
}
