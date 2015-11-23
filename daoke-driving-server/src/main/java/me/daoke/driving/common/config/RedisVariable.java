package me.daoke.driving.common.config;

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
         * 存储gps 坐标最后一次经纬度变量
         */
        public static final String TOKENCODE_LAST_LONLAT ="tokenCodeLastLonlat";

        /**
         * 存储gps 坐标最后一次速度信息和采集的时间
         */
        public static final String TOKENCODE_LAST_SPEED_GPSTIME ="tokenCodeLastSpeedGPSTime";


        /**
         * 用户谢尔值
         */
        public static final String ACCOUNTID_ROCHELLE = "accountIDRochelle";


    }
}
