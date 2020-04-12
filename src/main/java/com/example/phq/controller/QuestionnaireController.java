package com.example.phq.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.phq.annotation.JwtVerify;
import com.example.phq.common.response.Result;
import com.example.phq.pojo.PhqQuestionnaireTemplate;
import com.example.phq.service.QuestionnaireServer;
import com.example.phq.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    QuestionnaireServer questionnaireServer;

    @JwtVerify
    @PostMapping("/addQuestionnaireTemplate")
    public Result addQuestionnaireTemplate(HttpServletRequest request){
        JSONObject templateJson;
        try{
            templateJson = JSONObject.parseObject(IOUtil.InputStream2String(request.getInputStream()));
        }catch (IOException e) {
            e.printStackTrace();
            return Result.FAIL();
        }
        boolean ok = questionnaireServer.addQuestionnaireTemplate(templateJson);
        if(!ok)
            return Result.FAIL();
        return Result.SUCCESS();
    }

    @JwtVerify
    @PostMapping("/modifyQuestionnaireTemplate")
    public Result modifyQuestionnaireTemplate(HttpServletRequest request){
        JSONObject templateJson;
        try{
            templateJson = JSONObject.parseObject(IOUtil.InputStream2String(request.getInputStream()));
        }catch (IOException e) {
            e.printStackTrace();
            return Result.FAIL();
        }
        boolean ok = questionnaireServer.modifyQuestionnaire(templateJson);
        if(!ok)
            return Result.FAIL();
        return Result.SUCCESS();
    }

    @JwtVerify
    @PostMapping("/removeTemplateAndQuestion")
    public Result removeTemplateAndQuestion(@RequestBody PhqQuestionnaireTemplate template){
        if(questionnaireServer.removeTemplateAndQuestion(template.getId()))
            return Result.SUCCESS();
        return Result.FAIL();
    }

    @JwtVerify
    @PostMapping("/getQuestionnaireAllQuestion")
    public Result getQuestionnaireAllQuestion(@RequestBody PhqQuestionnaireTemplate template){
        return Result.SUCCESS(questionnaireServer.getQuestionnaireAllQuestion(template.getId()));
    }

    @JwtVerify
    @GetMapping("/getAllTemplate")
    public Result getAllTemplate(){
        List<PhqQuestionnaireTemplate> templateList = questionnaireServer.getAllTemplate();
        if(templateList == null)
            return Result.FAIL();
        return Result.SUCCESS(templateList);
    }
}
