package me.daoke.driving.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**用户谢尔明细
 * Created by chenmaomao on 2015/4/1.
 */
public class UserRochelleDetail implements Serializable {
    /**
     * 记录号
     */
    private  Integer recordID;
    /**
     * 用户ID
     */
    private  String accountID;
    /**
     * imei号
     */
    private  String imei;
    /**
     * 规则ID
     */
    private String ruleCode;
    /**
     * 谢尔值
     */
    private Integer rochelle;
    /**
     * 是否有效
     */
    private Integer isValid;
    /**
     * 创建时间
     */
    private Timestamp createDate;
    /**
     * 修改时间
     */
    private  Timestamp modifyDate;

    public UserRochelleDetail() {
    }

    public UserRochelleDetail(Integer recordID, String accountID, String imei, String ruleCode, Integer rochelle, Integer isValid, Timestamp createDate, Timestamp modifyDate) {
        this.recordID = recordID;
        this.accountID = accountID;
        this.imei = imei;
        this.ruleCode = ruleCode;
        this.rochelle = rochelle;
        this.isValid = isValid;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Integer getRecordID() {
        return recordID;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public Integer getRochelle() {
        return rochelle;
    }

    public void setRochelle(Integer rochelle) {
        this.rochelle = rochelle;
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

    @Override
    public String toString() {
        return "UserRochelleDetail{" +
                "recordID=" + recordID +
                ", accountID='" + accountID + '\'' +
                ", imei='" + imei + '\'' +
                ", ruleCode='" + ruleCode + '\'' +
                ", rochelle=" + rochelle +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
