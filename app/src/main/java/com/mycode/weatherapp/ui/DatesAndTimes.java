package com.mycode.weatherapp.ui;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesAndTimes {

    public DatesAndTimes(){ }

    public String getWholeDate(Date date){
        String h;
        String mi;
        String s;
        String mo;
        String d;
        int year = date.getYear()+1900;
        int day = date.getDate();
        int month = date.getMonth()+1;
        if(month == 13){
            month = 1;
        }
        int hour = date.getHours();
        int min = date.getMinutes();
        int seconds = date.getSeconds();
        if(Integer.toString(min).trim().length() == 1){
            String m = Integer.toString(min);
            m = 0+m;
            mi =m;
        }else{
            mi = Integer.toString(min);
        }
        if(Integer.toString(hour).trim().length() == 1){
            String m = Integer.toString(hour);
            m = 0+m;
            h =m;
        }else{
            h = Integer.toString(hour);
        }
        if(Integer.toString(seconds).trim().length() == 1){
            String m = Integer.toString(seconds);
            m = 0+m;
            s =m;
        }else{
            s = Integer.toString(seconds);
        }
        if(Integer.toString(month).trim().length() == 1){
            String m = Integer.toString(month);
            m = 0+m;
            mo =m;
        }else{
            mo = Integer.toString(month);
        }
        if(Integer.toString(day).trim().length() == 1){
            String m = Integer.toString(day);
            m = 0+m;
            d =m;
        }else{
            d = Integer.toString(day);
        }
        String string =year+"-"+mo+"-"+d+"T"+h+":"+mi+":"+s;
        return string;
    }

    public String getTime(String time){
        if(time ==null){
            return "N/A";
        }
        long numTime = Long.parseLong(time);
        String mi;
        String h;
        int hours = new Date(numTime).getHours();
        int minutes = new Date(numTime).getMinutes();
        if(Integer.toString(minutes).trim().length() == 1){
            String m = Integer.toString(minutes);
            m = 0+m;
            mi =m;
        }else{
            mi = Integer.toString(minutes);
        }
        if(Integer.toString(hours).trim().length() == 1){
            String m = Integer.toString(hours);
            m = 0+m;
            h =m;
        }else{
            h = Integer.toString(hours);
        }
        return h+":"+mi;
    }

    public String getMonthDate(Date date){
        int month = date.getMonth()+1;
        if(month ==13){
            month = 1;
        }
        int day = date.getDate();
        return month+"/"+day;
    }
}
