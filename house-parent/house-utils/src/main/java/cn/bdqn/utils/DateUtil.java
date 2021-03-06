package cn.bdqn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    /**
     * 时间相加
     * @param date
     * @param day
     * @return
     * @throws Exception
     */
    public static String addDate(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
        return dateFormat.format(new Date(time));
    }

    public static Date string2Date(Date date,long day){
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
        return new Date(time);
    }
    /**
     * 时间转字符串
     * @param date
     * @return
     */
    public static String date2String(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // 日期格式
        return dateFormat.format(date);
    }

    /**
     * 时间转字符串
     * @param date
     * @return
     */
    public static String date2String3(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // 日期格式
        return dateFormat.format(date);
    }
    /**
     * 时间转字符串
     * @param date
     * @return
     */
    public static String date2String1(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式
        return dateFormat.format(date);
    }
    /**
     * 时间转字符串
     * @param date
     * @return
     */
    public static String date2StringTime(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm"); // 日期格式
        return dateFormat.format(date);
    }

}
