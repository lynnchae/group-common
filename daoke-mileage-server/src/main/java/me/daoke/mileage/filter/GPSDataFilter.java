package me.daoke.mileage.filter;

import me.daoke.common.mq.client.ReceiveMessage;
import me.daoke.common.mq.util.JsonMapper;
import me.daoke.common.mq.util.MQConfig;
import me.daoke.mileage.common.config.ApplicationContextLoader;
import me.daoke.mileage.common.config.RedisVariable;
import me.daoke.mileage.common.service.RedisService;
import me.daoke.mileage.entity.GPSData;
import me.daoke.mileage.util.CalDistanceUtil;
import me.daoke.mileage.util.CalUtil;
import me.daoke.mileage.util.ConstantUtil;
import me.daoke.mileage.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chenlong
 * Date: 2015/4/21
 * Time: 16:38
 */
@Component
public class GPSDataFilter {

    private static Logger log = LoggerFactory.getLogger(GPSDataFilter.class);

    private ApplicationContextLoader loader;


    private RedisService redisService;


    public GPSDataFilter(ApplicationContextLoader loader) {
        this.loader = loader;
        redisService = loader.getBean("redisService");
    }

    public void calMileage() {
        PropertiesUtil props = PropertiesUtil.getInstance();
        final MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost(props.getProperty("poweroff_virtuaHost"));
        mqConfig.setUserName(props.getProperty("poweroff_username"));
        mqConfig.setPassWord(props.getProperty("poweroff_password"));
        mqConfig.setHost1(props.getProperty("poweroff_host1"));
        mqConfig.setPort1(Integer.parseInt(props.getProperty("poweroff_port1")));
        mqConfig.setQueueName(props.getProperty("gpsdata_queue"));
        mqConfig.setExchangeName(props.getProperty("poweroff_exchange"));
      //  mqConfig.setAutoAck(false);

        final Long day = CalUtil.getAllDays();
        final  int week = CalUtil.getWeekOfYear();
        final  int month = CalUtil.getMonthOfYear();
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            for (int i = 0; i < 6; i++) {
                executorService.execute(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            ReceiveMessage receiveMesasge = new ReceiveMessage(mqConfig);
                            while (true) {
                                long start1 = System.currentTimeMillis();
                                String message = receiveMesasge.receive();
                               // System.out.println("time1:" + (System.currentTimeMillis() - start1));
                                long start = System.currentTimeMillis();
                                GPSData gpsData = (GPSData) JsonMapper.fromJson(message, GPSData.class);
                               //System.out.println("gpsData:"+ gpsData);

                                calMilage(gpsData,day,week,month);
                             // System.out.println("time2:" + (System.currentTimeMillis() - start));

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();

            log.error(e.getMessage());
        }
    }

    final long segmNum = 100000000;

    /**
     * 计算里程
     *
     * @param gpsData
     */
    public void calMilage(GPSData gpsData,Long day,int week,int month){
        try {
            long distance = 0;
            if (gpsData != null) {
                Double longitude[] = gpsData.getLongitude();
                Double latiude[] = gpsData.getLatitude();
                if (longitude != null && latiude != null && longitude.length > 0 && latiude.length > 0 && longitude.length == latiude.length) {

                    String lon_lat_last_1 = (String) redisService.getHGetValue(RedisVariable.Mileage.TOKENCODE_LAST_LONLAT, gpsData.getTokenCode());
                    //计算距离
                    for (int i = 0; i < longitude.length; i++) {
                        if ((i + 1) == longitude.length) {
                            continue;
                        }

                        if (i == 0 && StringUtils.isNotBlank(lon_lat_last_1)) {


                            distance = distance + CalDistanceUtil.getDistance(latiude[i], longitude[i], Double.parseDouble(lon_lat_last_1.split("\\|")[1]), Double.parseDouble(lon_lat_last_1.split("\\|")[0]));
                        }

                        if (latiude[i] == null || longitude[i] == null || latiude[i + 1] == null || longitude[i + 1] == null) {
                            continue;
                        }
                        distance = distance + CalDistanceUtil.getDistance(latiude[i], longitude[i], latiude[i + 1], longitude[i + 1]);
                    }
                    if( longitude[longitude.length - 1] != null  && latiude[latiude.length - 1] != null &&  longitude[longitude.length - 1] >0 && latiude[latiude.length - 1] >0){
                        //保存用户gps坐标 最后一条经纬度
                        redisService.setHSetValue(RedisVariable.Mileage.TOKENCODE_LAST_LONLAT, gpsData.getTokenCode(), longitude[longitude.length - 1] + "|" + latiude[latiude.length - 1]);
                    }

                    // Long actalDistance = redisService.operatingHRuleValue(ConstantUtil.PowerOff.ACCOUNT_MILAGE, gpsData.getAccountID() + "_" + gpsData.getTokenCode(), distance);


                    Long mileageDay = redisService.getHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_DAY, gpsData.getAccountID(), null);

                    // driverMileageDay 变量存储 格式: 5005000  以10000为分割(车辆一天不会行驶超过9WKM) 前面为天数据后面为实际行驶米数 为第当年中的第5天当天行驶了5000M
                    long dayR = mileageDay / segmNum;
                    if (dayR == day) {
                        Long dis = redisService.operatingHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_DAY, gpsData.getAccountID(), distance);
                        // log.info("day distance:" + dis);

                        long mileageDayR = mileageDay % segmNum;
                        if (mileageDayR < 10000) {
                            //大于等于10KM 且小于等于30KM
                            if ((mileageDayR + distance) >= 10000 && (mileageDayR + distance) <= 30000) {
                                //奖励谢尔值
                                log.info("10KM DRIVER_MILEAGE_DAY:" + (mileageDayR + distance));
                            }
                        } else if (mileageDayR < 30000) {
                            //大于30KM
                            if ((mileageDayR + distance) >= 30000) {
                                //奖励谢尔值
                                log.info("30KM DRIVER_MILEAGE_DAY:" + (mileageDayR + distance));
                            }
                        }

                    } else {
                        //记录本次行驶里程
                        redisService.setHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_DAY, gpsData.getAccountID(), (day * segmNum) + distance);
                    }

                    Long mileageWeek = redisService.getHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_WEEKLY, gpsData.getAccountID(), null);

                    //driverMileageWeek 变量存储:  17100004000  以100000为分割(车辆一周不会行驶超过9WKM) 前面为周数据后面为实际行驶米数 为第当年中的第14周，当前周驶了4000M
                    long weekR = mileageWeek / segmNum;
                    if (weekR == week) {
                        Long dis = redisService.operatingHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_WEEKLY, gpsData.getAccountID(), distance);
                        //  log.info("week distance:" + dis);

                        long mileageWeekR = mileageWeek % segmNum;
                        if (mileageWeekR < 200000) {
                            if ((mileageWeekR + distance) >= 200000) {
                                //奖励谢尔值
                                log.info("200KM mileageWeek:" + (mileageWeekR + distance));
                            }
                        }
                    } else {
                        //记录本次行驶里程
                        redisService.setHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_WEEKLY, gpsData.getAccountID(), (week * segmNum) + distance);
                    }

                    //driverMileageMonth 变量存储:  17100004000  以100000为分割(车辆一个月不会行驶超过9WKM) 前面为月数据后面为实际行驶米数 为第当年中的第14周，当前周驶了4000M
                    Long mileageMonth = redisService.getHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_MONTH, gpsData.getAccountID(), null);
                    long monthR = mileageMonth / segmNum;
                    if (monthR == month) {
                        Long dis = redisService.operatingHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_MONTH, gpsData.getAccountID(), distance);
                        //    log.info("month distance:" + dis);

                        long mileageMonthR = mileageWeek % segmNum;
                        if (mileageMonthR < 300000) {
                            if ((mileageMonthR + distance) >= 300000) {
                                //奖励谢尔值
                                log.info("300KM DRIVER_MILEAGE_MONTH:" + (mileageMonthR + distance));
                            }
                        } else if (mileageMonthR < 1000000) {
                            if ((mileageMonthR + distance) >= 1000000) {
                                //奖励谢尔值
                                log.info("1000KM DRIVER_MILEAGE_MONTH:" + (mileageMonthR + distance));
                            }
                        } else if (mileageMonthR < 3000000) {
                            if ((mileageMonthR + distance) >= 3000000) {
                                //奖励谢尔值
                                log.info("3000KM DRIVER_MILEAGE_MONTH:" + (mileageMonthR + distance));
                            }
                        }

                    } else {
                        //记录本次行驶里程
                        redisService.setHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_MONTH, gpsData.getAccountID(), (month * segmNum) + distance);
                    }


                    //System.out.println("distance: " + distance);
                }
            }
        }catch (Exception e){
              e.printStackTrace();;
        }
      //  System.out.println(Thread.currentThread().getName() + "   distance: " + distance);

    }


}
