package me.daoke.common.mq.util;

/**
 * 经纬度
 * User: chenlong
 * Date: 2015/4/17
 * Time: 16:51
 *
 */
public class BLRate {

    /**
     * 经度
     */
    private double longitude;


    /**
     * 纬度
     */
    private double latitude;


    public BLRate() {
    }


    public BLRate(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "BLRate{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
