package me.daoke.driving.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 获取Spring的ApplicationContext的单例类
 * @author  chenlong
 * 2015-04-11
 */
public class ApplicationContextLoader {

    private final String CONFIG_FILES = "classpath*:spring/context*.xml";

    private static ApplicationContext context;

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextLoader.class);

    private final static ApplicationContextLoader loader = new ApplicationContextLoader();

    private ApplicationContextLoader() {
        load();
    }

    private void load(){
        context = new ClassPathXmlApplicationContext(CONFIG_FILES);
    }

    public static ApplicationContextLoader getInstance(){
       return loader;
    }

    public ApplicationContext getContext(){
        assertContextInjected();
        return context;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        assertContextInjected();
        return (T) context.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return context.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clear() {
        logger.debug("清除SpringContextHolder中的ApplicationContext:" + context);
        context = null;
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private void assertContextInjected() {
        if (context == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }

}
