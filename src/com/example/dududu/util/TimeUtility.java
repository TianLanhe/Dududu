package com.example.dududu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtility {
  
  public static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");// 设置日期格式;
  
  public static String getCurrentDate() {
    Calendar date = Calendar.getInstance();
    int year = date.get(Calendar.YEAR);
    int month = date.get(Calendar.MONTH) + 1;
    int day = date.get(Calendar.DATE);
    String ret = String.format("%04d_%02d_%02d", year, month, day);
    return ret;
  }
  
  public static String getCurrentTime() {
    return df.format(new Date());
  }
}
