package me.daoke.poweroff.controller;

import me.daoke.common.mq.client.EmitDirect;
import me.daoke.common.mq.util.JsonMapper;
import me.daoke.poweroff.common.model.CommonJsonResult;
import me.daoke.poweroff.entity.*;
import me.daoke.poweroff.util.CalHashUtil;
import me.daoke.poweroff.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * 接收开关机请求
 * User: chenlong
 * Date: 2014/12/23
 * Time: 17:13
 */
@Controller
public class PowerroffController {

    private Logger log = LoggerFactory.getLogger(PowerroffController.class);

    /**
     * 单播生产者
     */
    @Autowired
    private EmitDirect emitDirect;


    /**
     * 单播生产者
     */
    @Autowired
    private EmitDirect emitDirectGpsData;


    @Value("#{commonConfig[gps_data_queue_num]}")
    private Integer gps_data_queue_num;


    @Value("#{commonConfig[gps_data_routeKey]}")
    private String gps_data_routeKey;

    @Value("#{commonConfig[poweroff_routeKey]}")
    private String poweroff_routeKey;


    /**
     * 接收开关机信息
     *
     * @param normal
     * @param tokenCode
     * @param accountID
     * @param imei
     * @param travelID
     * @param preTimestamp
     * @return
     */
    // @RequestMapping("/v1.0/receive_poweroff")
    //  @ResponseBody
    public void receivePoweroff(Integer normal, String tokenCode, String accountID,
                                String imei, String travelID, Integer preTimestamp) {
        //  CommonJsonResult jsonResult = new CommonJsonResult();

        try {

            //修改使用protobuf
            PowerOffClientProtoBuf.KeyValue.Builder clientPro = PowerOffClientProtoBuf.KeyValue.newBuilder();
            clientPro.setNormal(normal);
            clientPro.setImei(imei == null ? "" : imei);
            clientPro.setAccountID(accountID == null ? "" : accountID);
            clientPro.setTokenCode(tokenCode == null ? "" : tokenCode);
            clientPro.setTravelID(travelID == null ? "" : travelID);
            ;
            /**
             * 生产开关机消息
             */
//            PowerOffClient client = new PowerOffClient(normal, tokenCode, accountID, imei, travelID, preTimestamp);
//            String clientJson = JsonMapper.toJson(client, true);
            /**
             * 发送到消息队列
             */
            emitDirect.publish(poweroff_routeKey, clientPro.build().toByteArray());

            //jsonResult.setERRORCODE(ConstantsUtil.ERRORCODE_SUCCESS);
            // jsonResult.setRESULT("接收处理成功");
        } catch (Exception e) {
            //jsonResult.setERRORCODE(ConstantsUtil.RESULT_SERVICE_ERROR);
            // jsonResult.setRESULT("接收处理失败");
            log.error("ExceptionMessage", e.getMessage());
            log.error("StackTrace: ", e);
            e.printStackTrace();
        }

        // return jsonResult;
    }

    /**
     * 采集的gps 数据信息
     * <p/>
     * //     * @param longitude 经度
     * //     * @param latitude  纬度
     * //     * @param IMEI      终端IMEI号
     * //     * @param model     终端型号
     * //     * @param speed     速度
     * //     * @param tokenCode
     * //     * @param accountID 帐户ID
     * //     * @param GPSTime   gps采集时间
     * //     * @param altitude  海拔
     * //     * @param direction 方向角
     * //     * @param extragps  补传数据
     *
     * @return
     */
    @RequestMapping("/publicentry")
    @ResponseBody
    public void receive_gpsdata(
            @RequestBody Map<String, Object> jsonMap,
            HttpSession session,
            HttpServletRequest request) throws IOException {
        //处理gps 数据
        if (log.isInfoEnabled()) {
            log.info("/publicentry?params=" + jsonMap);
        }

        try {
            if (jsonMap == null) {
                return;
            }
            String accountID = (String) jsonMap.get("accountID");
            Boolean powerOn = (Boolean) jsonMap.get("powerOn");
            // Integer normal = (Integer) jsonMap.get("normal");
            // Integer preTimestamp = (Integer) jsonMap.get("preTimestamp");
            String travelID = (String) jsonMap.get("travelID");
            String IMEI = (String) jsonMap.get("IMEI");
            String tokenCode = (String) jsonMap.get("tokenCode");
            //开机处理
            if (powerOn != null && Boolean.valueOf(powerOn)) {
                // receivePoweroff(10, tokenCode, accountID, IMEI, travelID, preTimestamp);
                //修改使用protobuf
                PowerOffClientProtoBuf.KeyValue.Builder clientPro = PowerOffClientProtoBuf.KeyValue.newBuilder();
                clientPro.setNormal(10);
                clientPro.setImei(IMEI == null ? "" : IMEI);
                clientPro.setAccountID(accountID == null ? "" : accountID);
                clientPro.setTokenCode(tokenCode == null ? "" : tokenCode);
                clientPro.setTravelID(travelID == null ? "" : travelID);
                /**
                 * 发送到消息队列
                 */
                emitDirect.publish(poweroff_routeKey, clientPro.build().toByteArray());
                clientPro = null;
            } else {
                Boolean collect = (Boolean) jsonMap.get("collect");
                List<Double> longitude = (List<Double>) jsonMap.get("longitude");
                List<Double> latitude = (List<Double>) jsonMap.get("latitude");
                List<Integer> speed = (List<Integer>) jsonMap.get("speed");
                List<Integer> GPSTime = (List<Integer>) jsonMap.get("GPSTime");
                List<Integer> altitude = (List<Integer>) jsonMap.get("altitude");
                List<Integer> direction = (List<Integer>) jsonMap.get("direction");
                Map extragps = (Map) jsonMap.get("extragps");
                if (collect != null && collect) {
                    GPSDataProtuBuf.GPSData.Builder gpsData = GPSDataProtuBuf.GPSData.newBuilder();
                    //正常数据
                   // List alist = null;
                    if (longitude != null && latitude != null && speed != null && tokenCode != null && GPSTime != null && altitude != null && direction != null) {
                        GPSDataProtuBuf.Location.Builder location = null;
                        for (int i = 0; i < longitude.size(); i++) {
                            location = GPSDataProtuBuf.Location.newBuilder();
                            location.setLongitude(longitude.get(i));
                            location.setLatitude(latitude.get(i));
                            location.setSpeed( speed.get(i));
                            location.setAltitude(altitude.get(i));
                            location.setDirection( direction.get(i));
                            location.setGPSTime(GPSTime.get(i));
                            gpsData.addLocations(location);

                        }
                        //对GpsTime进行排序
                        quickList(gpsData.getLocationsList());
                    }
                    gpsData.setIMEI(IMEI == null ? "" : IMEI);
                    gpsData.setTokenCode(tokenCode == null ? "" : tokenCode);
                    gpsData.setAccountID(accountID == null ? "" : accountID);

                    //补传数据
                   // List extragpsList = null;
                    if (extragps != null) {
                        List<Double> longitudes = (List) extragps.get("longitude");
                        List<Double> latitudes = (List) extragps.get("latitude");
                        List<Integer> speeds = (List) extragps.get("speed");
                        List<Integer> GPSTimes = (List) extragps.get("GPSTime");
                        List<Integer> altitudes = (List) extragps.get("altitude");
                        List<Integer> directions = (List) extragps.get("direction");
                        if (longitudes != null && longitudes.size() > 0) {
                            for (int i = 0; i < longitudes.size(); i++) {
                                GPSDataProtuBuf.Location.Builder location = GPSDataProtuBuf.Location.newBuilder();
                                location.setLongitude(longitudes.get(i));
                                location.setLatitude( latitudes.get(i));
                                location.setSpeed( speeds.get(i));
                                location.setAltitude(altitudes.get(i));
                                location.setDirection( directions.get(i));
                                location.setGPSTime(GPSTimes.get(i));
                                gpsData.addExtragps(location);
                            }
                        }
                        quickList(gpsData.getExtragpsList());
                       // log.info("补传数据: extragpsList :" + extragpsList.toString());
                    }

                    /**
                     * 发送到消息队列     采用 加法hash 均匀分配message
                     */
                    String route = gps_data_routeKey + CalHashUtil.additiveHash(gpsData.getTokenCode(), gps_data_queue_num);
                    emitDirectGpsData.publish(route, gpsData.build().toByteArray());
                    gpsData = null;
                }
            }
        } catch (Exception e) {
            log.error("ExceptionMessage", e.getMessage());
            log.error("StackTrace: ", e);
            e.printStackTrace();
        }
    }


    public int getMiddleList(List<GPSDataProtuBuf.Location> list, int low, int high) {
        GPSDataProtuBuf.Location tmp = list.get(low);    //数组的第一个作为中轴
        while (low < high) {
            while (low < high && list.get(high).getGPSTime() >= tmp.getGPSTime()) {
                high--;
            }
            list.set(low, list.get(high));   //比中轴小的记录移到低端
            while (low < high && list.get(low).getGPSTime() <= tmp.getGPSTime()) {
                low++;
            }
            list.set(high, list.get(low));
            // list[high] = list[low];   //比中轴大的记录移到高端
        }
        list.set(low, tmp);
        //list[low] = tmp;              //中轴记录到尾
        return low;                   //返回中轴的位置
    }

    /**
     * 递归形式的分治排序算法
     *
     * @param list
     * @param low
     * @param high
     */
    public void _quickSortList(List<GPSDataProtuBuf.Location> list, int low, int high) {
        if (low < high) {
            int middle = getMiddleList(list, low, high);  //将list数组进行一分为二
            _quickSortList(list, low, middle - 1);        //对低字表进行递归排序
            _quickSortList(list, middle + 1, high);       //对高字表进行递归排序
        }
    }

    /**
     * @param str
     */
    public void quickList(List<GPSDataProtuBuf.Location> str) {
        if (str.size() > 0) {    //查看数组是否为空
            _quickSortList(str, 0, str.size() - 1);
        }
    }


}
