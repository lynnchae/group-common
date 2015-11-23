package me.daoke.driving.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/*用户等级规则
 * Created by chenmaomao on 2015/4/1.
 */
public class RatingRules implements Serializable {
    /**
     * 记录号
     */
    private Integer recordID;
    /**
     * 谢尔值
     */
    private Integer rochelle;
    /**
     * 称号
     */
    private String title;
    /**
     * 奖励上限
     */
    private  Integer rewardLimit;
    /**
     * 是否有效 1有效 0无效
     */
    private Integer isValid;
    /**
     * 创建时间
     */
    private Timestamp createDate;
    /**
     * 修改时间
     */
    private Timestamp modifyDate;

    public RatingRules() {
    }

    public RatingRules(Integer recordID, Integer rochelle, String title, Integer rewardLimit, Integer isValid, Timestamp createDate, Timestamp modifyDate) {
        this.recordID = recordID;
        this.rochelle = rochelle;
        this.title = title;
        this.rewardLimit = rewardLimit;
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

    public Integer getRochelle() {
        return rochelle;
    }

    public void setRochelle(Integer rochelle) {
        this.rochelle = rochelle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRewardLimit() {
        return rewardLimit;
    }

    public void setRewardLimit(Integer rewardLimit) {
        this.rewardLimit = rewardLimit;
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
        return "RatingRules{" +
                "recordID=" + recordID +
                ", rochelle=" + rochelle +
                ", title='" + title + '\'' +
                ", rewardLimit=" + rewardLimit +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
