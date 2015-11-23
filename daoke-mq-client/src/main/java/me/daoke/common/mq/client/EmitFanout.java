package me.daoke.common.mq.client;

import me.daoke.common.mq.util.ConnectionUtil;
import me.daoke.common.mq.util.MQConfig;

import java.io.IOException;

/**
 * 不处理路由键。你只需要简单的将队列绑定到交换机上。
 * 一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
 * 很像子网广播，每台子网内的主机都获得了一份复制的消息。Fanout交换机转发消息是最快的。
 * User: chenlong
 * Date: 2014/12/19
 * Time: 17:57
 */
public class EmitFanout extends ConnectionUtil {

    /**
     * 是否持久化
     */
    protected static final boolean durable = true;
    /**
     * 提交的模式
     * 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上。
         1.可以理解为路由表的模式
         2.这种模式不需要RouteKey
         3.这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定。
         4.如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃。
     */
    protected static final String type = "fanout";

    public EmitFanout(MQConfig mqConfig) {
        super(mqConfig);
    }


    @Override
    protected void callBack() {
        if (logger.isInfoEnabled())
            logger.info("EmitFanout callback");
        try {
            channel.exchangeDeclare(mqConfig.getExchangeName(), type, durable);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     *生产消息
     *
     * @param message  消息主体，如果第一次发送失败，会再发送一次，如果第二次失败就会抛出Exception，如果抛出
     *                这个异常之后，需要自己做处理，如将日志保存在本地的文件系统中等等。
     * @throws Exception
     */
    public void publish(String message) throws Exception{
        try {
            channel.basicPublish(mqConfig.getExchangeName(),"",null,message.getBytes());
        } catch (IOException e) {
            //第一次发送失败，等待连接重连之后发送第二次
            channel.basicPublish(mqConfig.getExchangeName(), "", null, message.getBytes());
        }
    }
}
