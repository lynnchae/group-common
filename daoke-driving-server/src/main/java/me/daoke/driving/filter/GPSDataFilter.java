package me.daoke.driving.filter;

import me.daoke.common.mq.client.EmitDirect;
import me.daoke.common.mq.client.ReceiveMessage;
import me.daoke.common.mq.util.JsonMapper;
import me.daoke.common.mq.util.MQConfig;
import me.daoke.driving.common.config.ApplicationContextLoader;
import me.daoke.driving.common.config.RedisVariable;
import me.daoke.driving.common.service.RedisService;
import me.daoke.driving.entity.GPSDataProtuBuf;
import me.daoke.driving.entity.GPSDataProtuBuf.Location;
import me.daoke.driving.entity.RuleRewardMq;
import me.daoke.driving.util.CalUtil;
import me.daoke.driving.util.ConstantsUtil;
import me.daoke.driving.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

    private EmitDirect ruleCodeDirect;


    private String routRuleCodeReward = "rout_ruleCode_reward";


    public GPSDataFilter(ApplicationContextLoader loader) {
        this.loader = loader;
        redisService = loader.getBean("redisService");
        ruleCodeDirect = loader.getBean("ruleCodeDirect");
    }

    public void calMileage(String ququeName) {
        PropertiesUtil props = PropertiesUtil.getInstance();
        final MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost(props.getProperty("gpsdata_virtuaHost"));
        mqConfig.setUserName(props.getProperty("gpsdata_username"));
        mqConfig.setPassWord(props.getProperty("gpsdata_password"));
        mqConfig.setHost1(props.getProperty("gpsdata_host1"));
        mqConfig.setPort1(Integer.parseInt(props.getProperty("gpsdata_port1")));
        mqConfig.setHost2(props.getProperty("gpsdata_host2"));
        mqConfig.setPort2(Integer.parseInt(props.getProperty("gpsdata_port2")));
        mqConfig.setQueueName(ququeName);
        mqConfig.setExchangeName(props.getProperty("gpsdata_exchange"));
        mqConfig.setPrefetchCount(100);

        routRuleCodeReward = props.getProperty("mq.rout_ruleCode_reward");


        int poolThreadNum = Integer.valueOf(props.getProperty("pool_thread_num"));

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(poolThreadNum);
            for (int i = 0; i < poolThreadNum; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        ReceiveMessage receiveMesasge = new ReceiveMessage(mqConfig);
                        try {
                            while (true) {

                                // long start1 = System.currentTimeMillis();
                                GPSDataProtuBuf.GPSData.Builder gpsData = GPSDataProtuBuf.GPSData.parseFrom(receiveMesasge.receiveToByte()).toBuilder();
                               // String message = receiveMesasge.receive();
                               // log.info("gpsData:" + message);
                                // System.out.println("time1:" + (System.currentTimeMillis() - start1));
                                // long start = System.currentTimeMillis();
                               // GPSData gpsData = (GPSData) JsonMapper.fromJson(message, GPSData.class);
                                calMilage(gpsData);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();

            log.error(e.getMessage());
        }
    }

    final long segmNum = 100000000;

    private  static String extraGpsKey ="extraGps:";
    /**
     * 计算里程
     *
     * @param gpsData
     */
    public void calMilage( GPSDataProtuBuf.GPSData.Builder gpsData) {
        try {
            Long day = CalUtil.getAllDays();
            int week = CalUtil.getWeekOfYear();
            int month = CalUtil.getMonthOfYear();
            long distance = 0;
            long extDistance = 0;
            if (gpsData != null) {
                List<Location> list = gpsData.getLocationsList();
                if (list != null && list.size() > 0) {
                    Object tokenCodeLastSpeedGpsTimeObj = redisService.getHGetValue(RedisVariable.Mileage.TOKENCODE_LAST_SPEED_GPSTIME, gpsData.getTokenCode());
                    Integer tokenCodeLastSpeed = null;
                    Integer tokenCodeLastGPSTime = null;
                    if (tokenCodeLastSpeedGpsTimeObj != null) {
                        String[] lastSpeedGpsTimeStr = tokenCodeLastSpeedGpsTimeObj.toString().split("\\|");
                        tokenCodeLastSpeed = Integer.valueOf(lastSpeedGpsTimeStr[0]);
                        tokenCodeLastGPSTime = Integer.valueOf(lastSpeedGpsTimeStr[1]);
                    }

                    Location startLocation = list.get(0);
                    if (tokenCodeLastSpeed != null && tokenCodeLastGPSTime != null) {
                        //  distance = distance + ((tokenCodeLastSpeed + startLocation.getSpeed() / 2));
                        //计算上次包和本次包第一条的时间差
                        Integer timeDif = startLocation.getGPSTime() - tokenCodeLastGPSTime;
                        if (timeDif <= 15 && timeDif >0) {
                            //将上一次的速度 + 本次GPS第一条的数据 /2 * 时间差算距离   注： 会有误差
                            distance = distance + ((tokenCodeLastSpeed + startLocation.getSpeed()) / 2 *1000/3600) * timeDif;
                            // distance = distance + CalDistanceUtil.getDistance(location.getLatitude(), longitude[i], Double.parseDouble(lon_lat_last_1.split("\\|")[1]), Double.parseDouble(lon_lat_last_1.split("\\|")[0]));
                        }
                        //如果大于15秒为不处理当前包中的第一秒和前一个包在redis的数据
                        else if (timeDif > 15) {
                            ///处理补传的数据
                            //丢包的开始时间                   *1000用于
                            redisService.zAddSortedSet(extraGpsKey + gpsData.getTokenCode(), tokenCodeLastGPSTime, tokenCodeLastSpeed + "");
                            //丢包的结束时间      +1000用于标识是丢包结束的标识
                            redisService.zAddSortedSet(extraGpsKey + gpsData.getTokenCode(), startLocation.getGPSTime(), (startLocation.getSpeed() + 1000) + "");
                             //设置key 的过期 时间
                            redisService.setKeyExpire(extraGpsKey + gpsData.getTokenCode(),60*60*12);
                        }

                    }
                    //计算正常数据里程
                    for (int i = 0; i < list.size(); i++) {
                        if (i == list.size() - 1) {
                            continue;
                        }

                        Location location = list.get(i);
                        Location location2 = list.get(i + 1);
                        //如果下一个包中的时间小于上次最后的时间点 则抛弃计算  终端BUG
                        if(tokenCodeLastGPSTime != null && location.getGPSTime() <= tokenCodeLastGPSTime){
                           continue;
                        }

                        distance = distance + (location.getSpeed() + location2.getSpeed()) / 2 *1000/3600;
                    }

                    //保存用户gps坐标 最后一条速度信息和时间
                    redisService.setHSetValue(RedisVariable.Mileage.TOKENCODE_LAST_SPEED_GPSTIME, gpsData.getTokenCode(), list.get(list.size() - 1).getSpeed() + "|" + list.get(list.size() - 1).getGPSTime());

                    List<Location> tExtragps = gpsData.getExtragpsList();



                    //处理后的数据
                    List<Location> tExtragpsProcess = new ArrayList<Location>();
                    //如果有补传的数据
                    if (tExtragps != null && tExtragps.size() > 0) {

                        log.info("补传数据坐标："  + tExtragps.toString());
                        //确定补传的开始时间
                        int startTime = tExtragps.get(0).getGPSTime();
                        //确定补传的最后时间
                        int endTime = tExtragps.get(tExtragps.size() - 1).getGPSTime();
                        //从redis 中取出有需要补传的数据的节点
                        Set<RedisZSetCommands.Tuple> set = redisService.zRangByScoreSortedSet(extraGpsKey + gpsData.getTokenCode(), startTime, endTime);
                        if (set != null && set.size() > 0) {

                            Iterator<RedisZSetCommands.Tuple> iterator = set.iterator();
                            while (iterator.hasNext()) {
                                RedisZSetCommands.Tuple tuple = iterator.next();
                                int rStartTime = tuple.getScore().intValue();
                                int rStartSpeed = Integer.valueOf(new String(tuple.getValue()));
                                int rEndTime = 0;
                                int rEndSpeed = 0;
                                //如果speed < 1000 为 补传数据的开始时间段
                                if (rStartSpeed < 1000) {
                                    //把补传的结束时间 和速度取出用于判断
                                    if (iterator.hasNext()) {
                                        RedisZSetCommands.Tuple endTuple = iterator.next();
                                        rEndTime = endTuple.getScore().intValue();
                                        rEndSpeed = Integer.valueOf(new String(endTuple.getValue()));
                                    }
                                }

                                //取出 是补传数据时间内的 数据
                                for (int i = 0; i < tExtragps.size(); i++) {
                                    Location locationExt = tExtragps.get(i);
                                    //只有补传的时间大于redis 中开始时间 且 小于结束时间
                                    if (locationExt.getGPSTime() > rStartTime && locationExt.getGPSTime() < rEndTime) {
                                        tExtragpsProcess.add(locationExt);
                                    }
                                }

                                //计算补传的数据 里程
                                if (tExtragpsProcess != null && tExtragpsProcess.size() > 0) {
                                    //计算 第一条 和 redis中的需要补传数据时间 的距离
                                    Location locationExt0 = tExtragpsProcess.get(0);
                                    if ((locationExt0.getGPSTime() - rStartTime) <= 15 && (locationExt0.getGPSTime() - rStartTime) >0) {
                                        extDistance = extDistance + ((locationExt0.getSpeed() + rStartSpeed)  / 2 *1000/3600) * (locationExt0.getGPSTime() - rStartTime);
                                        //删除redis中 里程丢失开始时间点
                                        redisService.zRemRangeByScoreSortedSet(extraGpsKey + gpsData.getTokenCode(),rStartTime,rStartTime);
                                    } else {
                                        //添加新的结束点
                                        redisService.zAddSortedSet(extraGpsKey + gpsData.getTokenCode(), locationExt0.getGPSTime(), (locationExt0.getSpeed() + 1000) + "");
                                        redisService.setKeyExpire(extraGpsKey + gpsData.getTokenCode(),60*60*12);
                                    }
                                     //计算补传的里程
                                    for (int i = 0; i < tExtragpsProcess.size(); i++) {
                                        if (i == tExtragpsProcess.size() - 1) {
                                            continue;
                                        }
                                        Location location = tExtragpsProcess.get(i);
                                        Location location2 = tExtragpsProcess.get(i + 1);
                                        if (  location2.getSpeed() - location.getSpeed() <= 15) {
                                            extDistance = extDistance + ((location.getSpeed() + location2.getSpeed()) / 2 *1000/3600) * (location2.getGPSTime() - location.getGPSTime());
                                        }
                                    }

                                    //计算 第最后一条 和 redis中的需要补传数据时间 的距离
                                    Location locationExtLast = tExtragpsProcess.get(tExtragpsProcess.size() -1);
                                    if((locationExtLast.getGPSTime() - rEndTime) <= 15 && (locationExtLast.getGPSTime() - rEndTime) >0){
                                        extDistance = extDistance + ((locationExtLast.getSpeed() + rEndSpeed)  / 2 * 1000/3600) * (locationExtLast.getGPSTime()  - rEndTime);
                                        //删除redis中 里程丢失结束时间点
                                        redisService.zRemRangeByScoreSortedSet(extraGpsKey + gpsData.getTokenCode(),rEndTime,rEndTime);
                                    }else{
                                        //添加新的开始
                                        redisService.zAddSortedSet(extraGpsKey + gpsData.getTokenCode(), locationExtLast.getGPSTime(), locationExtLast.getSpeed() + "");
                                        redisService.setKeyExpire(extraGpsKey + gpsData.getTokenCode(),60*60*12);
                                    }
                                }
                            }
                        }
                    }

                // Long actalDistance = redisService.operatingHRuleValue(ConstantUtil.PowerOff.ACCOUNT_MILAGE, gpsData.getAccountID() + "_" + gpsData.getTokenCode(), distance);
                Long mileageDay = redisService.getHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_DAY, gpsData.getAccountID(), null);

                log.info("accountID: "+ gpsData.getAccountID() +  "  本上传gps 行行驶距离:" + distance + "  sumMilleageDay :" + mileageDay % segmNum);

                log.info("本上传补专gps 行行驶距离:" + extDistance);

                //加上补传距离
                distance +=extDistance;

                // driverMileageDay 变量存储 格式: 5005000  以10000为分割(车辆一天不会行驶超过9WKM) 前面为天数后面为实际行驶米数 为第当年中的第5天当天行驶了5000M
                long dayR = mileageDay / segmNum;
                if (dayR == day.longValue()) {

                    Long dis = redisService.operatingHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_DAY, gpsData.getAccountID(), distance);
                     log.info("day distance:" + dis);

                    long mileageDayR = mileageDay % segmNum;
                    log.info("mileageDayR : "+(mileageDayR + distance));
                    if (mileageDayR < 10000) {
                        //大于等于10KM 且小于等于30KM
                        if ((mileageDayR + distance) >= 10000 && (mileageDayR + distance) <= 30000) {
                            //预奖励谢尔值
                            // userGradeService.toReward(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.EveryDay.DRIVE_10Km_DAY);
                            pushMessage(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.EveryDay.DRIVE_10Km_DAY);
                            log.info("10KM DRIVER_MILEAGE_DAY:" + (mileageDayR + distance));
                        }
                    } else if (mileageDayR < 30000) {
                        //大于30KM
                        if ((mileageDayR + distance) >= 30000) {
                            //奖励谢尔值
                            // userGradeService.toReward(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.EveryDay.DRIVE_30KM_DAY);
                            pushMessage(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.EveryDay.DRIVE_30KM_DAY);
                            log.info("30KM DRIVER_MILEAGE_DAY:" + (mileageDayR + distance));

                        }


                    }
                    //单日行驶超过100KM
                    if(mileageDayR >= 100000){
                       Long dri100KM = redisService.getHRuleValue(ConstantsUtil.Grade.DRIVE_100KM_DAY, gpsData.getAccountID(), null);
                        if(dri100KM < 100000){
                            redisService.setHRuleValue(ConstantsUtil.Grade.DRIVE_100KM_DAY, gpsData.getAccountID(), mileageDayR + distance);
                            pushMessage(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Grade.DRIVE_100KM_DAY);
                            log.info("100KM exceed100DayOnce:" + (mileageDayR + distance));
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
                     log.info("week distance:" + dis);

                    long mileageWeekR = mileageWeek % segmNum;
                    if (mileageWeekR < 200000) {
                        if ((mileageWeekR + distance) >= 200000) {
                            //奖励谢尔值
                            //userGradeService.toReward(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Weekly.DRIVE_200Km_WEEKLY);
                            pushMessage(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Weekly.DRIVE_200Km_WEEKLY);
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
                       log.info("month distance:" + dis);
                    long mileageMonthR = mileageWeek % segmNum;
                    //System.out.println((mileageMonthR+distance)+":distance:"+distance);
                    if (mileageMonthR < 300000) {
                        if ((mileageMonthR + distance) >= 300000) {
                            //奖励谢尔值
                            //userGradeService.toReward(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Month.DRIVE_300KM_MONTH);
                            pushMessage(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Month.DRIVE_300KM_MONTH);
                            log.info("300KM DRIVER_MILEAGE_MONTH:" + (mileageMonthR + distance));
                        }
                    } else if (mileageMonthR < 1000000) {
                        if ((mileageMonthR + distance) >= 1000000) {
                            //奖励谢尔值
                            // userGradeService.toReward(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Month.DRIVE_1000KM_MONTH);
                            pushMessage(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Month.DRIVE_1000KM_MONTH);
                            log.info("1000KM DRIVER_MILEAGE_MONTH:" + (mileageMonthR + distance));

                        }
                    } else if (mileageMonthR < 3000000) {
                        if ((mileageMonthR + distance) >= 3000000) {
                            //奖励谢尔值
                            //  userGradeService.toReward(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Month.DRIVE_3000KM_MONTH);
                            pushMessage(gpsData.getAccountID(), gpsData.getIMEI(), ConstantsUtil.Month.DRIVE_3000KM_MONTH);
                            log.info("3000KM DRIVER_MILEAGE_MONTH:" + (mileageMonthR + distance) + "Thread :" + Thread.currentThread().getName());

                        }
                    }

                } else {
                    //记录本次行驶里程
                    redisService.setHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_MONTH, gpsData.getAccountID(), (month * segmNum) + distance);
                }
                //System.out.println("distance: " + distance);

            }
        }
    }

    catch(
    Exception e
    )

    {
        e.printStackTrace();
        ;
    }
    //  System.out.println(Thread.currentThread().getName() + "   distance: " + distance);

}

    /**
     * 生产 帐户规则奖励消息
     *
     * @param accountID 帐户ID
     * @param IMEI      IMEI号
     * @param ruleCode  规则代码
     * @throws Exception
     */
    public void pushMessage(String accountID, String IMEI, String ruleCode) throws Exception {

        RuleRewardMq ruleRewardMq = new RuleRewardMq(accountID, IMEI, ruleCode);
        String message = JsonMapper.toJson(ruleRewardMq, true);
        //System.out.println("message: "+ message);
        ruleCodeDirect.publish(routRuleCodeReward, message);


    }


}
