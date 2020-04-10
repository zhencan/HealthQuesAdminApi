package com.example.phq.annotation;

import java.lang.annotation.*;

/**
 * ========================
 * JWT验证忽略注解
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/18 9:50
 * Version: v1.0
 * ========================
 * Date: 2020年4月7日 17点26分
 * User: cjf
 * action: 修改为JwtVerify，只有标记此注解才验证JWT
 * ========================
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtVerify {
}
