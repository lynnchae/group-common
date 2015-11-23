package me.daoke.common.mq.client;

import me.daoke.common.mq.util.ConnectionUtil;
import me.daoke.common.mq.util.MQConfig;

import java.io.IOException;

/**
 * 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
 *
 * User: chenlong
 * Date: 2014/12/19
 * Time: 17:22
 */
public class EmitDirect extends ConnectionUtil {

    /**
     * 是否持久化
     */
    protected static final boolean durable = true;
    /**
     * 提交的模式
     *  任何发送到Direct Exchange的消息都会被转发到RouteKey中指定的Queue。
         1.一般情况可以使用rabbitMQ自带的Exchange：”"(该Exchange的名字为空字符串)。
         2.这种模式下不需要将Exchange进行任何绑定(binding)操作
         3.消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
         4.如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。
     */
    protected static final String type = "direct";

    public EmitDirect(MQConfig mqConfig) {
        super(mqConfig);
    }

    @Override
    protected void callBack() {
        if (logger.isInfoEnabled())
            logger.info("EmitDirect callback");
        try {
            channel.exchangeDeclare(mqConfig.getExchangeName(), type, durable);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }


    /**
     *生产消息
     *
     * @param routingKey 路由key主键
     * @param message  消息主体，如果第一次发送失败，会再发送一次，如果第二次失败就会抛出Exception，如果抛出
     *                这个异常之后，需要自己做处理，如将日志保存在本地的文件系统中等等。
     * @throws Exception
     */
    public void publish(String routingKey,String message) throws Exception{
        try {
            channel.basicPublish(mqConfig.getExchangeName(),routingKey,null,message.getBytes());
        } catch (IOException e) {
            //第一次发送失败，等待连接重连之后发送第二次
            e.printStackTrace();
            channel.basicPublish(mqConfig.getExchangeName(), routingKey, null, message.getBytes());
        }
    }


    /**
     *生产消息
     *
     * @param routingKey 路由key主键
     * @param message  消息主体，如果第一次发送失败，会再发送一次，如果第二次失败就会抛出Exception，如果抛出
     *                这个异常之后，需要自己做处理，如将日志保存在本地的文件系统中等等。
     * @throws Exception
     */
    public void publish(String routingKey,byte[] message) throws Exception{
        try {
            channel.basicPublish(mqConfig.getExchangeName(),routingKey,null,message);
        } catch (IOException e) {
            //第一次发送失败，等待连接重连之后发送第二次
            e.printStackTrace();
            channel.basicPublish(mqConfig.getExchangeName(), routingKey, null, message);
        }
    }


}
