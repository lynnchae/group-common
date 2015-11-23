package me.daoke.driving.entity;

import java.io.Serializable;

/**
 * User: chenlong
 * Date: 2015/5/14
 * Time: 17:14
 */
public class RuleRewardMq implements Serializable{


    private String accountID;


    private String IMEI;

    private String ruleCode;

    public RuleRewardMq() {
    }

    public RuleRewardMq(String accountID, String IMEI, String ruleCode) {
        this.accountID = accountID;
        this.IMEI = IMEI;
        this.ruleCode = ruleCode;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    @Override
    public String toString() {
        return "RewardRochelleMq{" +
                "accountID='" + accountID + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", ruleCode='" + ruleCode + '\'' +
                '}';
    }
}
