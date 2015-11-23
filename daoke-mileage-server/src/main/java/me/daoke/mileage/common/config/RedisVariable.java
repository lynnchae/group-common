package me.daoke.mileage.common.config;

/**
 * User: chenlong
 * Date: 2015/4/22
 * Time: 13:30
 * redis变量类
 *
 */
public interface RedisVariable {

    /**
     * 里程
     */
    public class Mileage{
        /**
         * 每天行驶里程变量
         */
        public static final String DRIVER_MILEAGE_DAY ="driverMileageDay";


        /**
         * 每周行驶里程变量
         */
        public static final String DRIVER_MILEAGE_WEEKLY ="driverMileageWeekLy";

        /**
         * 每月行驶里程变量
         */
        public static final String DRIVER_MILEAGE_MONTH ="driverMileageMonth";

        /**
         * 存储gps 坐标最后一次经维度变量
         */
        public static final String TOKENCODE_LAST_LONLAT ="tokenCodeLastLonlat";


    }
}
