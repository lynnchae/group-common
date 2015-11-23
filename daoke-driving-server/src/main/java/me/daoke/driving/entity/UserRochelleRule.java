package me.daoke.driving.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**用户谢尔规则
 * Created by chenmaomao on 2015/4/1.
 */
public class UserRochelleRule implements Serializable {
    /**
     * id
     */
    private Integer ruleID;
    /**
     *规则名称
     */
    private String ruleName;
    /**
     * 可得谢尔值
     */
    private Integer rochell;
    /**
     * 是否有效
     */
    private Integer isValid;
    /**
     * 创建日期
     */
    private Timestamp createDate;
    /**
     * 修改时间
     */
    private Timestamp modifyDate;
    /**
     * 规则码
     */
    private  String ruleCode;

    public UserRochelleRule() {
    }

    public UserRochelleRule(Integer ruleID, String ruleName, Integer rochell, Integer isValid, Timestamp createDate, Timestamp modifyDate, String ruleCode) {
        this.ruleID = ruleID;
        this.ruleName = ruleName;
        this.rochell = rochell;
        this.isValid = isValid;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.ruleCode = ruleCode;
    }

    public Integer getRuleID() {
        return ruleID;
    }

    public void setRuleID(Integer ruleID) {
        this.ruleID = ruleID;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getRochell() {
        return rochell;
    }

    public void setRochell(Integer rochell) {
        this.rochell = rochell;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    @Override
    public String toString() {
        return "UserRochelleRule{" +
                "ruleID=" + ruleID +
                ", ruleName='" + ruleName + '\'' +
                ", rochell=" + rochell +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", ruleCode='" + ruleCode + '\'' +
                '}';
    }
}
