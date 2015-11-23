package me.daoke.mileage.util;

import java.util.Calendar;

/**
 * User: chenlong
 * Date: 2015/4/11
 * Time: 13:53
 * 日期工具类
 *
 */
public class CalUtil {

    /**
     * 获取1970年到当前时间的天数
     * @return
     */
    public static long getAllDays(){
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis() /(24*60*60*1000);
    }


    /**
     * 获取1970年到当前的自然周
     * @return
     */
    public static long getAllWeek(){
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis() /(24*60*60*1000*7);
    }

    /**
     * 获取当天中的月份
     *
     * @return
     */
    public static int getMonthOfYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) +1 ;

    }

    /**
     * 获取当年中的周
     *
     * @return
     */
    public static int getWeekOfYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR) ;

    }



    /**
     * 获取当年中的第几天
     *
     * @return
     */
    public static int getDayOfYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_YEAR) ;

    }
}
