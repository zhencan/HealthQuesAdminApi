package com.example.phq.controller;

import com.example.phq.common.response.Result;
import com.example.phq.pojo.PhqUser;
import com.example.phq.service.UserService;
import com.example.phq.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/userManagement")
public class UserManagementController {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public Result test(){
        File file = new File("E:\\Documents\\work\\cjfv\\spring_work\\phq\\用户表.xls");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Result.FAIL();
        }
        List<PhqUser> list = IOUtil.readUserFromExcel(fis);
        return Result.SUCCESS(list );
    }

    @PostMapping("/importUserFromFile")
    public Result importUserFromFile(MultipartFile file){
        InputStream is;
        try{
            is = file.getInputStream();
        }catch (IOException e) {
            e.printStackTrace();
            return Result.FAIL("上传失败");
        }
        List<PhqUser> list = IOUtil.readUserFromExcel(is);
        if(list == null)return Result.FAIL();
        boolean ok = userService.importUsers(list);
        if(ok)
            return Result.SUCCESS();
        else return Result.FAIL();
    }
}
