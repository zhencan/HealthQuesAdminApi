package com.example.phq.service;

import com.example.phq.mapper.PhqAdminMapper;
import com.example.phq.pojo.PhqAdmin;
import com.example.phq.pojo.PhqAdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    PhqAdminMapper phqAdminMapper;

    public PhqAdmin getAdmin(int id){
        return phqAdminMapper.selectByPrimaryKey(id);
    }

    public PhqAdmin getAdmin(String id){
        return getAdmin(Integer.parseInt(id));
    }

    public PhqAdmin getAdmin(String ph, String pw){
        PhqAdminExample example = new PhqAdminExample();
        example.or().andPhoneEqualTo(ph).andPasswordEqualTo(pw);
        List<PhqAdmin> list = phqAdminMapper.selectByExample(example);
        if(list.size() == 0)return null;
        return list.get(0);
    }

    public PhqAdmin updateAdmin(PhqAdmin admin){
        PhqAdmin admin2 = getAdmin(admin.getId());
        admin2.setAdminName(admin.getAdminName());
        admin2.setAtUpdate((int)(System.currentTimeMillis()/1000));
        admin2.setImageUrl(admin.getImageUrl());
        int ok = phqAdminMapper.updateByPrimaryKey(admin2);
        if(ok <= 0)return null;
        return admin2;
    }

    public PhqAdmin updateAdminPassword(String adminId, String newPasswd){
        PhqAdmin adminInfo = getAdmin(adminId);
        adminInfo.setPassword(newPasswd);
        adminInfo.setAtUpdate((int)(System.currentTimeMillis()/1000));
        if(phqAdminMapper.updateByPrimaryKey(adminInfo) == 0){
            return null;
        }
        return adminInfo;
    }

    public PhqAdmin updateAdminPhone(String adminId, String newPhone){
        PhqAdmin adminInfo = getAdmin(adminId);
        adminInfo.setPhone(newPhone);
        adminInfo.setAtUpdate((int)(System.currentTimeMillis()/1000));
        if(phqAdminMapper.updateByPrimaryKey(adminInfo) == 0){
            return null;
        }
        return adminInfo;
    }
}
