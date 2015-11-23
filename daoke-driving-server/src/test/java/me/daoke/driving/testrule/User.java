package me.daoke.driving.testrule;

import java.io.Serializable;

/**
 * User: chenlong
 * Date: 2015/4/8
 * Time: 15:31
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1267719235225203410L;

    private String uid;

    private String address;


    public User() {
    }


    public User(String uid, String address) {
        this.uid = uid;
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
