package com.example.phq.service;

import com.alibaba.fastjson.JSONObject;
import com.example.phq.mapper.PhqMessageMapper;
import com.example.phq.mapper.PhqUserMapper;
import com.example.phq.pojo.PhqMessage;
import com.example.phq.pojo.PhqUser;
import com.example.phq.pojo.PhqUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private PhqUserMapper phqUserMapper;
    @Autowired
    private PhqMessageMapper messageMapper;

    @Transactional
    public void publishQuestionnaire(JSONObject json){
        int schoolId = json.getIntValue("schoolId");
        int templateId = json.getIntValue("templateId");
        String title = json.getString("title");
        String content = json.getString("content");

        PhqUserExample userExample = new PhqUserExample();
        userExample.or().andSchoolIdEqualTo(schoolId);
        List<PhqUser> userList = phqUserMapper.selectByExample(userExample);
        int time = (int)(System.currentTimeMillis()/1000);
        for(PhqUser user: userList){
            PhqMessage message = new PhqMessage();
            message.setUserId(user.getId());
            message.setQuesTplId(templateId);
            message.setTitle(title);
            message.setContent(content);
            message.setIsOccupy(0);
            message.setAtCreate(time);
            message.setAtUpdate(time);
            messageMapper.insert(message);
        }
    }

}
