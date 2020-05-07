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
import com.example.phq.pojo.PhqUser;
import com.example.phq.service.AdminService;
import com.example.phq.service.SmsService;
import com.example.phq.util.IOUtil;
import com.example.phq.util.JwtTokenUtil;
import com.example.phq.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    Environment environment;

    //项目的父目录
    private String pathProfilePhoto = System.getProperty("user.dir") + "\\static\\profilePhoto\\";

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
        log.info("### 登录成功, token={} ###", token);
        JwtTokenUtil.removeSignoutToken(userId);

        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("userInfo", adminInfo);
        return Result.SUCCESS(result);
    }

    @JwtVerify
    @GetMapping("/signout")
    public Result adminSingout(HttpServletRequest request){
        String adminId = getAdminIdFromToken(request);
        String token = getTokenFromRequest(request);
        JwtTokenUtil.putSingoutToken(adminId, token);
        return Result.SUCCESS();
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

    @JwtVerify
    @PostMapping("/uploadProfilePhoto")
    public Result importUserFromFile(MultipartFile profilePhoto, HttpServletRequest request){
        log.info(pathProfilePhoto);
        log.info(profilePhoto.getOriginalFilename());//获取上传文件的名字
        String OriginaFilename = profilePhoto.getOriginalFilename();
        String profilePhotoName = OriginaFilename.substring(OriginaFilename.lastIndexOf('.'));
        String adminId = getAdminIdFromToken(request);
        profilePhotoName = adminId  + profilePhotoName;
        File localProfilePhoto = new File(pathProfilePhoto,  profilePhotoName);
        File parentFile = localProfilePhoto.getParentFile();
        if(!parentFile.exists() && parentFile.mkdirs()){ //创建父目录
            return Result.FAIL();
        }
        try {
            profilePhoto.transferTo(localProfilePhoto); //保存头像
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Result.FAIL();
        }
        PhqAdmin admin = adminService.getAdmin(adminId);
        admin.setImageUrl(getLocalUrl() + "profilePhoto/" + profilePhotoName);
        admin = adminService.updateAdmin(admin);
        return Result.SUCCESS(admin);
    }

    @GetMapping("/getOwnInfo")
    @JwtVerify
    public Result getOwnInfo(HttpServletRequest request){
        String adminId = getAdminIdFromToken(request);
        PhqAdmin adminInfo = adminService.getAdmin(adminId);
        return Result.SUCCESS(adminInfo);
    }

    private String getTokenFromRequest(HttpServletRequest request){
        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        String token = authHeader.substring(7);
        return token;
    }

    private String getAdminIdFromToken(HttpServletRequest request){
        String token = getTokenFromRequest(request);
        String adminId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());
        return adminId;
    }

    private String getLocalUrl(){
        InetAddress address = null;
        try {
            address = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://"+environment.getProperty("internetAddress") +":"+environment.getProperty("local.server.port") + "/";
    }
}
