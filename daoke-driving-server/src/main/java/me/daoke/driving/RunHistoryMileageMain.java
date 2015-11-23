package me.daoke.driving;

import me.daoke.driving.common.config.ApplicationContextLoader;
import me.daoke.driving.common.config.RedisVariable;
import me.daoke.driving.common.service.RedisService;
import me.daoke.driving.util.CalUtil;
import me.daoke.driving.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chenlong
 * Date: 2015/6/5
 * Time: 11:23
 */
public class RunHistoryMileageMain {


    Logger log = LoggerFactory.getLogger(RunHistoryMileageMain.class);



    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotalPage() {
        if (this.totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount / pageSize + 1;
        }
    }


    int totalCount;


    int pageSize = 2000;

    private static ApplicationContextLoader loader;


    private void initSpring() {
        loader = ApplicationContextLoader.getInstance();
    }


    public static void main(String args[]) {

        RunHistoryMileageMain runHistoryMileageMain = new RunHistoryMileageMain();

        runHistoryMileageMain.initSpring();
        JdbcTemplate  jdbcTemplate = loader.getBean("jdbcTemplate");
        JdbcTemplate   jdbcTemplateDateCenter = loader.getBean("jdbcTemplateDateCenter");

        JdbcTemplate  jdbcTemplateClientMirrtalk = loader.getBean("jdbcTemplateClientMirrtalk");

        RedisService  redisService = loader.getBean("redisService");
        try {
            runHistoryMileageMain.calRoll(jdbcTemplate, jdbcTemplateDateCenter, jdbcTemplateClientMirrtalk, redisService);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public  void calRoll(final JdbcTemplate  jdbcTemplate,final  JdbcTemplate   jdbcTemplateDateCenter,final    JdbcTemplate  jdbcTemplateClientMirrtalk ,
                       final    RedisService  redisService ) throws InterruptedException {
        //查询开关机总行数据
        totalCount = jdbcTemplateDateCenter.queryForInt("select count(*) from daoke_AccountID_Date_bak_20150605 where LENGTH(accountID) >0  ");

        //  totalCount = 40070;
        int totalPage = getTotalPage();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int b = 0; b < totalPage; b++) {

            final int q = b;

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //List<Map<String, Object>> listPowerA = this.jdbcTemplateDateCenter.queryForList("SELECT  p.accountID FROM daoke_powerOffInfo as p where accountID ='zdfeqE74Vi' group by  accountID ");
                    List<Map<String, Object>> listPowerA = jdbcTemplateDateCenter.queryForList("select accountID from daoke_AccountID_Date_bak_20150605 where  LENGTH(accountID) >0    limit ?,? order by accountID"
                            , q * pageSize, (q + 1) * pageSize);

                    for (int i = 0; i < listPowerA.size(); i++) {
                        final String accountID = listPowerA.get(i).get("accountID").toString();

                        int nicknameC = jdbcTemplateClientMirrtalk.queryForInt("select count(*) from  userInfo where  LENGTH(nickname) >0 and accountID = ?", accountID);
                        if (nicknameC > 0) {
                            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                    "VALUES(?,?,?,1,1)", new Object[]{accountID, "settingNickName", 20});
                        }
                        //查询当前帐户的开关机信息
                        List<Map<String, Object>> listPowerB = jdbcTemplateDateCenter.queryForList(
                                "select  a.accountID, dat1 from daoke_powerOffInfo_date_bak_20150605 as a where LENGTH(accountID) >0 and a.accountID =?  order by  dat1", accountID);

                        int three = 1;
                        int five = 1;
                        int seven = 1;
                        int befThreeDay = 0;
                        int rochell = 0;

                        jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                "VALUES(?,?,?,1,1)", new Object[]{accountID, "bindWEME", 30});

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                        int monthRochelle = 0;
                        //计算开关机
                        for (int j = 0; j < listPowerB.size(); j++) {
                            Map mapPower = listPowerB.get(j);


                            Integer dat1 = Integer.valueOf(mapPower.get("dat1").toString());

                            Date date = null;
                            try {
                                date = sdf.parse(mapPower.get("dat1").toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                    "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "powerOneDay", 10, date});
                            rochell += 10;
                            log.info("每天开机奖励谢尔值 dat1:" + dat1);

                            Long day = date.getTime() / (24 * 60 * 60 * 1000);
                            if (dat1 - befThreeDay == 1) {
                                if (three < 3) {
                                    three++;
                                } else {
                                    three = 1;
                                }
                                if (five < 5) {
                                    five++;
                                } else {
                                    five = 1;
                                }
                                if (seven < 7) {
                                    seven++;
                                    if(seven ==1){
                                        redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, accountID, day * 1000 + 1);
                                    }else {
                                        redisService.operatingHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, accountID, 1L);
                                    }
                                } else {
                                    redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, accountID, day * 1000 + 1);
                                    seven = 1;
                                }
                                if (three == 3) {
                                    log.info("连续3天开机奖励谢尔值 dat1:" + dat1);
                                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                            "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "powerThreeDay", 20, date});
                                    rochell += 20;
                                    if (dat1 >= 20150601) {
                                        monthRochelle += 20;
                                    }
                                }
                                if (five == 5) {
                                    log.info("连续5天开机奖励谢尔值 dat1:" + dat1);
                                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                            "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "powerFiveDay", 30, date});
                                    rochell += 30;
                                    if (dat1 >= 20150601) {
                                        monthRochelle += 30;
                                    }
                                }
                                if (seven == 7) {
                                    log.info("连续7天开机奖励谢尔值 dat1:" + dat1);
                                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                            "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "powerSevenDay", 50, date});
                                    rochell += 50;
                                    if (dat1 >= 20150601) {
                                        monthRochelle += 50;
                                    }
                                }

                            }else{
                                three = 1;
                                five = 1;
                                seven = 1;
                                redisService.setHRuleValue(ConstantsUtil.EveryDay.POWEROFF_SEVEN_DAY, accountID, day * 1000 + 1);
                            }
                            befThreeDay = dat1;
                        }

                        List<Map<String, Object>> listTravel = jdbcTemplateDateCenter.queryForList("select a.accountID, dat1,dat2,dat3, sumM from daoke_userTravelInfo_date_bak_20150605 as a where LENGTH(a.accountID) >0 and a.accountID =? ", accountID);
                        int month = 0;
                        int week = 0;
                        int weekSumMileage = 0;
                        int monthSumMileage = 0;
                        boolean mileage300000 = false;

                        boolean mileage1000000 = false;

                        boolean mileage3000000 = false;

                        boolean mileageWeek1 = false;

                        boolean dayMilleage100KMOne = false;
                        //计算里程
                        for (int k = 0; k < listTravel.size(); k++) {
                            Map mapTravel = listTravel.get(k);
                            Integer day1 = Integer.valueOf(mapTravel.get("dat1").toString());

                            Integer month1 = Integer.valueOf(mapTravel.get("dat2").toString());

                            Integer week1 = Integer.valueOf(mapTravel.get("dat3").toString());

                            Integer sumM = Integer.valueOf(mapTravel.get("sumM").toString());
                            if(sumM == null || sumM <1){
                               continue;
                            }


                            Date date = null;
                            try {
                                date = sdf.parse(mapTravel.get("dat1").toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            if (sumM >= 10000) {
                                log.info("完成每日驾驶10公里奖励谢尔值 dat1:" + day1);
                                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                        "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "drive10KmDay", 10, date});
                                rochell += 10;
                                if (day1 >= 20150601) {
                                    monthRochelle += 10;
                                }
                            }

                            if (sumM >= 30000) {
                                log.info("完成每日驾驶30公里奖励谢尔值 dat1:" + day1);
                                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                        "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "drive30KmDay", 30, date});
                                rochell += 30;
                                if (day1 >= 20150601) {
                                    monthRochelle += 30;
                                }
                            }

                            if (sumM >= 100000 && !dayMilleage100KMOne) {
                                log.info("完成单日日驾驶100公里奖励谢尔值 dat1:" + day1);
                                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                        "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "exceed100DayOnce", 100, date});
                                rochell += 100;
                                dayMilleage100KMOne = true;
                            }


                            if (week == week1 || week == 0) {
                                weekSumMileage += sumM;
                                if (weekSumMileage >= 200000 && !mileageWeek1) {
                                    mileageWeek1 = true;
                                    log.info("完成本周行驶200公里奖励谢尔值 dat1:" + day1 + " weekSumMileage :" + monthSumMileage);
                                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                            "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "drive200KmWeekly", 50, date});
                                    rochell += 50;
                                    if (day1 >= 20150601) {
                                        monthRochelle += 50;
                                    }
                                }
                                if (week1 == CalUtil.getWeekOfYear() && month1 > 201506) {
                                    redisService.operatingHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_WEEKLY, accountID, Long.valueOf(sumM));
                                }
                            } else {
                                weekSumMileage = sumM;
                                if (week1 == CalUtil.getWeekOfYear()  && month1 > 201506 ) {
                                    redisService.setHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_WEEKLY, accountID, week1 * 100000000L + weekSumMileage);
                                }
                            }

                            if (month == month1 || month == 0) {
                                monthSumMileage += sumM;


                                if (monthSumMileage > 300000 && !mileage300000) {
                                    mileage300000 = true;
                                    log.info("完成本月行驶300公里奖励谢尔值 dat1:" + day1 + " monthSumMileage :" + monthSumMileage);
                                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                            "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "drive300KmMonth", 30, date});
                                    rochell += 30;
                                    if (day1 >= 20150601) {
                                        monthRochelle += 30;
                                    }
                                }

                                if (monthSumMileage > 1000000 && !mileage1000000) {
                                    mileage1000000 = true;
                                    log.info("完成本月行驶1000公里奖励谢尔值 dat1:" + day1 + " monthSumMileage :" + monthSumMileage);
                                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                            "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "drive1000KmMonth", 100, date});
                                    rochell += 100;
                                    if (day1 >= 20150601) {
                                        monthRochelle += 100;
                                    }
                                }

                                if (monthSumMileage > 3000000 && !mileage3000000) {
                                    mileage3000000 = true;
                                    log.info("完成本月行驶3000公里奖励谢尔值 dat1:" + day1 + " monthSumMileage :" + monthSumMileage);
                                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid,createDate) " +
                                            "VALUES(?,?,?,1,1,?)", new Object[]{accountID, "drive3000KmMonth", 300, date});
                                    rochell += 300;
                                    if (day1 >= 20150601) {
                                        monthRochelle += 300;
                                    }
                                }

                                if (month1 == 201506) {
                                    redisService.operatingHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_MONTH, accountID, Long.valueOf(sumM));
                                }

                            } else {
                                mileage300000 = false;
                                mileage1000000 = false;
                                mileage3000000 = false;
                                monthSumMileage = sumM;
                                if (month1 == 201506) {
                                    redisService.setHRuleValue(RedisVariable.Mileage.DRIVER_MILEAGE_MONTH, accountID, 5 * 100000000L + sumM);
                                }
                            }
                            month = month1;
                            week = week1;
                        }

                        int ruleGrade = getGrade(rochell,jdbcTemplate);





                        if (ruleGrade >= 10 ) {
                            log.info("完成等级达到10级奖励谢尔值 ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                    "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade10", 100});
                            rochell += 100;
                            ruleGrade = getGrade(rochell,jdbcTemplate);
                        }

                        if (ruleGrade >= 20 ) {
                            log.info("完成等级达到20级奖励谢尔值  ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                    "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade20", 500});
                            rochell += 500;
                            ruleGrade = getGrade(rochell,jdbcTemplate);
                        }

                        if (ruleGrade >= 30 ) {
                            log.info("完成等级达到30级奖励谢尔值  ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                    "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade30", 1500});
                            rochell += 1500;
                            ruleGrade = getGrade(rochell,jdbcTemplate);
                        }

                        if (ruleGrade >= 40 ) {
                            log.info("完成等级达到40级奖励谢尔值  ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                    "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade40", 3000});
                            rochell += 3000;
                            ruleGrade = getGrade(rochell,jdbcTemplate);
                        }

                        if (ruleGrade >= 50 && ruleGrade < 60) {
                            log.info("完成等级达到50级奖励谢尔值  ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                    "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade50", 5000});
                            rochell += 5000;
                            ruleGrade = getGrade(rochell,jdbcTemplate);
                        }

                        if (ruleGrade >= 60) {
                            log.info("完成等级达到60级奖励谢尔值 ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                    "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade60", 10000});
                            rochell += 10000;
                        }


                        ruleGrade = getGrade(rochell,jdbcTemplate);


                        List<Map<String, Object>> userGradeList = jdbcTemplate.queryForList("select accountID,grade,rochelle from userGrade where isValid =1 and   accountID = ?", accountID);

                        if (userGradeList == null || userGradeList.size() < 1) {
                            jdbcTemplate.update(" INSERT INTO userGrade(accountID,grade,rochelle,isValid,monthRochelle) VALUES(?,?,?,1,?)", accountID, ruleGrade, rochell, monthRochelle);
                        } else {
                            jdbcTemplate.update(" UPDATE userGrade SET grade=?,rochelle=?,monthRochelle =? where isValid =1 and accountID= ?",
                                    new Object[]{ruleGrade, rochell, monthRochelle, accountID});
                        }

                    }
                }

            });

            ////  ----
        }

        log.info("全部处理完毕 sleep");
        executorService.shutdown();

    }

    public int getGrade(Integer rochell ,JdbcTemplate jdbcTemplate) {
        List<Map<String, Object>> gradeList = jdbcTemplate.queryForList("select recordID,rochelle from ratingRules where rochelle <= ?   order by  rochelle  desc limit 1", rochell);
        if (gradeList != null && gradeList.size() > 0) {
            return Integer.valueOf(gradeList.get(0).get("recordID").toString());
        }
        return 0;

    }
}
