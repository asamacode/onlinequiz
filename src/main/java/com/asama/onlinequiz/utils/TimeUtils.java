package com.asama.onlinequiz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static boolean isInHappyHour(String startHour, String endHour) {

        boolean result = false;

        SimpleDateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        Date start = null;
        try {
            start = (Date) hourFormat.parse(startHour);
        } catch (ParseException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        Date end = null;
        try {
            end = (Date) hourFormat.parse(endHour);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date now = new Date(System.currentTimeMillis());

        String nowHourStr = hourFormat.format(now.getTime());

        try {
            Date nowHour = (Date) hourFormat.parse(nowHourStr);
            if (nowHour.after(start) && nowHour.before(end) || (nowHour.equals(start) || (nowHour.equals(end)))) {
                result = true;
            }

        } catch (ParseException e) {
            result = false;
        }

        return result;

    }

    public static boolean invalidSubmit(String dateLimitToSubmit, String currentDate) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date truoc = null;
        try {
            truoc = df.parse(currentDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date sau = null;
        try {
            sau = df.parse(dateLimitToSubmit);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long ConLai = sau.getTime() - truoc.getTime();
        if (ConLai > 0)
            return true;
        return false;

    }

    public static long timeRemain(String dateLimitToSubmit, String currentDate) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date truoc = null;
        try {
            truoc = df.parse(currentDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date sau = null;
        try {
            sau = df.parse(dateLimitToSubmit);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long ConLai = sau.getTime() - truoc.getTime();
        return ConLai;
    }

    public static Date covertStringToDate(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return (Date) df.parse(date);
    }

    public static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        String currentDate = dtf.format((LocalDateTime.now()));
        return currentDate;
    }

    public static String addTime(String currentTime, int minuteAdd) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date d = null;
        try {
            d = df.parse(currentTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, minuteAdd);
        String newTime = df.format(cal.getTime());
        return newTime;
    }

    public static long getMinutes(long timeLimit) {
        return timeLimit / 60;
    }

    public static long getSecondRemain(long timeLimit) {
        long minute = timeLimit / 60;
        return timeLimit - minute * 60;
    }

}
