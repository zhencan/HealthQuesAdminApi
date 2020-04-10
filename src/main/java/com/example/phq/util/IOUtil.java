package com.example.phq.util;

import com.example.phq.pojo.PhqUser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

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

    public static List<PhqUser> readUserFromExcel(InputStream is){
        Workbook wb = null;
        try{
            wb = WorkbookFactory.create(is);
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }

        List<PhqUser> list = new LinkedList<>();
        Sheet sheet = wb.getSheetAt(0);
        for(int i = 1; i <= sheet.getLastRowNum(); ++i){
            Row row = sheet.getRow(i);

            row.getCell(0).setCellType(CellType.STRING);
            String schoolId = row.getCell(0).getStringCellValue();
            row.getCell(1).setCellType(CellType.STRING);
            String username = row.getCell(1).getStringCellValue();
            row.getCell(2).setCellType(CellType.STRING);
            String password = row.getCell(2).getStringCellValue();
            row.getCell(3).setCellType(CellType.STRING);
            String phone = row.getCell(3).getStringCellValue();
            row.getCell(4).setCellType(CellType.STRING);
            String imageUrl = row.getCell(4).getStringCellValue();

            PhqUser user = new PhqUser();
            user.setSchoolId(Integer.parseInt(schoolId));
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            user.setImageUrl(imageUrl);

            list.add(user);
        }

        return list;
    }
}
