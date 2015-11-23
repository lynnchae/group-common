package me.daoke.driving.util;


import java.text.SimpleDateFormat;

/**
 * Created by wangzp on 2015/4/1.
 * <p/>
 * 常量
 */
public interface ConstantsUtil {


    /**
     * 用于验证签名
     */
    public class Sign {
        public static String APP_KEY = "286302235";
        public static String SECRET = "CD5ED55440C21DAF3133C322FEDE2B63D1E85949";
    }

    /**
     * 极光推送
     */
    public class JPush {
        public static final String APP_KEY = "984c766f15804e9a73e0a251";
        public static final String MASTER_SECRET = "23d78c543aec3b773de99124";
    }

    /**
     * 按天每日任务
     */
    public class EveryDay {
        /**
         * 每天开机
         */
        public static final String POWEROFF_ONE_DAY = "powerOneDay";
        /**
         * 连续驾驶3天
         */
        public static final String POWEROFF_THREE_DAY = "powerThreeDay";
        /**
         * 连续驾驶5天
         */
        public static final String POWEROFF_FIVE_DAY = "powerFiveDay";
        /**
         * 连续驾驶7天
         */
        public static final String POWEROFF_SEVEN_DAY = "powerSevenDay";

        /**
         * 完成10km后及任务
         */
        public static final String DRIVE_10Km_DAY = "drive10KmDay";
        /**
         * 每天完成30公里任务
         */
        public static final String DRIVE_30KM_DAY = "drive30KmDay";
        /**
         * 单日吐槽30条
         */
        public static final String TUCAO_30_DAY = "tucao30Day";



    }

    /**
     * 每周成长任务
     */
    public class Weekly {
        /**
         * 本周共行驶200公里
         */
        public static final String DRIVE_200Km_WEEKLY = "drive200KmWeekly";

        /**
         * 每周吐槽50条
         */
        public static final String TUCAO_50NUM_WEEKLY = "tucao50NumWeekly";
        /**
         * 每周分享3次成绩
         */
        public static final String SHARE_3_WEEKLY = "share3Weekly";
    }

    /**
     * 每月成长任务
     */
    public class Month {
        /**
         * 每月完成300km任务
         */
        public static final String DRIVE_300KM_MONTH = "drive300KmMonth";
        /**
         * 每月完成1000km任务
         */
        public static final String DRIVE_1000KM_MONTH = "drive1000KmMonth";
        /**
         * 每月完成3000km任务
         */
        public static final String DRIVE_3000KM_MONTH = "drive3000KmMonth";
        /**
         * 每月吐槽100条
         */
        public static final String TUCAO_100NUM_MONTH = "tucao100NumMonth";

    }

    /**
     * 成就任务
     */
    public class Grade {
        /**
         * 绑定微密
         */
        public static final String BINDING_WEME = "bindingWeme";
        /**
         * 设置昵称
         */
        public static final String SET_NICKNAME = "setNickname";

        /**
         * 单日行驶超过100公里
         */
        public static final String DRIVE_100KM_DAY = "exceed100DayOnce";
        /**
         * 第一次周边吐槽
         */
        public static final String FIRST_PERIPHERY_TUCAO = "firstPeripheryTucao";
        /**
         * 第一次频道吐槽
         */
        public static final String FIRST_CHANNEL_TUCAO = "firstChannelTucao";
        /**
         * 第一次回复吐槽
         */
        public static final String FIRST_REPLY_TUCAO = "firstReplyTucao";

        /**
         * 等级达到10级
         */
        public static final String GRADE_10 = "grade10";
        /**
         * 等级达到20级
         */
        public static final String GRADE_20 = "grade20";
        /**
         * 等级达到30级
         */
        public static final String GRADE_30 = "grade30";
        /**
         * 等级达到40级
         */
        public static final String GRADE_40 = "grade40";
        /**
         * 等级达到50级
         */
        public static final String GRADE_50 = "grade50";
        /**
         * 等级达到60级
         */
        public static final String GRADE_60 = "grade60";


    }


    /**
     * 开关机状态
     */
    public class PowerOff {

        /**
         * 用户里程变量名
         */
        public static final String ACCOUNT_MILAGE = "accountMileage";


        /**
         * 开机
         */
        public static final Integer BOOT = 10;

        /**
         * 关机
         */
        public static final Integer SHUTDOWN = 1;

    }


    /**
     * Http状态码
     */
    public class HttpStatusCode {
        //成功
        public static final int OK = 200;

    }


    /**
     * 道客内部状态码
     */
    public class DaoKeStatusCode {
        // 返回码
        public static final String AppVerification_OK = "9527";

        public static final String OK = "0";

        public static final String ERRORCODE_SERVICE_ERROR = "ME01001";
        public static final String RESULT_SERVICE_ERROR = "Service is error!";

        public static final String ERRORCODE_PARAMETERS_ERROR = "ME01023";

        public static final String ERRORCODE_JSON_ERROR = "ME01006";
        public static final String RESULT_JSON_ERROR = "Json is error!";

        public static final String ERRORCODE_MEMBER_EMPTY_ERROR = "ME22004";
        public static final String RESULT_MEMBER_EMPTY_ERROR = "The team member is empty!";

        public static final String ERRORCODE_EMPTY_ERROR = "ME22005";
        public static final String RESULT_EMPTY_ERROR = "The content is empty!";

        public static final String ERRORCODE_NOT_EXISTS_NAME = "ME22006";
        public static final String RESULT_NOT_EXISTS_NAME = "Username is not exist in db!";

        public static final String ERRORCODE_VERICODE = "ME22007";
        public static final String RESULT_VERICODE = "The verificationCode is error!";

        public static final String ERRORCODE_UNBIND = "ME22008";
        public static final String RESULT_UNBIND = "The mobile phone number did not bind IMEI!";

        public static final String ERRORCODE_USER_EXIST = "ME22009";
        public static final String RESULT_USER_EXIST = "The user has exist!";

        public static final String ERRORCODE_USER_EXIST_OTHRE = "ME22010";
        public static final String RESULT_USER_EXIST_OTHER = "The user has joined other teams!";

        public static final String ERRORCODE_FIELD_EMPTY = "ME22011";

        public static final String ERRORCODE_PHONE = "ME22012";
        public static final String RESULT_PHONE = "The phone number length is less than 6!";

        public static final String ERRORCODE_SIGN_FAIL = "ME01019";
        // 用户名不存在
        public static final String ERRORCODE_USERNAME_NOTEXIST = "ME18061";
    }


}
