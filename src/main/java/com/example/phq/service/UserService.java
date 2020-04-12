package com.example.phq.service;

import com.example.phq.mapper.PhqUserMapper;
import com.example.phq.pojo.PhqUser;
import com.example.phq.pojo.PhqUserExample;
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

    public List<PhqUser> getAllUsers(){
        PhqUserExample example = new PhqUserExample();
        example.or().andIdIsNotNull();
        List<PhqUser> list = phqUserMapper.selectByExample(example);
        return list;
    }
}
