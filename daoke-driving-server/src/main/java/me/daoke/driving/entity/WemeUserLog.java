package me.daoke.driving.entity;

import java.util.Date;

/**
 * Created by huanghongyang on 2015/3/24.
 */
public class WemeUserLog {
    /**
     * 请求路径
     */
    private String action;
    /**
     * 请求时间
     */
    private Date accessTime;
    /**
     * 终端类别
     */
    private String sys;
    /**
     * ios android
     */
    private String app;
    /**
     * 请求参数
     */
    private String parameterInfos;
    /**
     * 备注
     */
    private String remark;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getParameterInfos() {
        return parameterInfos;
    }

    public void setParameterInfos(String parameterInfos) {
        this.parameterInfos = parameterInfos;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WemeUserLog{" +
                "action='" + action + '\'' +
                ", accessTime=" + accessTime +
                ", sys='" + sys + '\'' +
                ", app='" + app + '\'' +
                ", parameterInfos='" + parameterInfos + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
