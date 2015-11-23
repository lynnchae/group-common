package me.daoke.driving.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * User: chenlong
 * Date: 2015/6/17
 * Time: 10:37
 */
public class UserBeanProxy implements InvocationHandler {

    private Object targetObject;

    public  UserBeanProxy(Object targetObject)
    {
        this.targetObject = targetObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        UserBeanImpl userBean = (UserBeanImpl) targetObject;
        String userName = userBean.getUserName();
        Object result = null;

        //权限判断
        if(userName != null && !"".equals(userName))
        {
            result = method.invoke(targetObject, args);
        }

        return result;
    }

}
