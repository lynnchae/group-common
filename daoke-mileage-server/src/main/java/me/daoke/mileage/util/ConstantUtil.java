package me.daoke.mileage.util;


import java.text.SimpleDateFormat;

/**
 * Created by wangzp on 2015/4/1.
 *
 * 常量
 */
 public interface ConstantUtil {

    /**
     * 按天每日任务
     */
    public class EveryDay{
        /**
         * 每天开机
         */
        public static final String POWEROFF_ONE_DAY ="powerOneDay";
        /**
         * 连续驾驶3天
         */
        public static final String POWEROFF_THREE_DAY ="powerThreeDay";
        /**
         * 连续驾驶5天
         */
        public static final String POWEROFF_FIVE_DAY ="powerFiveDay";
        /**
         * 连续驾驶7天
         */
        public static final String POWEROFF_SEVEN_DAY ="powerSevenDay";

        /**
         * 完成10km后及任务
         */
        public static final String DRIVE_10Km_DAY = "drive10KmDay";
        /**
         * 每天完成30公里任务
         */
        public static final String DRIVE_30KM_DAY ="drive30KmDay";
        /**
         * 单日吐槽30条
         */
        public static final String TUCAO_30_DAY ="tucao30Day";

        /**
         * 单日行驶超过100公里
         */
        public static final String DRIVE_100KM_DAY ="drive100KmDay";

    }

    /**
     * 每周成长任务
     */
    public class Weekly{
        /**
         * 本周共行驶200公里
         */
        public static final String DRIVE_200Km_WEEKLY = "drive200KmWeekly";

        /**
         * 每周吐槽50条
         */
        public static final String TUCAO_50NUM_WEEKLY ="tucao50NumWeekly";
        /**
         * 每周分享3次成绩
         */
        public static final String SHARE_3_WEEKLY = "share3Weekly";
    }

    /**
     * 每月成长任务
     */
    public class Month{
        /**
         *每月完成300km任务
         */
        public static final String DRIVE_300KM_MONTH ="drive300KmMonth";
        /**
         * 每月完成1000km任务
         */
        public static final String DRIVE_1000KM_MONTH ="drive1000KmMonth";
        /**
         * 每月完成3000km任务
         */
        public static final String DRIVE_3000KM_MONTH ="drive3000KmMonth";
        /**
         * 每月吐槽100条
         */
        public static final String TUCAO_100NUM_MONTH ="tucao100NumMonth";

    }

    /**
     * 成就任务
     */
    public class Grade{
        /**
         * 绑定微密
         */
        public static  final String BINDING_WEME ="bindingWeme";
        /**
         * 设置昵称
         */
        public static final String SET_NICKNAME ="setNickname";
        /**
         * 第一次周边吐槽
         */
        public static final String  FIRST_PERIPHERY_TUCAO ="firstPeripheryTucao";
        /**
         * 第一次频道吐槽
         */
        public static final String FIRST_CHANNEL_TUCAO ="firstChannelTucao";
        /**
         * 第一次回复吐槽
         */
        public static final String FIRST_REPLY_TUCAO ="firstReplyTucao";

        /**
         * 等级达到10级
         */
        public static final String GRADE_10 ="grade10";
        /**
         * 等级达到20级
         */
        public static final String GRADE_20 ="grade20";
        /**
         * 等级达到30级
         */
        public static final String GRADE_30 ="grade30";
        /**
         * 等级达到40级
         */
        public static final String GRADE_40 ="grade40";
        /**
         * 等级达到50级
         */
        public static final String GRADE_50 ="grade50";
        /**
         * 等级达到60级
         */
        public static final String GRADE_60 ="grade60";





    }

   //通用时间格式
   static SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 开关机状态
     */
    public class PowerOff{

        /**
         * 用户里程变量名
         */
        public static final String ACCOUNT_MILAGE ="accountMileage";


        /**
         * 开机
         */
        public static final Integer BOOT =10;

        /**
         * 关机
         */
        public static final Integer SHUTDOWN =1;


    }


}
