package me.daoke.driving.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**用户等级
 * Created by chenmaomao on 2015/4/1.
 */
public class UserGrade implements Serializable {
    /**
     * 记录号
     */
    private Integer recordID;
    /**
     * 用户ID
     */
    private String accountID;
    /**
     * 用户头像
     */
    private String userHeadName;
    /**
     * 昵称
     */
    private  String nickName;
    /**
     * 等级
     */
    private Integer grade;
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
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyDate;

    public UserGrade() {
    }

    public UserGrade(Integer recordID, String accountID, String userHeadName, String nickName, Integer grade, Integer rochelle, Integer isValid, Timestamp createDate, Timestamp modifyDate) {
        this.recordID = recordID;
        this.accountID = accountID;
        this.userHeadName = userHeadName;
        this.nickName = nickName;
        this.grade = grade;
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

    public String getUserHeadName() {
        return userHeadName;
    }

    public void setUserHeadName(String userHeadName) {
        this.userHeadName = userHeadName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "UserGrade{" +
                "recordID=" + recordID +
                ", accountID='" + accountID + '\'' +
                ", userHeadName='" + userHeadName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", grade=" + grade +
                ", rochelle=" + rochelle +
                ", isValid=" + isValid +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
