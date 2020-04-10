package com.example.phq.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.example.phq.annotation.JwtVerify;
import com.example.phq.common.response.Result;
import com.example.phq.common.response.ResultCode;
import com.example.phq.model.Audience;
import com.example.phq.model.Sms;
import com.example.phq.pojo.PhqAdmin;
import com.example.phq.service.AdminService;
import com.example.phq.service.SmsService;
import com.example.phq.util.IOUtil;
import com.example.phq.util.JwtTokenUtil;
import com.example.phq.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/18 10:41
 * Version: v1.0
 * ========================
 */
@Slf4j
@RestController
@RequestMapping("admin")
public class AdminUserController {
    private static Logger log = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private Audience audience;
    @Autowired
    private AdminService adminService;
    @Autowired
    private SmsService smsService;

    @PostMapping("/login")
    public Result adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject input = JSONObject.parseObject(IOUtil.InputStream2String(request.getInputStream()));
        String phone = input.getString("phone");
        String password = input.getString("password");

        PhqAdmin adminInfo = adminService.getAdmin(phone, password);
        if(adminInfo == null){
            return Result.FAIL("登录失败");
        }

        // 这里模拟测试, 默认登录成功，返回用户ID和角色信息
//        String userId = UUID.randomUUID().toString();
        String userId = ""+adminInfo.getId();
        String role = "admin";

        // 创建token
        String token = JwtTokenUtil.createJWT(userId, adminInfo.getAdminName(), role, audience);
        System.out.println("adminId============"+JwtTokenUtil.getUserId(token, audience.getBase64Secret()));
        log.info("### 登录成功, token={} ###", token);

        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("userInfo", adminInfo);
        return Result.SUCCESS(result);
    }

    @GetMapping("/users")
    public Result userList() {
        log.info("### 查询所有用户列表 ###");
        return new Result(smsService.sendSmsCode("15626257906"));
//        SmsUtil.putSmsCodeQ(new Sms("15626257906", "123123", System.currentTimeMillis()), /*10*60*1000L*/10000000);
//        return Result.SUCCESS();
    }

    @JwtVerify
    @PostMapping("/sendCode")
    public Result sendCode4ModifyPassword(HttpServletRequest request, @RequestBody PhqAdmin adminInfo){
        String validPhone = adminInfo.getPhone();
        String adminId = getAdminIdFromToken(request);
        PhqAdmin admin = adminService.getAdmin(adminId);
        String phone = admin.getPhone();

        if(phone == null)
            return new Result(ResultCode.PARAM_IS_BLANK);
        if( !phone.equals(validPhone) )
            return new Result(ResultCode.SMS_PHONE_NUMBER_INVAILD);

        ResultCode resultCode = smsService.sendSmsCode(phone);
        return new Result(resultCode);
    }

    @JwtVerify
    @PostMapping("/verifyCodeAndModifyPassword")
    public Result verifyCodeAndModifyPassword(HttpServletRequest request) throws IOException {
        JSONObject input;
        try{
            input = JSONObject.parseObject(IOUtil.InputStream2String(request.getInputStream()));
        }catch(IOException e){
            e.printStackTrace();
            return Result.FAIL();
        }
        String adminId = getAdminIdFromToken(request);
        PhqAdmin oldAdminInfo = adminService.getAdmin(adminId);
        String phone = oldAdminInfo.getPhone();
        String code = input.getString("code");
        String newPasswd = input.getString("password");
        ResultCode resultCode = smsService.validSmsCode(phone, code);
        if(ResultCode.SUCCESS  != resultCode){
            return new Result(resultCode);
        }

        PhqAdmin resultAdmin = adminService.updateAdminPassword(adminId, newPasswd);
        if(resultAdmin == null){
            return Result.FAIL();
        }
        return Result.SUCCESS(resultAdmin);
    }

    @JwtVerify
    @PostMapping("/verifyCodeAndModifyPhone")
    public Result verifyCodeAndModifyPhone(HttpServletRequest request) throws IOException {
        JSONObject input;
        try{
            input = JSONObject.parseObject(IOUtil.InputStream2String(request.getInputStream()));
        }catch(IOException e){
            e.printStackTrace();
            return Result.FAIL();
        }
        String adminId = getAdminIdFromToken(request);
        PhqAdmin oldAdminInfo = adminService.getAdmin(adminId);
        String phone = oldAdminInfo.getPhone();
        String code = input.getString("code");
        String newPhone = input.getString("phone");
        ResultCode resultCode = smsService.validSmsCode(phone, code);
        if(ResultCode.SUCCESS  != resultCode){
            return new Result(resultCode);
        }

        PhqAdmin resultAdmin = adminService.updateAdminPhone(adminId, newPhone);
        if(resultAdmin == null){
            return Result.FAIL();
        }
        return Result.SUCCESS(resultAdmin);
    }

    @PostMapping("/updateOwnInfo")
    @JwtVerify
    public Result updateOwnInfo(HttpServletRequest request, @RequestBody PhqAdmin admin){
        String adminId = getAdminIdFromToken(request);
        admin.setId(Integer.parseInt(adminId));
        admin = adminService.updateAdmin(admin);
        if(admin == null){
            return Result.FAIL("更新失败");
        }
        return Result.SUCCESS(admin);
    }

    @GetMapping("/getOwnInfo")
    @JwtVerify
    public Result getOwnInfo(HttpServletRequest request){
        String adminId = getAdminIdFromToken(request);
        PhqAdmin adminInfo = adminService.getAdmin(adminId);
        return Result.SUCCESS(adminInfo);
    }

    private String getAdminIdFromToken(HttpServletRequest request){
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7);
        String adminId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());
        return adminId;
    }
}
