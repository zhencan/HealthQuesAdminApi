package com.example.phq.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.phq.annotation.JwtVerify;
import com.example.phq.common.response.Result;
import com.example.phq.model.Audience;
import com.example.phq.service.MessageService;
import com.example.phq.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("message")
public class MessageController {
    private static Logger log = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private Audience audience;
    @Autowired
    private MessageService messageService;

    @JwtVerify
    @PostMapping("/publishQuestionnaire")
    public Result publishQuestionnaire(HttpServletRequest request){
        JSONObject inputJson;
        try {
            inputJson = JSONObject.parseObject(IOUtil.InputStream2String(request.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return Result.FAIL();
        }
        messageService.publishQuestionnaire(inputJson);
        return Result.SUCCESS();
    }
}
