package com.example.phq.util;

import com.example.phq.model.Sms;
import com.example.phq.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SmsUtil {
    private static Logger log = LoggerFactory.getLogger(SmsUtil.class);

    private static PriorityQueue<SmsAndTimeout> smsCodeQ;
    private static Map<String, SmsAndTimeout> sms2PhoneMap;
//    private static Timer smsCodeTimer;
    private static Thread thTimer;
    static{
        smsCodeQ = new PriorityQueue<SmsAndTimeout>();
        sms2PhoneMap = new HashMap<String, SmsAndTimeout>();
//        smsCodeTimer = new Timer();
//        smsCodeTimer.schedule(new UpdateTimer(), 0);
        thTimer = new Thread(new UpdateTimer(), "UpdateTimer");
        thTimer.start();
    }

    public static synchronized boolean putSmsCodeQ(Sms sms, long delay){
        SmsAndTimeout st = new SmsAndTimeout();
        st.sms = sms;
        st.timeout = sms.getTime() + delay;
        int preSize = smsCodeQ.size();
        boolean timeOK = smsCodeQ.add(st);
        if(!timeOK)return false;
        sms2PhoneMap.put(sms.getPhone(), st);
        if(preSize == 0){
            SmsUtil.class.notify();
        }
        log.info("putSmsCodeQ : " + sms.getPhone() + " code:" + sms.getCode() + "\t" + "Q容量:" + smsCodeQ.size());
        return true;
    }

    public static synchronized Sms getOneOfSmsCodeQ(String phone){
        SmsAndTimeout st = sms2PhoneMap.get(phone);
        if(st == null)return null;
        return st.sms;
    }

    public static synchronized boolean deleteOneOfSmsCodeQ(String phone){
        SmsAndTimeout st = sms2PhoneMap.get(phone);
        if(!smsCodeQ.remove(st))return false;
        if(null == sms2PhoneMap.remove(phone))return false;
        log.info("deleteOneOfSmsCodeQ : " + st.sms.getPhone() + "\t" + st.sms.getCode()+ "\t" + "Q容量:" + smsCodeQ.size());
        return true;
    }

    private static class UpdateTimer implements Runnable {
        @Override
        public void run() {
            long delay = 0;
            log.info("UpdateTimer  start");
            SmsAndTimeout st = null;
            while(true){
                synchronized (SmsUtil.class) {
                    while ((st = smsCodeQ.peek()) == null) {
                        try {
                            log.info("UpdateTimer wait........... \t" + "Q容量:" + smsCodeQ.size());
                            SmsUtil.class.wait();
                            log.info("UpdateTimer wake up........... ");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (st != null && st.timeout <= System.currentTimeMillis()) {
                        smsCodeQ.poll();
                        sms2PhoneMap.remove(st.sms.getPhone());
                        log.info("smsCodeTimer poll : " + st.sms.getPhone() + " code:" + st.sms.getCode()+ "\t" + "Q容量:" + smsCodeQ.size());
                        st = smsCodeQ.peek();
                    }
                    if (st != null) {
                        delay = st.timeout - System.currentTimeMillis();
                    }
                }
                try {
                    if(st != null)
                        Thread.currentThread().sleep(delay);
                }catch(Exception e){}
            }
        }
    }

    private static class SmsAndTimeout implements Comparable{
        public Sms sms;
        public long timeout;

        @Override
        public int compareTo(Object o) {
            SmsAndTimeout st = (SmsAndTimeout)o;
            return (int)(this.timeout - st.timeout);
        }

        @Override
        public boolean equals(Object obj) {
            SmsAndTimeout st = (SmsAndTimeout)obj;
            return this.sms.equals(st.sms);
        }
    }
}
