package me.daoke.common.mq;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * User: chenlong
 * Date: 2015/4/15
 * Time: 10:40
 * 采集的GPS数据
 */
public class GPSData implements Serializable {


    private static final long serialVersionUID = -1070679872229187819L;


    private String IMEI;

    //private String model ;

    private String tokenCode;

    private String accountID;

    List<Location> locations;


    public GPSData() {
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "GPSData{" +
                "IMEI='" + IMEI + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", accountID='" + accountID + '\'' +
                ", locations=" + locations +
                '}';
    }
}
