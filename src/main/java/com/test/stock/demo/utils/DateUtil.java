package com.test.stock.demo.utils;


import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    /**
     * {@value}
     **/
    public static final String DATE_FMT_A = "yyyy-MM-dd";
    /**
     * {@value}
     **/
    public static final String DATE_FMT_B = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FME_C = "dd/MM/yyyy";
    public static final String DATE_FME_D = "dd-MM-yyyy";
    public static final String DATE_FME_E = "HH:mm:ss";
    /**
     * {@value}
     **/
    public static final String dataFmtCh = "yyyy年MM月dd日";
    /**
     * {@value}
     **/
    public static final String dataTimeFmt = "yyyy-MM-dd HH:mm:ss";
    /**
     * {@value}
     **/
    public static final String FMT_YYYYMMDD = "yyyyMMdd";
    public static final String FMT_YYYYPMMPDD = "yyyy.MM.dd";
    public static final String FMT_YYYY = "yyyy";
    public static final String FMT_MM = "MM";
    public static final String FMT_DD = "dd";
    public static final String FMT_YYYYMM = "yyyyMM";
    public static final String FMT_YYYY_MM = "yyyy-MM";
    public static final String FMT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FMTPYYYYPMM_DD_HH_MM = "yyyy.MM.dd HH:mm";
    public static final String FMT_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String FMT_HH = "HH";

    public static final ZoneId brZone = ZoneId.of("America/Sao_Paulo");
    public static final DateTimeFormatter FORMATTER_A = DateTimeFormatter.ofPattern(DATE_FMT_A);
    public static final DateTimeFormatter FORMATTER_B = DateTimeFormatter.ofPattern(DATE_FMT_B).withZone(brZone);

    /**
     * 日期天数差 (如果d1 < d2 返回负数)
     *
     * @param d1
     * @param d2
     * @return
     */
    public static long dateInterval(Date d1, Date d2) {
        return (d1.getTime() - d2.getTime()) / (1000 * 3600 * 24);
    }

    /**
     * 日期天数差(不包含时分秒), 返回正数
     */
    public static long interval(Date d1, Date d2) {
        return Math.abs(dateInterval(d1, d2));
    }

    /**
     * 将日期字符串装换成 long时间戳
     *
     * @param strDate 日期支持年月日 示例:yyyyMMdd
     * @return 1970年1月1日器日期的毫秒数
     */
    public static long getDateTime(String strDate, String format) {
        return getDateByFormat(strDate, format).getTime();
    }

    /**
     * 日期字符串转为date类型
     *
     * @param strDate 日期字符串
     * @param format  日期格式
     * @return Date
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return (sdf.parse(strDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将date转为指定格式 日期字符串
     *
     * @param date
     * @param pattern 日期格式
     * @return 指定格式字符串
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    /**
     * 字符串日期转换指定格式
     *
     * @param pattern
     * @param date
     * @return
     */
    public static String formatDate(String pattern, String date) {
        if (StringUtils.isNotEmpty(date)) {
            DateFormat fmt = new SimpleDateFormat(dataTimeFmt);
            try {
                Date dateTime = fmt.parse(date);
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                return sdf.format(dateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 获取指定格式的 当前时间
     *
     * @param format
     * @return
     */
    public static String getNowTimeFormat(String format) {
        return formatDate(new Date(), format);
    }

    /**
     * 获取 当前时间，格式 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowTime() {
        return getNowTimeFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * date格式化为 "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将日期字符串从一个格式转为另一个格式字符串
     *
     * @param dateStr
     * @param fromPattern
     * @param toPattern
     * @return
     */
    public static String convertDateStr(String dateStr, String fromPattern, String toPattern) {
        if (StringUtils.isBlank(dateStr)) {
            return "";
        }
        Date date = getDateByFormat(dateStr, fromPattern);
        if (Objects.isNull(date)) {
            return "";
        }
        return formatDate(date, toPattern);
    }

    /**
     * yyyy-MM-dd 转为 dd/MM/yyyy
     *
     * @param dateStr
     * @return
     */
    public static String convertDateStrA(String dateStr) {
        return convertDateStr(dateStr, DATE_FMT_A, DATE_FME_C);
    }

    /**
     * dd/MM/yyyy 转为yyyy-MM-dd
     *
     * @param dateStr
     * @return
     */
    public static String convertDateStrB(String dateStr) {
        return convertDateStr(dateStr, DATE_FME_C, DATE_FMT_A);
    }

    /**
     * 获取从现在到今天结束之间的总秒数
     *
     * @return
     */
    public static int getTillDayEndSec() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime next = now.plusDays(1).withSecond(0);
        return (int) ChronoUnit.SECONDS.between(now, next);
    }

    /**
     * 获取日期为周几
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EE");
        String week = sdf.format(date);
        return week;
    }

    public static Date getNMonthToDate(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, i);
        return c.getTime();
    }

    public static Date getNDayToDate(Date d, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, n);
        return c.getTime();
    }

    /**
     * date转为yyyy-MM-dd
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String formDate2dateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FMT_A);
        return sdf.format(date);
    }

    /**
     * 获取n月后日期 yyyy-MM-dd
     *
     * @param dateStr yyyy-MM-dd 日期
     * @param inc     增加月数
     * @return yyyy-MM-dd
     */
    public static String dateAddMonth(String dateStr, Integer inc) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FMT_A);
        return LocalDateTime.parse(dateStr, format).plusMonths(inc).format(format);
    }

    public static Date dateAddMonth(Date date, int i) {
        ZonedDateTime time = date.toInstant().atZone(ZoneId.systemDefault());
        return Date.from(time.plusMonths(i).toInstant());
    }

    /**
     * 日期加上多少年后的日期
     *
     * @param now
     * @param year
     * @return
     */
    public static Date dateAddYear(Date now, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获取n天后日期 yyyy-MM-dd
     *
     * @param dateStr
     * @param inc
     * @return
     */
    public static String dateAddDays(String dateStr, Integer inc) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FMT_A);
        return LocalDateTime.parse(dateStr, format).plusDays(inc).format(format);
    }

    public static Date dateAddDays(Date date, int n) {
        ZonedDateTime time = date.toInstant().atZone(ZoneId.systemDefault());
        return Date.from(time.plusDays(n).toInstant());
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date timeStamp2Date(String seconds) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FMT_B);
        return new Date(Long.valueOf(seconds));
    }

    /**
     * 返回两个日期相差的天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getIntervalDays(Date fDate, Date oDate) {
        if (null == fDate || null == oDate) {
            return -1;
        }
        long intervalMilli = oDate.getTime() - fDate.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }


    /**
     * 返回当前时间字符串
     */
    public static String getNowStr() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            tsStr = sdf.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

    /**
     * 将指定格式的日期转换为yyyy-MM-dd HH:mm:ss
     *
     * @param pattern
     * @param date
     * @return
     */
    public static String formatDateToSy(String pattern, String date) {
        if (StringUtils.isNotEmpty(date)) {
            DateFormat fmt = new SimpleDateFormat(dataTimeFmt);
            try {
                Date dateTime = fmt.parse(date);
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                return sdf.format(dateTime);
            } catch (ParseException e) {
            }
        }
        return date;
    }
    public static String formateDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parseToDate(String dateTime) {
        DateFormat fmt = new SimpleDateFormat(dataTimeFmt);
        try {
            return fmt.parse(dateTime);
        } catch (ParseException e) {
        }
        return null;
    }

    public static Date parseToDate(String dateTime, String pattern) {
        DateFormat fmt = new SimpleDateFormat(pattern);
        try {
            return fmt.parse(dateTime);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 判断日期是否相同
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSame(Date d1, Date d2) {
        try {
            SimpleDateFormat datef = new SimpleDateFormat("yyyyMMdd");
            return datef.format(d1).equals(datef.format(d2));
        } catch (Exception e) {
        }
        return false;
    }

    public static Date getDayBegin(Date date) {
        try {
            SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            String endTime1 = datef.format(ca.getTime()) + " 00:00:00";
            Date dateEnd = dateFormat.parse(endTime1);

            return dateEnd;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 计算日期加减小时
     *
     * @param datetime yyyy-MM-dd HH:mm:ss
     * @param hours    小时数
     * @return 默认是加
     */
    public static String plusHours(String datetime, int hours) {
        Date date = parseToDate(datetime);
        long millis = TimeUnit.HOURS.toMillis(hours);
        return formateDate(new Date(date.getTime() + millis), "yyyyMMddHHmmss");
    }

    /**
     * format yyyy-MM-dd HH:mm:ss
     *
     * @param type
     * @param number
     * @return
     */
    public static String addTime(int type, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(type, number);
        return formatDate(calendar.getTime());
    }

    /**
     * format yyyy年MM月dd日
     *
     * @param type
     * @param number
     * @param date
     * @return
     */
    public static String addTime(int type, int number, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, number);
        return formateDate(calendar.getTime(), "yyyy年MM月dd日");
    }

    /**
     * format yyyy-MM-dd
     *
     * @param type
     * @param number
     * @param date
     * @return
     */
    public static String addTimeNew(int type, int number, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, number);
        return formateDate(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * @param type
     * @param number
     * @param fromat 格式化
     * @return
     */
    public static String addTime(int type, int number, String fromat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(type, number);
        return formateDate(calendar.getTime(), fromat);
    }

    public static String addTime(int type, String number, String format) {
        return addTime(type, number, format, new Date());
    }

    public static String addTime(int type, String number, String format, Date date) {
        date = date == null ? new Date() : date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, Integer.valueOf(number));
        return formateDate(calendar.getTime(), format);
    }




    public static int compare(Date before, Date end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String beforeStr = sdf.format(before);
        String endStr = sdf.format(end);
        return beforeStr.compareTo(endStr);
    }


    public static int getBetweenDays(Date before, Date after) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            before = sdf.parse(sdf.format(before));
            after = sdf.parse(sdf.format(after));
            Calendar cal = Calendar.getInstance();
            cal.setTime(before);
            long time1 = cal.getTimeInMillis();
            cal.setTime(after);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / 86400000L;
            int result = Integer.parseInt(String.valueOf(between_days));
            return result;
        } catch (Exception var11) {
            return 0;
        }
    }

    public static Date addDateMinut(Date day, int x) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(12, x);
        day = cal.getTime();
        cal = null;
        return day;
    }

    public static Date addDateDay(Date day, int x) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(5, x);
        day = cal.getTime();
        cal = null;
        return day;
    }

    /**
     * 时间前后比较
     * @param after
     * @param before
     * @return
     */
    public static boolean isAfter(Date after,Date before){
        return after.getTime() > before.getTime() ? true :false;
    }

    public static void main(String[] args) {

    }
}
