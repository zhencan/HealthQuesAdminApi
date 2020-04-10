package com.example.phq.model;

public class Sms implements Comparable {
    private String phone;       //电话号码
    private String code;        //短信验证码
    private Long time;             //短信生成时间

    public Sms(){}

    public Sms(String phone, String code, Long time){
        this.phone = phone;
        this.code = code;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public int compareTo(Object o) {
        Sms sms = (Sms)o;
        return (int)(getTime() - sms.getTime());
    }

    @Override
    public boolean equals(Object obj) {
        Sms sms = (Sms)obj;
        return (getPhone()).equals(sms.getPhone());
    }

    @Override
    public int hashCode() {
        return (getPhone()).hashCode();
    }
}
