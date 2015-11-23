package me.daoke.driving.aop;

/**
 * User: chenlong
 * Date: 2015/6/17
 * Time: 10:36
 */
public class UserBeanImpl implements UserBean {

    private String user = null;
    public UserBeanImpl()
    {
    }
    public UserBeanImpl(String user)
    {
        this.user = user;
    }
    public String getUserName()
    {
        return user;
    }
    public void getUser()
    {
        System.out.println("this is getUser() method!");
    }

    public void setUser(String user)
    {
        this.user = user;
        System.out.println("this is setUser() method!");
    }
    public String addUser()
    {
        System.out.println("this is addUser() method!");
        return user;
    }

    public void updateUser()
    {
        System.out.println("this is updateUser() method!");
    }
    public void deleteUser()
    {
        System.out.println("this is deleteUser() method!");
    }
}
