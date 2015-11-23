package me.daoke.common.mq.util;

/**
 * User: chenlong
 * Date: 2015/4/17
 * Time: 16:51
 */
public class CalDistanceUtil {

    static double EARTH_RADIUS = 6378245.0;

    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两点之前的距离
     *
     *
     * @param b1    起始经纬度对象
     * @param b2    终始经纬度对象
     * @return
     */
    public static long getDistance(BLRate b1, BLRate b2)
    {
        double radLat1 = rad(b1.getLatitude());
        double radLat2 = rad(b2.getLatitude());
        double radLat = radLat1 - radLat2;
        double radLng = rad(b1.getLongitude()) - rad(b2.getLongitude());
        double s = 2*Math.asin(Math.sqrt(Math.pow(Math.sin(radLat/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(radLng/2),2)));
        int distance  = (int) (s*EARTH_RADIUS);
        return distance;
    }


    /**
     * 计算两点之前的距离
     *
     * @param startLatitude   起始纬度
     * @param StartLongitude  起始经度
     * @param endLatitude     终始纬度
     * @param endLongitude    终始经度
     * @return
     */
    public static long getDistance(double startLatitude,double StartLongitude,double endLatitude,double endLongitude )
    {
        double radLat1 = rad(startLatitude);
        double radLat2 = rad(endLatitude);
        double radLat = radLat1 - radLat2;
        double radLng = rad(StartLongitude) - rad(endLongitude);
        double s = 2*Math.asin(Math.sqrt(Math.pow(Math.sin(radLat/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(radLng/2),2)));
        int distance  = (int) (s*EARTH_RADIUS);
        return distance;
    }

}