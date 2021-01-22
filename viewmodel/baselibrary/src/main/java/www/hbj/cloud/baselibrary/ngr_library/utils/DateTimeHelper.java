package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间操作
 *
 * @author : zw
 */
public class DateTimeHelper {
    public static final String MONTH_DAY_FORMAT = "M月d日";
    public static final String[] WEEK_OF_DAYS = {"星期日", "星期一", "星期二", "星期三",
            "星期四", "星期五", "星期六"};
    public static final String[] WEEK_OF_DAYS2 = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    public static final String YYYY_NIAN_MM_YUE_DDHHMM = "yyyy年MM月dd日 HH:mm";
    public static final String MD_HHMM = "M/d HH:mm";
    public static final String MM_YUE_DD = "MM月dd日";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String CHATTIME_FORMAT = "yy-MM-dd";
    public static final String YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String HOURTIME_FORMAT = "kk:mm";
    public static final String FORMAT_HHMM = "HH:mm";
    public static final String YYYYNIANMMYUEDD = "yyyy年MM月dd日";
    public static final String ENGLISH_MMMDDYYYY = "MMM dd, yyyy";//Apr 25, 2016


    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String mTag = "DateTimeHelper";


    /************************************************************************************/
    /************************************************************************************/
    /************************************当前时间获取的函数********************************/
    /************************************************************************************/
    /************************************************************************************/
    /**
     * 获取当前时间字符串
     *
     * @return
     */
    public static String getDateTime() {
        return (String) DateFormat.format("yyyy-MM-dd kk:mm:ss",
                Calendar.getInstance());
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getDate() {
        return getDate(Calendar.getInstance());
    }

    /************************************************************************************/
    /************************************************************************************/
    /************************获取年月日时分秒等单项（返回值为int）**************************/
    /*************************************************************************************/
    /************************************************************************************/

    /**
     * 获取秒
     *
     * @return
     */
    public static int getSeconds() {
        Date dt = new Date();
        return dt.getSeconds();
    }

    /**
     * 获取某个日期对应的秒数
     *
     * @param date
     * @return
     */
    public static int getSeconds(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(YYMMDDHHMMSS);
            Date dt;
            dt = format.parse(date);
            return dt.getSeconds();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取某个日期对应的分
     *
     * @param date
     * @return
     */
    public static int getMinutes(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date dt;
            dt = format.parse(date);
            return dt.getMinutes();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取某个日期对应的小时
     *
     * @param date
     * @return
     */
    public static int getHours(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date dt;
            dt = format.parse(date);
            return dt.getHours();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取当前年
     *
     * @return
     */
    public static int getYear() {
        Calendar c = Calendar.getInstance();
        return getYear(c);
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getYear(Calendar c) {
        return c.get(Calendar.YEAR);
    }


    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMonth() {
        return getMonth(getCalendar());
    }

    /**
     * 获取所在月份
     *
     * @return
     */
    public static int getMonth(Calendar c) {
        return c.get(Calendar.MONTH);
    }

    /**
     * 获取所在月份
     *
     * @param date
     * @return
     */
    public static int getMonth(String date) {
        try {
            Date dt = getDate(date);
            if (null == dt) {
                return getMonth();
            }
            Calendar cal = getCalendar(dt);
            return getMonth(cal);
        } catch (Exception e) {
            Log.e(mTag, "Exception", e);
        }
        return getMonth();
    }


    /**
     * 得到这个月的第多少天
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar c = Calendar.getInstance();
        return getDayOfMonth(c);
    }

    /**
     * 得到传入参数所在月的第多少天
     *
     * @param c
     * @return
     */
    public static int getDayOfMonth(Calendar c) {
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到传入参数所在月的第多少天
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(String date) {
        try {
            Date dt = getDate(date);

            if (null == dt) {
                return getDayOfMonth();
            }
            Calendar cal = getCalendar(dt);
            return getDayOfMonth(cal);
        } catch (Exception e) {
            Log.e(mTag, "Exception", e);
        }
        return getDayOfMonth();
    }


    /*********************************************************************************/
    /*********************************************************************************/
    /**********************日期格式之间的转换函数****************************************/
    /**************（传入Date或者Calendar，返回值为日期String格式）***********************/
    /*********************************************************************************/
    /*********************************************************************************/
    /**
     * 获取日期
     *
     * @param dt
     * @return
     */
    public static String getDate(Date dt) {
        return (String) DateFormat.format(YYYYMMDD, dt);
    }

    /**
     * 将Date类型转换为对应格式的String类型
     *
     * @param dt
     * @param inFormat
     * @return
     */
    public static String getFormatDate(Date dt, CharSequence inFormat) {
        return (String) DateFormat.format(inFormat, dt);
    }

    /**
     * 将Date类型转换为对应格式的String类型
     *
     * @param dt
     * @return
     */
    public static String getChatDate(Date dt) {
        return (String) DateFormat.format(CHATTIME_FORMAT, dt);
    }

    /**
     * 将Date类型转换为对应格式的String类型
     *
     * @param dt
     * @return
     */
    public static String getHour(Date dt) {
        return (String) DateFormat.format(HOURTIME_FORMAT, dt);
    }

    /**
     * 获取某个日期
     *
     * @param c
     * @return
     */
    public static String getDate(Calendar c) {
        return (String) DateFormat.format(YYYYMMDD, c);
    }


    /**
     * 环信使用的格式
     *
     * @param date
     * @return
     */
    public static String getEaseDateString(Date date) {
        Calendar today = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);
        int y1;
        int y2 = 0;
        y1 = today.get(Calendar.YEAR);
        y2 = c2.get(Calendar.YEAR);

        if (y2 != y1) {
            return format(date, YYYY_NIAN_MM_YUE_DDHHMM);
        } else {
            int dayofYear = today.get(Calendar.DAY_OF_YEAR);
            int dayofYear2 = c2.get(Calendar.DAY_OF_YEAR);
            if (dayofYear != dayofYear2) {
                return format(date, MM_YUE_DD);
            } else {
                return format(date, FORMAT_HHMM);
            }
        }

    }

    /**
     * 转换为随访时间格式
     * e.g.（2013年2月2日）
     *
     * @param date
     * @return
     */
    public static String toSystemFriendlyTime3(Date date) {
        String sdate = null;
        try {
            String dateString = format(date, DateTimeHelper.YYYYNIANMMYUEDD);
            String timeleft = dateString.substring(5, 10);

            String str1 = timeleft.substring(0, 2);
            String str2 = timeleft.substring(3, 5);

            if (str1.substring(0, 1).equals("0")) {
                str1 = str1.substring(1, 2);
            } else {
                str1 = str1.substring(0, 2);
            }

            if (str2.substring(0, 1).equals("0")) {
                str2 = str2.substring(1, 2);
            } else {
                str2 = str2.substring(0, 2);
            }
            sdate = dateString.substring(0, 5) + str1 + "月" + str2 + "日";
        } catch (Exception e) {
            e.printStackTrace();
            return sdate;
        }
        return sdate;

    }

    /*********************************************************************************/
    /*********************************************************************************/
    /**********************日期格式之间的转换函数****************************************/
    /********************（传入String，返回值为日期Date或者Calendar格式）*****************/
    /*********************************************************************************/
    /*********************************************************************************/

    public static Date getDate(String date) {
        return getDate(date, YYYYMMDD);
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 将Date类型转换为Calendar类型
     *
     * @param date
     * @return
     */
    public static Calendar getCalendar(Date date) {
        Calendar cal = getCalendar();
        cal.setTime(date);
        return cal;
    }


    public static Date getDate(String date, String format) {
        try {
            if (null == date || date.isEmpty()) {
                return null;
            }
            if (null == format || format.isEmpty()) {
                format = YYYYMMDD;
            }
            SimpleDateFormat ft = new SimpleDateFormat(format);
            return ft.parse(date);
        } catch (ParseException e) {
            Log.e(mTag, "Exception getDate date:" + date, e);
        } catch (Exception e) {
            Log.e(mTag, "Exception getDate date:" + date, e);
        }
        return null;
    }


    /**
     * 根据传入的年月日返回诸如“2015-06-23”这样格式的字串
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return “2015-06-23”这样格式的字串
     */
    public static String getDate(int year, int monthOfYear, int dayOfMonth) {
        String split = "-";
        String time = year + split;
        if (10 > monthOfYear) {
            time = time + "0" + monthOfYear;
        } else {
            time += monthOfYear;
        }
        time += split;
        if (10 > dayOfMonth) {
            time = time + "0" + dayOfMonth;
        } else {
            time += dayOfMonth;
        }
        return time;
    }

    /**
     * 转换日期字符串的格式
     *
     * @param date
     * @param oldpattern
     * @param newpattern
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String format(String date, String oldpattern,
                                String newpattern) {
        SimpleDateFormat df = new SimpleDateFormat(oldpattern);
        Date dt;
        try {
            dt = df.parse(date);
            return format(dt, newpattern);
        } catch (ParseException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据传入的pattern格式化当前日期
     *
     * @param pattern
     * @return 格式化之后的String类型
     */
    public static String format(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date date = new Date();
        return df.format(date);
    }


    /**
     * 根据传入的pattern格式化Date类型
     *
     * @param date
     * @param pattern
     * @return 格式化之后的String类型
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }


    /**
     * 返回Date类型
     *
     * @param dateStr 支持三种格式的输入字符串
     * @return Date值
     */
    public static Date getDateFromString(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        final String trimStr = dateStr.trim();
        SimpleDateFormat df = null;
        switch (trimStr.length()) {
            case 19:
                df = new SimpleDateFormat(YYMMDDHHMMSS);
                try {
                    return df.parse(trimStr);

                } catch (ParseException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;

            case 16:
                df = new SimpleDateFormat(YYYMMDDHHMM);
                try {
                    return df.parse(trimStr);

                } catch (ParseException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            case 10:
                df = new SimpleDateFormat(YYYYMMDD);
                try {
                    return df.parse(trimStr);

                } catch (ParseException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            case 12:
                df = new SimpleDateFormat(ENGLISH_MMMDDYYYY, Locale.ENGLISH);
                try {
                    return df.parse(trimStr);

                } catch (ParseException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            default:
                return null;
        }
    }


    /*********************************************************************************/
    /*********************************************************************************/
    /**********************日期格式之间的转换函数****************************************/
    /********************（传入String，返回值为指定格式的String）************************/
    /*********************************************************************************/
    /*********************************************************************************/


    /**
     * 转换为几分钟前这样的格式
     *
     * @param sdate
     * @return “几分钟前”、“几小时前”、“昨天”、“前天”、“N天前”、“2016-01-01”
     */
    public static String friendlyTime(String sdate, String format) {
        SimpleDateFormat dateFormater2 = new SimpleDateFormat(format);

        Date time;

        try {
            time = dateFormater2.parse(sdate);
        } catch (ParseException e) {
            LogUtils.e(e.getMessage());
            return null;
        }
        if (time == null) {
            return "";
        }

        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.format(cal.getTime());
        String paramDate = dateFormater2.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.format(time);
        }
        return ftime;
    }


    /**
     * 转换为应用时间格式
     * e.g.（几分钟前...2月2日 10:33）
     *
     * @param sdate
     * @param format
     * @return
     */
    public static String toSystemFriendlyTime1(String sdate, String format) {
        format = "yyyy-MM-dd HH:mm";
        if (sdate.length() >= 19) {
            sdate = sdate.substring(0, sdate.length() - 3);
        }
        SimpleDateFormat dateFormater = new SimpleDateFormat(format);

        Date time;

        try {
            time = dateFormater.parse(sdate);
        } catch (ParseException e) {
            return null;
        }
        if (time == null) {
            return "";
        }

        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater.format(cal.getTime());
        String paramDate = dateFormater.format(time);
        String sdatetime = paramDate.substring(paramDate.length() - 5,
                paramDate.length());
        String nowday = curDate.substring(0, 10);
        String sdateday = paramDate.substring(0, 10);
        if (nowday.equals(sdateday)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }

        double lt = time.getTime() * 1.0 / 86400000;
        double ct = cal.getTimeInMillis() * 1.0 / 86400000;
        int days = (int) (ct - lt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        int day1 = Integer.parseInt(date.substring(date.length() - 2,
                date.length()));
        int day2 = Integer.parseInt(sdate.substring(8, 10));
        if (days >= 2 || (day1 - day2) != 1) {
            String stime = paramDate.substring(5, paramDate.length());
            String timeleft = stime.substring(0, 5);
            String str1 = timeleft.substring(0, 2);
            String str2 = timeleft.substring(3, 5);

            if (str1.substring(0, 1).equals("0")) {
                str1 = str1.substring(1, 2);
            } else {
                str1 = str1.substring(0, 2);
            }

            if (str2.substring(0, 1).equals("0")) {
                str2 = str2.substring(1, 2);
            } else {
                str2 = str2.substring(0, 2);
            }
            timeleft = str1 + "月" + str2 + "日";
            String timeright = stime.substring(stime.length() - 6,
                    stime.length());
            ftime = timeleft + timeright;
            return ftime;
        } else {
            if (day1 - day2 == 1) {
                ftime = "昨天" + sdatetime;
            }

        }
        return ftime;
    }

    public static String toSystemFriendlyTime3(String sdate) {
        if (sdate.length() > 12) {
            String str1 = sdate.substring(5, 7);
            String str2 = sdate.substring(8, 10);
            return Integer.parseInt(str1) + "月" + Integer.parseInt(str2) + "日";
        }
        return sdate;
    }


    /**
     * 转换为应用时间格式
     * e.g.（2月2日 10:33）
     *
     * @param sdate
     * @return
     */
    public static String toSystemFriendlyTime2(String sdate) {
        if (TextUtil.isNull(sdate)) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (sdate.length() >= 19) {
                sdate = sdate.substring(0, sdate.length() - 3);
            }
            dateFormat.parse(sdate);
            sdate = sdate.substring(5, sdate.length());
            String timeleft = sdate.substring(0, 5);

            String str1 = timeleft.substring(0, 2);
            String str2 = timeleft.substring(3, 5);

            if (str1.substring(0, 1).equals("0")) {
                str1 = str1.substring(1, 2);
            } else {
                str1 = str1.substring(0, 2);
            }

            if (str2.substring(0, 1).equals("0")) {
                str2 = str2.substring(1, 2);
            } else {
                str2 = str2.substring(0, 2);
            }
            timeleft = str1 + "月" + str2 + "日";
            String timeright = sdate.substring(sdate.length() - 6, sdate.length());
            sdate = timeleft + timeright;
        } catch (Exception e) {
            e.printStackTrace();
            return sdate;
        }
        return sdate;

    }

    /**
     * e.g.（2-2 10:33-10:44）
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static String toSystemFriendlyTime6(String startDate, String endDate) {
        String time = "";
        if (TextUtil.isNull(startDate) || TextUtil.isNull(endDate)) {
            return time;
        }
        String preFix = toSystemFriendlyTime4(startDate);
        String suffix = toSystemFriendlyTime4(endDate);
        suffix = suffix.substring(suffix.length() - 5);
        time = preFix + "-" + suffix;
        return time;
    }


    /**
     * 转换为应用时间格式
     * e.g.（2-2 10:33）
     *
     * @param sdate
     * @return
     */
    public static String toSystemFriendlyTime4(String sdate) {
        if (TextUtil.isNull(sdate)) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (sdate.length() >= 19) {
                sdate = sdate.substring(0, sdate.length() - 3);
            }
            dateFormat.parse(sdate);
            sdate = sdate.substring(5, sdate.length());
            String timeleft = sdate.substring(0, 5);

            String str1 = timeleft.substring(0, 2);
            String str2 = timeleft.substring(3, 5);

            if (str1.substring(0, 1).equals("0")) {
                str1 = str1.substring(1, 2);
            } else {
                str1 = str1.substring(0, 2);
            }

            if (str2.substring(0, 1).equals("0")) {
                str2 = str2.substring(1, 2);
            } else {
                str2 = str2.substring(0, 2);
            }
            timeleft = str1 + "-" + str2;
            String timeright = sdate.substring(sdate.length() - 6, sdate.length());
            sdate = timeleft + timeright;
        } catch (Exception e) {
            e.printStackTrace();
            return sdate;
        }
        return sdate;

    }

    /**
     * 转换为应用时间格式
     * e.g.（2016-2-2 10:33）
     *
     * @param sdate
     * @return
     */
    public static String toSystemFriendlyTime5(String sdate) {
        if (TextUtil.isNull(sdate)) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (sdate.length() >= 19) {
                sdate = sdate.substring(0, sdate.length() - 3);
            }
            dateFormat.parse(sdate);
            String str0 = sdate.substring(0, 5);
            sdate = sdate.substring(5, sdate.length());
            String timeleft = sdate.substring(0, 5);

            String str1 = timeleft.substring(0, 2);
            String str2 = timeleft.substring(3, 5);

            if (str1.substring(0, 1).equals("0")) {
                str1 = str1.substring(1, 2);
            } else {
                str1 = str1.substring(0, 2);
            }

            if (str2.substring(0, 1).equals("0")) {
                str2 = str2.substring(1, 2);
            } else {
                str2 = str2.substring(0, 2);
            }
            timeleft = str0 + str1 + "-" + str2;
            String timeright = sdate.substring(sdate.length() - 6, sdate.length());
            sdate = timeleft + timeright;
        } catch (Exception e) {
            e.printStackTrace();
            return sdate;
        }
        return sdate;

    }


    /**
     * 获取如下时间格式12:10 (HH:mm)
     *
     * @param dateString Date字符串
     * @param format     Date字符串的格式，比如"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getHourAndMinute(String dateString, String format) {
        try {
            if (TextUtil.isNull(dateString)) {
                return "";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date dt;
            dt = dateFormat.parse(dateString);
            int hours = dt.getHours();
            int minutes = dt.getMinutes();

            return (hours == 0 ? "00" : dt.getHours()) + ":" + (minutes == 0 ? "00" : dt.getMinutes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /******************************************************************************/
    /******************************************************************************/
    /**********************日期之间的比较大小****************************************/
    /********************给定某个偏移值后计算偏移后的日期******************************/
    /**********************计算两个日期之间的间隔天数*********************************/
    /******************************************************************************/
    /******************************************************************************/


    /**
     * 比较两个日期先后
     *
     * @param c1
     * @param c2
     * @return 如果c1在c2后或者c1等于c2，返回true，否则返回false
     */
    public static boolean isSameOrLaterrDay(Calendar c1, Calendar c2) {
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);

        int day1 = c1.get(Calendar.DAY_OF_YEAR);
        int day2 = c2.get(Calendar.DAY_OF_YEAR);
        if (year1 > year2 || (year1 == year2 && day1 >= day2)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 比较两个日期先后
     *
     * @param c1
     * @param c2
     * @return 如果c1在c2前，返回true，否则返回false
     */
    public static boolean isBeforeOneDay(Calendar c1, Calendar c2) {
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        if (year1 < year2) {
            return true;

        } else if (year1 > year2) {
            return false;
        }
        int day1 = c1.get(Calendar.DAY_OF_YEAR);
        int day2 = c2.get(Calendar.DAY_OF_YEAR);
        if (day1 < day2) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 得到当前月+diff个月之后的日期
     *
     * @param diff
     * @return
     */
    public static Calendar afterMonth(int diff) {
        Calendar c = Calendar.getInstance();
        return afterMonth(c, diff);
    }

    /**
     * 得到某个日期为基准+diff个月之后的日期
     *
     * @param c
     * @param diff
     * @return
     */
    public static Calendar afterMonth(Calendar c, int diff) {
        c.add(Calendar.MONTH, diff);
        return c;
    }

    /**
     * 得到N天后的日期
     *
     * @param diff 相差几天
     * @return
     */
    public static Calendar afterDay(int diff) {
        Calendar c = Calendar.getInstance();
        return afterDay(c, diff);
    }

    /**
     * 得到N天后的日期
     *
     * @param c    基准日期
     * @param diff 相差几天
     * @return
     */
    public static Calendar afterDay(Calendar c, int diff) {
        c.add(Calendar.DAY_OF_MONTH, diff);
        return c;
    }

    /**
     * @param afterDay 0代表今天 1代表明天 2代表后台以此内推
     * @return 比如6月12日 6月1日
     */
    public static String formatYd(int afterDay) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(MONTH_DAY_FORMAT);//设置日期格式
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, afterDay);
            return df.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取从今天起往后N天的日期
     *
     * @param afterDay 0代表今天 1代表明天 2代表后台以此内推
     * @return 比如6月12日 6月1日
     */
    public static String formatYd(int afterDay, String format) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, afterDay);
            return df.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取从今天起往后N天的日期
     *
     * @param afterDay 0代表今天 1代表明天 2代表后台以此内推
     * @return 比如6月12日 6月1日
     */
    public static Date formatYd1(int afterDay) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, afterDay);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取从特定日期起往后N天的日期
     *
     * @param date     起始日期
     * @param afterDay 0代表今天 1代表明天 2代表后台以此内推
     * @return 比如6月12日 6月1日
     */
    public static Date formatYd1(Date date, int afterDay) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, afterDay);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取从今天起往后N天的日期
     *
     * @param afterDay 0代表今天 1代表明天 2代表后台以此内推
     * @return 比如6月12日 6月1日
     */
    public static Date formatYd2(int afterDay, String format) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, afterDay);
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 计算两个日期相差的月份数
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param pattern 日期1和日期2的日期格式
     * @return 相差的月份数
     * @throws ParseException yichang
     */
    public static int countMonths(String date1, String date2, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

        //开始日期若小月结束日期
        if (year < 0) {
            year = -year;
            return year * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        }

        return year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException yichang
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweendays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweendays));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException yichang
     */
    public static int daysBetween(String smdate, String bdate, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long betweendays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweendays));
    }


    /*******************************************************************************/
    /*******************************************************************************/
    /********************获取某月的第一天最后一天日期***********************************/
    /*******************************************************************************/
    /*******************************************************************************/

    /**
     * 获取当前月最后一天
     */
    public static Date getLastDayOfThisMonth() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,
                ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        return ca.getTime();
    }

    /**
     * 获取前月最后一天
     */
    public static Date getLastDayOfLastMonth() {
        // 获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);

        return cale.getTime();

    }

    /**
     * 获取当前月第一天
     */
    public static Date getFirstDayOfThisMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取前月的第一天
     */
    public static Date getFirstDayOfLastMonth() {
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.MONTH, -1);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        return cal1.getTime();
    }

    /**
     * 获取倒数第几个月的第一天
     */
    public static Date getFirstDayOfLastMonths(int months) {
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.MONTH, -months);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        return cal1.getTime();
    }

    /**
     * 获取倒数第几个月的最后一天
     */
    public static Date getLastDayOfLastMonths(int months) {
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.MONTH, -months);
        cal1.set(Calendar.DAY_OF_MONTH, 0);
        cal1.set(Calendar.HOUR_OF_DAY, 23);
        cal1.set(Calendar.MINUTE, 59);
        cal1.set(Calendar.SECOND, 59);
        return cal1.getTime();
    }


    /**
     * 获取年龄（虚岁or实岁）
     *
     * @param birthDay 生日
     * @return
     * @throws Exception
     */
    public static String getAge(Date birthDay) {
        if (birthDay == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int dayOfYearNow = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int dayofYearBirth = cal.get(Calendar.DAY_OF_YEAR);
        int age = yearNow - yearBirth;
        if (dayOfYearNow < dayofYearBirth - 1) {
            age--;
        }
        if (age < 0) {
            age = 0;
        }
        return age + "";
    }

    /**
     * 得到星期几的输出格式
     *
     * @param date
     * @return “星期一”、“星期二”等
     */
    public static String getWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEK_OF_DAYS[w];
    }

    /**
     * 得到星期几的输出格式
     *
     * @param date
     * @return "周日"，"周一"等
     */
    public static String getWeekOfDate_2(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEK_OF_DAYS2[w];
    }



    /************************************************************************************/
    /************************************************************************************/
    /*************************返回基于UNIX 1970-1-1基准的时间偏移值相关的函数****************/
    /************************************************************************************/
    /************************************************************************************/
    /**
     * 获取某个偏移时间
     *
     * @param unixtimestamp
     * @return
     */
    public static String getDate(int unixtimestamp) {
        return getDate(unixtimestamp * 1000L);
    }

    public static String getChatDate(int unixtimestamp) {
        return getChatDate(unixtimestamp * 1000L);
    }

    public static String getHourDate(int unixtimestamp) {
        return getHour(unixtimestamp * 1000L);
    }

    /**
     * 获取天开始时间
     *
     * @param unixtimestamp
     * @return
     */
    public static long getStartTime(int unixtimestamp) {

        Calendar c = getCalendar(unixtimestamp);

        String format = "%d-%d-%d 00:00:01";
        return getDayTime(c, format);
    }

    /**
     * 获取天结束时间
     *
     * @param unixtimestamp
     * @return
     */
    public static long getEndTime(int unixtimestamp) {
        Calendar c = getCalendar(unixtimestamp);

        String format = "%d-%d-%d 23:59:59";
        return getDayTime(c, format);
    }

    /**
     * 获取具体传入日期的unix_timestamp
     *
     * @param cal
     * @param format
     * @return
     */
    public static long getDayTime(Calendar cal, String format) {
        try {
            format = String
                    .format(format, cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH) + 1,
                            cal.get(Calendar.DAY_OF_MONTH));
            SimpleDateFormat sm = new SimpleDateFormat(YYMMDDHHMMSS,
                    Locale.CHINA);
            Date tmp;
            tmp = sm.parse(format);
            return tmp.getTime();
        } catch (ParseException e) {
            Log.e(mTag, "ParseException", e);
        } catch (Exception e) {
            Log.e(mTag, "Exception", e);
        } catch (Throwable e) {
            Log.e(mTag, "Throwable", e);
        }
        return 0L;
    }

    /**
     * 获取某个偏移值日期
     *
     * @param unixtimestamp
     * @return
     */
    public static String getDate(long unixtimestamp) {
        Date dt = new Date(unixtimestamp);
        return getDate(dt);
    }

    /**
     * 获取某个偏移值日期
     *
     * @param unixtimestamp
     * @return
     */
    public static String getFormatDate(long unixtimestamp) {
        Long time = new Long(unixtimestamp);
        return SIMPLE_DATE_FORMAT.format(time);
    }

    /**
     * 获取某个偏移值日期
     *
     * @param unixtimestamp
     * @return
     */
    public static String getChatDate(long unixtimestamp) {
        Date dt = new Date(unixtimestamp);
        return getChatDate(dt);
    }

    /**
     * 获取某个偏移值日期的小时
     *
     * @param unixtimestamp
     * @return
     */
    public static String getHour(long unixtimestamp) {
        Date dt = new Date(unixtimestamp);
        return getHour(dt);
    }

    /**
     * 获取当前日期的unix_timestamp
     *
     * @return
     */
    public static long getUnixTimeStamp() {
        Date dt = new Date();
        return getUnixTimeStamp(dt);
    }

    /**
     * 获取unix_timestamp
     *
     * @param dt
     * @return
     */
    public static long getUnixTimeStamp(Date dt) {
        return (long) (dt.getTime() / 1000L);
    }

    /**
     * 传入具体日期获取unix_timestamp
     *
     * @param date
     * @return
     */
    public static long getUnixTimeStamp(String date) {
        Date dt;
        try {
            SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD);
            dt = format.parse(date);
            return getUnixTimeStamp(dt);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
        } catch (Exception e) {
        } catch (Throwable e) {
        }
        return 0;
    }

    /**
     * 传入具体日期和日期格式获取unix_timestamp
     *
     * @param date
     * @return
     */
    public static long getUnixTimeStamp(String date, String mformats) {
        Date dt;
        try {
            SimpleDateFormat format = new SimpleDateFormat(mformats);
            dt = format.parse(date);
            return getUnixTimeStamp(dt);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
        } catch (Exception e) {
        } catch (Throwable e) {
        }
        return 0;
    }


    /**
     * 获取utc时间
     *
     * @return
     */
    public static CharSequence getUTCTime() {
        return getUTCTime(Locale.CHINA);
    }

    public static CharSequence getUTCTime(Locale local) {
        Calendar cal = Calendar.getInstance(local);
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return DateFormat.format("yyyy'-'MM'-'dd'T'kk':'mm':'ss'Z'", cal);
    }

    /**
     * 将Date类型转换为Calendar类型
     *
     * @param unixtimestamp
     * @return
     */
    public static Calendar getCalendar(long unixtimestamp) {
        Date dt = new Date(unixtimestamp);
        return getCalendar(dt);
    }

    public static Calendar getCalendar(int unixtimestamp) {
        return getCalendar(unixtimestamp * 1000L);
    }


    /**
     * 输入"yyyy-MM-dd HH:mm:ss"格式字符串，获取utc时间
     *
     * @param time
     * @return
     */
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 输入年月日，获取utc时间
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long getStringToDate(int year, int month, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        Date date = new Date();
        try {
            date = calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /****************************END UNIX TIME**************************************/
    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  //不同年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {   //闰年

                    timeDistance += 366;
                } else {   //不是闰年
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {
            return day2 - day1;
        }
    }

    public static long getTime(String usertime) {
        String retime = null;
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            d = sdf.parse(usertime);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static String calcuteDayOfWeek(int valueFromCalendarGet) {
        String date = "";
        switch (valueFromCalendarGet) {
            case Calendar.MONDAY:
                date = "周一";
                break;
            case Calendar.TUESDAY:
                date = "周二";
                break;
            case Calendar.WEDNESDAY:
                date = "周三";
                break;
            case Calendar.THURSDAY:
                date = "周四";
                break;
            case Calendar.FRIDAY:
                date = "周五";
                break;
            case Calendar.SATURDAY:
                date = "周六";
                break;
            case Calendar.SUNDAY:
                date = "周日";
                break;
            default:
                break;
        }
        return date;
    }

    public static String getYear(String date) {
        if (!TextUtil.isNull(date) && date.length() >= 5) {
            String[] strs = date.split("-");
            if (strs != null && strs.length > 0) {
                return strs[0];
            }
        }
        return null;
    }

    /**
     * 转换为应用时间格式
     * e.g.（几分钟前...2018-2-2 10:33）
     *
     * @param sdate
     * @param format
     * @return
     */
    public static String toSystemFriendlyTime7(String sdate, String format) {
        format = "yyyy-MM-dd HH:mm";
//        if (sdate.length() >= 19) {
//            sdate = sdate.substring(0, sdate.length() - 3);
//        }
        SimpleDateFormat dateFormater = new SimpleDateFormat(format);

        Date time;

        try {
            time = dateFormater.parse(sdate);
        } catch (ParseException e) {
            return null;
        }
        if (time == null) {
            return "";
        }

        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater.format(cal.getTime());
        String paramDate = dateFormater.format(time);
        String sdatetime = paramDate.substring(paramDate.length() - 5,
                paramDate.length());
        String nowday = curDate.substring(0, 10);
        String sdateday = paramDate.substring(0, 10);
        if (nowday.equals(sdateday)) {
            long xtime = cal.getTimeInMillis() - time.getTime();
            int hour = (int) (xtime / 3600000);
            if (hour == 0) {
                if ((xtime / 60000) < 1) {
                    ftime = "1分钟内";
                } else {
                    ftime = (xtime / 60000) + "分钟前";
                }
            } else {
                ftime = hour + "小时前";
            }
            return ftime;
        }


        double lt = time.getTime() * 1.0 / 86400000;
        double ct = cal.getTimeInMillis() * 1.0 / 86400000;
        int days = (int) (ct - lt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        int day1 = Integer.parseInt(date.substring(date.length() - 2,
                date.length()));
        int day2 = Integer.parseInt(sdate.substring(8, 10));
        if (days >= 2) {
            return paramDate;
        } else if (days == 1) {
            return "昨天 " + sdatetime;
        } else {
            double lh = time.getTime() * 1.0 / 3600000;
            double ch = cal.getTimeInMillis() * 1.0 / 3600000;
            int hours = (int) (ch - lh);
            if (hours < 24) {
                ftime = hours + "小时前";
            } else {
                if (day1 - day2 == 1) {
                    ftime = "昨天 " + sdatetime;
                } else {
                    return paramDate;
                }
            }

        }
        return ftime;
    }

    /**
     * 判断是否是明天
     *
     * @param date
     * @return
     */
    public static boolean isTomorrowDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Calendar pre = Calendar.getInstance();

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
            if (diffDay == 1) {
                return true;
            }
        }

        return false;
    }
}