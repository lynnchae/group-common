package me.daoke.driving.aop;

import java.lang.reflect.Proxy;

/**
 * User: chenlong
 * Date: 2015/6/17
 * Time: 10:44
 */
public class ProxyExe {

    public static void main(String[] args)
    {
        System.out.println("Proved.............");
        UserBeanImpl targetObject = new UserBeanImpl("Bob Liang");
        UserBeanProxy proxy = new UserBeanProxy(targetObject);
        //生成代理对象
        UserBean object = (UserBean) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), proxy);
        object.addUser();

        System.out.println("NO Proved.............");
        targetObject = new UserBeanImpl("111");
        proxy = new UserBeanProxy(targetObject);
        //生成代理对象
        object = (UserBean)Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), proxy);
        object.addUser();
        object.deleteUser();

    }
}
