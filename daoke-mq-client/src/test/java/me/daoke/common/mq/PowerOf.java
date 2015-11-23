package me.daoke.common.mq;

import java.io.Serializable;

/**
 * User: chenlong
 * Date: 2015/3/31
 * Time: 11:18
 */
public class PowerOf implements Serializable {


    private String accountID;

    private String preTimestamp;

    private String imei;

    private String tokenCode;

    private String travelID;

    private  Integer normal;

    public PowerOf(String accountID, String preTimestamp, String imei, String tokenCode, String travelID, Integer normal) {
        this.accountID = accountID;
        this.preTimestamp = preTimestamp;
        this.imei = imei;
        this.tokenCode = tokenCode;
        this.travelID = travelID;
        this.normal = normal;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getPreTimestamp() {
        return preTimestamp;
    }

    public void setPreTimestamp(String preTimestamp) {
        this.preTimestamp = preTimestamp;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public String getTravelID() {
        return travelID;
    }

    public void setTravelID(String travelID) {
        this.travelID = travelID;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    @Override
    public String toString() {
        return "PowerOf{" +
                "accountID='" + accountID + '\'' +
                ", preTimestamp='" + preTimestamp + '\'' +
                ", imei='" + imei + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", travelID='" + travelID + '\'' +
                ", normal=" + normal +
                '}';
    }
}
