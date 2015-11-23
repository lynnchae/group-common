package me.daoke.poweroff.entity;

import java.io.Serializable;

/**
 * User: chenlong
 * Date: 2015/5/19
 * Time: 10:55
 */
public class Location implements Comparable , Serializable {


    private Double longitude;

    private Double   latitude;

    private Integer speed;

    private Integer GPSTime;

    private Integer altitude;

    private Integer direction;


    public Location() {
    }

    public Location(Double longitude, Double latitude, Integer speed, Integer GPSTime, Integer altitude, Integer direction) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
        this.GPSTime = GPSTime;
        this.altitude = altitude;
        this.direction = direction;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getGPSTime() {
        return GPSTime;
    }

    public void setGPSTime(Integer GPSTime) {
        this.GPSTime = GPSTime;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }



    @Override
    public int compareTo(Object o) {
        return this.getGPSTime() - ((Location)o).getGPSTime();
    }


    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", speed=" + speed +
                ", GPSTime=" + GPSTime +
                ", altitude=" + altitude +
                ", direction=" + direction +
                '}';
    }
}
