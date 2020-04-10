package com.example.phq.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtil {
    public static String InputStream2String(InputStream is){
        StringBuilder stringBuilder = new StringBuilder();
        char str[] = new char[1000];
        int len_read = 0;
        InputStreamReader isReader = new InputStreamReader(is);
        try{
            while((len_read = isReader.read(str, 0, 1000))!= -1) {
                stringBuilder.append(str, 0, len_read);
            }
        }catch (IOException e){
            e.printStackTrace();
            return new String();
        }
        return stringBuilder.toString();
    }
}
