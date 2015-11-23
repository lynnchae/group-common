package me.daoke.driving.testrule;

import me.daoke.driving.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chenlong
 * Date: 2015/5/26
 * Time: 14:03
 */
public class TestMileage1 extends BaseTest {


    Logger log = LoggerFactory.getLogger(TestMileage1.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplateDateCenter;

    @Autowired
    private JdbcTemplate jdbcTemplateClientMirrtalk;


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


    int pageSize = 1000;

    @Test
    public void calRoll() throws InterruptedException {
        //查询开关机总行数据
//        totalCount = this.jdbcTemplateDateCenter.queryForInt("\n" +
//              "select count(*) from \n" +
//               "(SELECT  p.accountID FROM daoke_powerOffInfo as p group by  accountID   ) t");


        //List<Map<String, Object>> listPowerA = this.jdbcTemplateDateCenter.queryForList("SELECT  p.accountID FROM daoke_powerOffInfo as p where accountID ='zdfeqE74Vi' group by  accountID ");
        List<Map<String, Object>> listPowerA = jdbcTemplateDateCenter.queryForList("SELECT  p.accountID FROM daoke_powerOffInfo as p where accountID ='3XhS4kgGVl' group by  accountID ");

        for (int i = 0; i < listPowerA.size(); i++) {
            final String accountID = listPowerA.get(i).get("accountID").toString();


            int nicknameC = jdbcTemplateClientMirrtalk.queryForInt("select count(*) from  userInfo where  LENGTH(nickname) >0 and accountID = ?", accountID);
            if (nicknameC > 0) {
                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                        "VALUES(?,?,?,1,1)", new Object[]{accountID, "settingNickName", 20});
            }

            //查询当前帐户的开关机信息
            List<Map<String, Object>> listPowerB = jdbcTemplateDateCenter.queryForList(
                    "select DISTINCT a.accountID,FROM_UNIXTIME(a.powerOnTime,'%Y%m%d') as dat1 from daoke_powerOffInfo as a where a.accountID =?  order by  dat1", accountID);

            int three = 0;
            int five = 0;
            int seven = 0;
            int befThreeDay = 0;
            int rochell = 0;

            jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                    "VALUES(?,?,?,1,1)", new Object[]{accountID, "bindWEME", 30});


            //计算开关机
            for (int j = 0; j < listPowerB.size(); j++) {
                Map mapPower = listPowerB.get(j);


                Integer dat1 = Integer.valueOf(mapPower.get("dat1").toString());

                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                        "VALUES(?,?,?,1,1)", new Object[]{accountID, "powerOneDay", 10});
                rochell += 10;
                log.info("每天开机奖励谢尔值 dat1:" + dat1);
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
                    } else {
                        seven = 1;
                    }
                    if (three == 2) {
                        log.info("连续3天开机奖励谢尔值 dat1:" + dat1);
                        jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                "VALUES(?,?,?,1,1)", new Object[]{accountID, "powerThreeDay", 20});
                        rochell += 20;
                    }
                    if (five == 4) {
                        log.info("连续5天开机奖励谢尔值 dat1:" + dat1);
                        jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                "VALUES(?,?,?,1,1)", new Object[]{accountID, "powerFiveDay", 30});
                        rochell += 30;
                    }
                    if (seven == 6) {
                        log.info("连续7天开机奖励谢尔值 dat1:" + dat1);
                        jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                "VALUES(?,?,?,1,1)", new Object[]{accountID, "powerSevenDay", 50});
                        rochell += 50;
                    }
                }
                befThreeDay = dat1;
            }

            List<Map<String, Object>> listTravel = jdbcTemplateDateCenter.queryForList("select a.accountID,FROM_UNIXTIME(a.createTime,'%Y%m%d') as dat1,FROM_UNIXTIME(a.createTime,'%Y%m') as dat2,FROM_UNIXTIME(a.createTime,'%V') as dat3,sum(a.sumMileage ) as sumM from daoke_userTravelInfo as a where a.accountID =? group by  accountID,dat1", accountID);
            int month = 0;
            int week = 0;
            int weekSumMileage = 0;
            int monthSumMileage = 0;
            boolean mileage300000 = false;

            boolean mileage1000000 = false;

            boolean mileage3000000 = false;

            boolean mileageWeek1 = false;
            //计算里程
            for (int k = 0; k < listTravel.size(); k++) {
                Map mapTravel = listTravel.get(k);
                Integer day1 = Integer.valueOf(mapTravel.get("dat1").toString());

                Integer month1 = Integer.valueOf(mapTravel.get("dat2").toString());

                Integer week1 = Integer.valueOf(mapTravel.get("dat3").toString());

                Integer sumM = Integer.valueOf(mapTravel.get("sumM").toString());

                if (sumM >= 10000) {
                    log.info("完成每日驾驶10公里奖励谢尔值 dat1:" + day1);
                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                            "VALUES(?,?,?,1,1)", new Object[]{accountID, "drive10KmDay", 10});
                    rochell += 10;
                }

                if (sumM >= 30000) {
                    log.info("完成每日驾驶30公里奖励谢尔值 dat1:" + day1);
                    jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                            "VALUES(?,?,?,1,1)", new Object[]{accountID, "drive30KmDay", 30});
                    rochell += 30;
                }

                if (week == week1 || week == 0) {
                    weekSumMileage += sumM;
                    if (weekSumMileage >= 200000 && !mileageWeek1) {
                        mileageWeek1 = true;
                        log.info("完成本周行驶200公里奖励谢尔值 dat1:" + day1 + " weekSumMileage :" + monthSumMileage);
                        jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                "VALUES(?,?,?,1,1)", new Object[]{accountID, "drive200KmWeekly", 50});
                        rochell += 50;
                    }
                } else {
                    weekSumMileage = sumM;
                }

                if (month == month1 || month == 0) {
                    monthSumMileage += sumM;

                    if (monthSumMileage > 300000 && !mileage300000) {
                        mileage300000 = true;
                        log.info("完成本月行驶300公里奖励谢尔值 dat1:" + day1 + " monthSumMileage :" + monthSumMileage);
                        jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                "VALUES(?,?,?,1,1)", new Object[]{accountID, "drive300KmMonth", 30});
                        rochell += 30;
                    }

                    if (monthSumMileage > 1000000 && !mileage1000000) {
                        mileage1000000 = true;
                        log.info("完成本月行驶1000公里奖励谢尔值 dat1:" + day1 + " monthSumMileage :" + monthSumMileage);
                        jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                "VALUES(?,?,?,1,1)", new Object[]{accountID, "drive1000KmMonth", 100});
                        rochell += 100;
                    }

                    if (monthSumMileage > 3000000 && !mileage3000000) {
                        mileage3000000 = true;
                        log.info("完成本月行驶3000公里奖励谢尔值 dat1:" + day1 + " monthSumMileage :" + monthSumMileage);
                        jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                                "VALUES(?,?,?,1,1)", new Object[]{accountID, "drive3000KmMonth", 300});
                        rochell += 300;
                    }

                } else {
                    monthSumMileage = sumM;
                }

                month = month1;
                week = week1;
            }

            int ruleGrade = getGrade(rochell);
            if (ruleGrade >= 10 && ruleGrade < 20) {
                log.info("完成等级达到10级奖励谢尔值 ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                        "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade10", 100});
                rochell += 100;
                ruleGrade = getGrade(rochell);
            }

            if (ruleGrade >= 20 && ruleGrade < 30) {
                log.info("完成等级达到20级奖励谢尔值  ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                        "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade20", 500});
                rochell += 500;
                ruleGrade = getGrade(rochell);
            }

            if (ruleGrade >= 30 && ruleGrade < 40) {
                log.info("完成等级达到30级奖励谢尔值  ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                        "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade30", 1500});
                rochell += 1500;
                ruleGrade = getGrade(rochell);
            }

            if (ruleGrade >= 40 && ruleGrade < 50) {
                log.info("完成等级达到40级奖励谢尔值  ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                        "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade40", 3000});
                rochell += 3000;
                ruleGrade = getGrade(rochell);
            }

            if (ruleGrade >= 50 && ruleGrade < 60) {
                log.info("完成等级达到50级奖励谢尔值  ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                        "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade50", 5000});
                rochell += 5000;
                ruleGrade = getGrade(rochell);
            }

            if (ruleGrade >= 60) {
                log.info("完成等级达到60级奖励谢尔值 ruleGrade :" + ruleGrade + "  rochell:" + rochell);
                jdbcTemplate.update("  INSERT INTO userRochelleDetail(accountID,ruleCode,rochelle,receiveStatus,isValid) " +
                        "VALUES(?,?,?,1,1)", new Object[]{accountID, "grade60", 10000});
                rochell += 10000;
            }


            ruleGrade = getGrade(rochell);


            List<Map<String, Object>> userGradeList = jdbcTemplate.queryForList("select accountID,grade,rochelle from userGrade where isValid =1 and   accountID = ?", accountID);

            if (userGradeList == null || userGradeList.size() < 1) {
                jdbcTemplate.update(" INSERT INTO userGrade(accountID,grade,rochelle,isValid) VALUES(?,?,?,1)", accountID, ruleGrade, rochell);
            } else {
                jdbcTemplate.update(" UPDATE userGrade SET grade=?,rochelle=? where isValid =1 and accountID= ?",
                        new Object[]{ruleGrade, rochell, accountID});
            }
//
        }
    }

    ////  ----


    public int getGrade(Integer rochell) {
        List<Map<String, Object>> gradeList = this.jdbcTemplate.queryForList("select recordID,rochelle from ratingRules where rochelle <= ?   order by  rochelle  desc limit 1", rochell);
        if (gradeList != null && gradeList.size() > 0) {

            return Integer.valueOf(gradeList.get(0).get("recordID").toString());
        }
        return 0;

    }

    @Test
    public void testA() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 1000; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aaa  " + Thread.currentThread().getName());
                }
            });
        }
        Thread.sleep(200000000);
        executorService.shutdown();


    }


    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 1000; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aaa  " + Thread.currentThread().getName());
                }
            });
        }
        executorService.shutdown();

    }
}