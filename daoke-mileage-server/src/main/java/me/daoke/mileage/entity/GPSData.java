package me.daoke.mileage.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * User: chenlong
 * Date: 2015/4/15
 * Time: 10:40
 * 采集的GPS数据
 */
public class GPSData implements Serializable {


    private static final long serialVersionUID = -1070679872229187819L;

    private Double longitude[];

    private Double   latitude[];

    private String IMEI;

    //private String model ;

    private Integer speed[];

    private String tokenCode;

    private String accountID;

    private Integer GPSTime[];

    private Short altitude[];

    private Short direction[];

    public GPSData() {
    }

    public Double[] getLongitude() {
        return longitude;
    }

    public void setLongitude(Double[] longitude) {
        this.longitude = longitude;
    }

    public Double[] getLatitude() {
        return latitude;
    }

    public void setLatitude(Double[] latitude) {
        this.latitude = latitude;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public Integer[] getSpeed() {
        return speed;
    }

    public void setSpeed(Integer[] speed) {
        this.speed = speed;
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

    public Integer[] getGPSTime() {
        return GPSTime;
    }

    public void setGPSTime(Integer[] GPSTime) {
        this.GPSTime = GPSTime;
    }

    public Short[] getAltitude() {
        return altitude;
    }

    public void setAltitude(Short[] altitude) {
        this.altitude = altitude;
    }

    public Short[] getDirection() {
        return direction;
    }

    public void setDirection(Short[] direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "GPSData{" +
                "longitude=" + Arrays.toString(longitude) +
                ", latitude=" + Arrays.toString(latitude) +
                ", IMEI='" + IMEI + '\'' +
                ", speed=" + Arrays.toString(speed) +
                ", tokenCode='" + tokenCode + '\'' +
                ", accountID='" + accountID + '\'' +
                ", GPSTime=" + Arrays.toString(GPSTime) +
                ", altitude=" + Arrays.toString(altitude) +
                ", direction=" + Arrays.toString(direction) +
                '}';
    }
}
