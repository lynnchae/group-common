package me.daoke.common.mq.util;

import com.rabbitmq.client.*;
import org.apache.log4j.Logger;

/**
 *
 * 连接工具类
 *
 * User: chenlong
 * Date: 2014/12/18
 * Time: 19:20
 */
public abstract class ConnectionUtil {

    /**
     * 日志
     */
    protected Logger logger = Logger.getLogger(ConnectionUtil.class);

    private static ConnectionFactory factory = new ConnectionFactory(); //创建连接工厂

    /**
     * 配置信息
     */
    protected MQConfig mqConfig;

    /**
     * 通道
     */
    public Channel channel;


    protected ConnectionUtil(MQConfig mqConfig) {
        this.mqConfig = mqConfig;
        //初始化连接
        Connection();
    }

    /**
     * 建立连接
     */
    private void Connection() {
        try {
            factory.setVirtualHost(mqConfig.getVirtuaHost());
            factory.setUsername(mqConfig.getUserName());
            factory.setPassword(mqConfig.getPassWord());
            Address[] addresses = new Address[]{new Address(mqConfig.getHost1(), mqConfig.getPort1()),new Address(mqConfig.getHost2(),mqConfig.getPort2())};
            Connection connection = factory.newConnection(addresses);
            connection.addShutdownListener(new ShutdownListener() {
                @Override
                public void shutdownCompleted(ShutdownSignalException cause) {
                  System.out.println("连接中断");
                    logger.info("断开连接");
                }
            });
            channel = connection.createChannel();
            callBack();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * 供子类实现
     */
    protected abstract void callBack();
}
