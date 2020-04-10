package com.example.phq.service;

import com.example.phq.mapper.PhqUserMapper;
import com.example.phq.pojo.PhqUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    PhqUserMapper phqUserMapper;

    public boolean importUsers(List<PhqUser> list){
        int time = (int)(System.currentTimeMillis()/1000);
        for(PhqUser user: list){
            user.setAtCreate(time);
            user.setAtUpdate(time);
            phqUserMapper.insert(user);
        }
        return true;
    }
}
