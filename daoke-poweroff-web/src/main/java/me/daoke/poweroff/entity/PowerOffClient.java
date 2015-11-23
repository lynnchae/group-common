package me.daoke.poweroff.entity;

import java.io.Serializable;

/**
 * User: chenlong
 * Date: 2015/4/7
 * Time: 17:13
 */
public class PowerOffClient implements Serializable {

    /**
     * 开关机状态  10 开机  1 关机
     */
    private Integer normal;
    /**
     *
     */
    private String tokenCode;
    /**
     * 用户帐户ID
     */
    private String accountID;

    /**
     * 终端设备IMDI
     */
    private String imei;
    /**
     * 途径ID
     */
    private String travelID;
    /**
     * - 上一次的最后一次心跳包时间
     */
    private Integer preTimestamp;


    public PowerOffClient() {
    }


    public PowerOffClient(Integer normal, String tokenCode, String accountID, String imei, String travelID, Integer preTimestamp) {
        this.normal = normal;
        this.tokenCode = tokenCode;
        this.accountID = accountID;
        this.imei = imei;
        this.travelID = travelID;
        this.preTimestamp = preTimestamp;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTravelID() {
        return travelID;
    }

    public void setTravelID(String travelID) {
        this.travelID = travelID;
    }

    public Integer getPreTimestamp() {
        return preTimestamp;
    }

    public void setPreTimestamp(Integer preTimestamp) {
        this.preTimestamp = preTimestamp;
    }

    @Override
    public String toString() {
        return "PowerOffClient{" +
                "normal=" + normal +
                ", tokenCode='" + tokenCode + '\'' +
                ", accountID='" + accountID + '\'' +
                ", imei='" + imei + '\'' +
                ", travelID='" + travelID + '\'' +
                ", preTimestamp=" + preTimestamp +
                '}';
    }
}
