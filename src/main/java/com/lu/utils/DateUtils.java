package com.lu.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author lzkj
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 计算两个时间差（小时）
     */
    public static String getMinPoor(Date endDate, Date nowDate) {
        long nm = 1000 * 60 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        Float diff = Float.valueOf(endDate.getTime()) - Float.valueOf(nowDate.getTime());
        // 计算差多少小时
        Float min = diff / nm;
        //构造方法的字符格式这里如果小数不足2位,会以0补足
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        //format 返回的是字符串
        return decimalFormat.format(min);

    }

    /**
     * 获取昨天日期
     * @return date
     */
    public static Date getLastDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        return calendar.getTime();
    }

    /**
     * pass
     * 返回当前月份的开始时间
     * @return Date
     */
    public static Date getCurrentMonthDateBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH,0);
        return calendar.getTime();
    }

    /**
     * pass
     * 返回当前月份的结束时间
     * @return Date
     */
    public static Date getCurrentMonthDateEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,lastDay);
        return calendar.getTime();
    }

    /**
     * pass
     * 返回上月的开始时间
     * @return Date
     */
    public static Date getLastMonthDateBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH,-1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
    }

    /**
     * pass
     * 返回上月的结束时间
     * @return Date
     */
    public static Date getLastMonthDateEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH,0);
        calendar.add(Calendar.DATE,-1);
        return calendar.getTime();
    }

    /**
     * pass
     * 返回当前周的开始时间
     * @return Date
     */
    public static Date getCurrentWeekDateBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dateBegin = calendar.getActualMinimum(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK,dateBegin);
        return calendar.getTime();
    }

    /**
     * pass
     * 返回当前周的结束时间
     * @return Date
     */
    public static Date getCurrentWeekDateEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dateEnd = calendar.getActualMaximum(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK,dateEnd);
        return calendar.getTime();
    }



    /**
     * pass
     * 返回上周的开始时间
     * @return Date
     */
    public static Date getLastWeekDateBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        calendar.set(Calendar.DAY_OF_WEEK,calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
        return calendar.getTime();
    }

    /**
     * pass
     * 返回上周的结束时间
     * @return Date
     */
    public static Date getLastWeekDateEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        calendar.set(Calendar.DAY_OF_WEEK,calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
        return calendar.getTime();
    }

    /**
     * pass
     * 返回当前周第几天的日期
     * 0 为当前周的周日
     * 1 ~ 6 为当前周的周一到周六
     * @return Date
     */
    public static Date getCurrentWeekNumberDay(Date date, int num){
        if (num >= 0 && num <= 6){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_WEEK,calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
            calendar.add(Calendar.DAY_OF_WEEK,num);
            return calendar.getTime();
        }else {
            return null;
        }
    }

    /**
     * pass
     * 返回上一周的当前时间
     * @return Date
     */
    public static Date getLastWeekDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,-7);
        return calendar.getTime();
    }

    /**
     * pass
     * 返回当前季度的开始时间
     * @return Date
     */
    public static Date getCurrentQuarterDateBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int quarter = month / 3 + 1;
        int startMonth = 1;
        if (quarter == 2){
            startMonth = 4;
        }else if(quarter == 3){
            startMonth = 7;
        }else if(quarter == 4){
            startMonth = 10;
        }
        calendar.set(Calendar.MONTH,startMonth - 1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
    }

    /**
     * pass
     * 返回当前季度结束时间
     * @return Date
     */
    public static Date getCurrentQuarterDateEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int quarter = month / 3 + 1;
        int endMonth = 3;
        if (quarter == 2){
            endMonth = 6;
        }else if(quarter == 3){
            endMonth = 9;
        }else if(quarter == 4){
            endMonth = 12;
        }
        calendar.set(Calendar.MONTH,endMonth - 1);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,lastDay);
        return calendar.getTime();
    }

    /**
     * pass
     * 某年某月是星期几
     * @param date date
     * @return  （周日返回0，周一到周六就是1-6）
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 设置
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }


    /**
     * pass
     * 获取日期时间差
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return day
     * @throws ParseException
     */
    public static int getDayDiffer(Date startDate, Date endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
        long endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
    }
}
