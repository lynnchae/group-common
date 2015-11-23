package me.daoke.driving.filter;

import me.daoke.common.mq.client.EmitDirect;
import me.daoke.common.mq.client.ReceiveMessage;
import me.daoke.common.mq.util.MQConfig;
import me.daoke.driving.common.config.ApplicationContextLoader;
import me.daoke.driving.common.service.RedisService;
import me.daoke.driving.entity.PowerOffClient;
import me.daoke.driving.entity.PowerOffClientProtoBuf;
import me.daoke.driving.entity.RuleRewardMq;
import me.daoke.driving.util.CalUtil;
import me.daoke.driving.util.ConstantsUtil;
import me.daoke.driving.util.JsonMapper;
import me.daoke.driving.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chenlong
 * Date: 2015/3/31
 * Time: 11:07
 */
@Component
public class PowerOffFilter {

    private static Logger log = LoggerFactory.getLogger(PowerOffFilter.class);

    private ApplicationContextLoader loader;


    private RedisService redisService ;


    private EmitDirect ruleCodeDirect;


    private String routRuleCodeReward = "rout_ruleCode_reward";

    public PowerOffFilter(ApplicationContextLoader loader) {
        this.loader = loader;
        redisService = loader.getBean("redisService");
        ruleCodeDirect = loader.getBean("ruleCodeDirect");
    }



    /**
     * 设置开机里程变量
     */
    public void setBootMileageVariable(PowerOffClient powerOff){
       // Long ruleValueOneDay = redisService.getHRuleValue(ConstantUtil.EveryDay.POWEROFF_ONE_DAY, powerOff.getAccountID(), null);
         //key  由 accountID ,tokenCode组成        value为里程数
        redisService.setHRuleValue(ConstantsUtil.PowerOff.ACCOUNT_MILAGE, powerOff.getAccountID()+"_"+ powerOff.getTokenCode(), 0l);
    }

    /**
     * 计算开关机
     *
     * @throws Exception
     */
    public void calPowerOff() throws Exception {

        PropertiesUtil props = PropertiesUtil.getInstance();
        final   MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost(props.getProperty("poweroff_virtuaHost"));
        mqConfig.setUserName(props.getProperty("poweroff_username"));
        mqConfig.setPassWord(props.getProperty("poweroff_password"));
        mqConfig.setHost1(props.getProperty("poweroff_host1"));
        mqConfig.setPort1(Integer.parseInt(props.getProperty("poweroff_port1")));
        mqConfig.setHost2(props.getProperty("poweroff_host2"));
        mqConfig.setPort2(Integer.parseInt(props.getProperty("poweroff_port2")));
        mqConfig.setQueueName(props.getProperty("poweroff_queue"));
        mqConfig.setExchangeName(props.getProperty("poweroff_exchange"));
        mqConfig.setPrefetchCount(20);

        int poolThreadNum =Integer.valueOf(props.getProperty("pool_thread_num"));
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(poolThreadNum);
            for (int i = 0; i < poolThreadNum; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ReceiveMessage receiveMesasge = new ReceiveMessage(mqConfig);
                            while (true) {
                                //采有protobuf 提高解析效率
                                PowerOffClientProtoBuf.KeyValue.Builder powerOff =  PowerOffClientProtoBuf.KeyValue.parseFrom(receiveMesasge.receiveToByte()).toBuilder();
                                //String message = receiveMesasge.receive();
                                // log.info("powerOff:" + message);
                                // PowerOffClient powerOff = (PowerOffClient) JsonMapper.fromJson(message, PowerOffClient.class);
                                //开机
                                // if(powerOff.getNormal() == ConstantsUtil.PowerOff.BOOT) {
                                //设置用户单次里程变量
                                //   setBootMileageVariable(powerOff);
                                //计算开机规则
                                calStartUp(powerOff);
                                //  }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
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

    final long segmNum = 1000;

    /**
     * 计算开机规则
     *
     * @param powerOff
     */
    public void calStartUp(PowerOffClientProtoBuf.KeyValue.Builder powerOff) throws Exception {

        Long day = CalUtil.getAllDays();

        //--------------------1.判断每天开机情况 begin    -----------
        Long ruleValueOneDay = redisService.getHRuleValue(ConstantsUtil.EveryDay.POWEROFF_ONE_DAY, powerOff.getAccountID(), null);
        log.info("ruleValueOneDay:" + ruleValueOneDay +"  today:"+ day);
        if (ruleValueOneDay == 0) {
            redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_ONE_DAY, powerOff.getAccountID(), day);
            //奖励每天开机的谢尔值
            //-------
           // userGradeService.toReward(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_ONE_DAY);
            pushMessage(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_ONE_DAY);
        } else {
            if ((day - ruleValueOneDay) >= 1) {
                redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_ONE_DAY, powerOff.getAccountID(), day);
                //奖励每天开机的谢尔值
                //-----
               // userGradeService.toReward(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_ONE_DAY);
               pushMessage(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_ONE_DAY);
            }

        }
        //-------------------判断每天开机的情况  end ----------------


        //---------------2.判断连续三天开机的情况  begin ---------------
        Long ruleValueThreeDay = redisService.getHRuleValue(ConstantsUtil.EveryDay.POWEROFF_THREE_DAY, powerOff.getAccountID(), null);
        log.info("ruleValueThreeDay:" + ruleValueThreeDay +"  today:"+ day);
        if (ruleValueThreeDay == 0) {
            //初始化值
            // day *1000 +1  说明:  day 用于计算时间  *1000 +1 为 0001 意为第一天开机 后面联系开机为0002 以此类推
            redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_THREE_DAY, powerOff.getAccountID(), day * 1000 + 1);

        } else {
            //判断连续开机到第三天  ==2 是因为 连续开机的第一天是包含当天
            if ((day - ruleValueThreeDay / segmNum) == 2  && ruleValueThreeDay % segmNum == 2) {
                redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_THREE_DAY, powerOff.getAccountID(), day * 1000 + 1);
                //奖励连续三天开机的谢尔值
                //-----
               // userGradeService.toReward(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_THREE_DAY);
                pushMessage(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_THREE_DAY);
            } else {
                //如果当前的天数  - redis的天数 = 0 则表时是连续的第二天开机
                if ((day - ruleValueThreeDay / segmNum) - ruleValueThreeDay % segmNum == 0) {
                    redisService.operatingHRuleValue(ConstantsUtil.EveryDay.POWEROFF_THREE_DAY, powerOff.getAccountID(), 1L);
                } else if ((day - ruleValueThreeDay / segmNum) - ruleValueThreeDay % segmNum >= 1) {
                    redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_THREE_DAY, powerOff.getAccountID(), day * 1000 + 1);
                }
            }
        }

        //---------------判断连续三天开机的情况  end---------------


        //---------------3.判断连续五天开机的情况  begin ---------------
        Long ruleValueFiveDay = redisService.getHRuleValue(ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY, powerOff.getAccountID(), null);
        log.info("ruleValueFiveDay:" + ruleValueFiveDay +"  today:"+ day);
        if (ruleValueFiveDay == 0) {
            //初始化值
            // day *1000 +1  说明:  day 用于计算时间  *1000 +1 为 0001 意为第一天开机 后面联系开机为0002 以此类推
            redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY, powerOff.getAccountID(), day * 1000 + 1);

        } else {
            //判断连续开机到第第五天  ==4 是因为 连续开机的第一天是包含当天
            if ((day - ruleValueFiveDay /segmNum) == 4 && ruleValueFiveDay % 1000 == 4) {
                redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY, powerOff.getAccountID(), day * 1000 + 1);
                //奖励连续五天开机的谢尔值
               // userGradeService.toReward(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY);
                //-----
                pushMessage(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY);
            } else {
                //如果当前的天数  - redis的天数 = 0 则表时是连续的第二天开机
                if ((day - ruleValueFiveDay /segmNum) - ruleValueFiveDay % 1000 == 0) {
                    redisService.operatingHRuleValue(ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY, powerOff.getAccountID(), 1L);
                } else if ((day - ruleValueThreeDay /segmNum) - ruleValueThreeDay % 1000 >= 1) {
                    redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY, powerOff.getAccountID(), day * 1000 + 1);
                }
            }
        }
        //----------------判断连续五天开机的情况  end------------------


        //---------------4.判断连续七天开机的情况  begin ---------------
        Long ruleValueSevenDay = redisService.getHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, powerOff.getAccountID(), null);
        log.info("ruleValueSevenDay:" + ruleValueSevenDay +"  today:"+ day);
        if (ruleValueSevenDay == 0) {
            //初始化值
            // day *1000 +1  说明:  day 用于计算时间  *1000 +1 为 0001 意为第一天开机 后面联系开机为0002 以此类推
            redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, powerOff.getAccountID(), day * 1000 + 1);

        } else {
            //判断连续开机到第第七天  ==6 是因为 连续开机的第一天是包含当天
            if ((day - ruleValueSevenDay /segmNum) == 6 && ruleValueSevenDay % 1000 == 6) {
                redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, powerOff.getAccountID(), day * 1000 + 1);

                //奖励连续七天开机的谢尔值
               // userGradeService.toReward(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY);
                pushMessage(powerOff.getAccountID(), powerOff.getImei(), ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY);
                //-----

            } else {
                //如果当前的天数  - redis的天数 = 0 则表时是连续的第二天开机
                if ((day - ruleValueSevenDay /segmNum ) - ruleValueSevenDay % 1000 == 0) {
                    redisService.operatingHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, powerOff.getAccountID(), 1L);
                } else if ((day - ruleValueThreeDay /segmNum) - ruleValueThreeDay % 1000 >= 1) {
                    redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, powerOff.getAccountID(), day * 1000 + 1);
                }
            }
        }
        //----------------判断连续五天开机的情况  end------------------
    }

    /**
     * 生产 帐户规则奖励消息
     * @param accountID  帐户ID
     * @param IMEI        IMEI号
     * @param ruleCode  规则代码
     * @throws Exception
     */
    public void pushMessage(String accountID, String IMEI, String ruleCode) throws Exception {
        RuleRewardMq ruleRewardMq = new RuleRewardMq(accountID, IMEI, ruleCode);
        String message = me.daoke.common.mq.util.JsonMapper.toJson(ruleRewardMq, true);
        log.info("pushMessage:" + message);
        ruleCodeDirect.publish(routRuleCodeReward, message);


    }



    /**
     * 生产 帐户规则奖励消息
     * @param accountID  帐户ID
     * @param IMEI        IMEI号
     * @param ruleCode  规则代码
     * @throws Exception
     */
    public void pushMessageTest(String accountID, String IMEI, String ruleCode) throws Exception {
        RuleRewardMq ruleRewardMq = new RuleRewardMq(accountID, IMEI, ruleCode);
        String message = me.daoke.common.mq.util.JsonMapper.toJson(ruleRewardMq, true);

        ruleCodeDirect.publish(routRuleCodeReward, message);


    }





}
