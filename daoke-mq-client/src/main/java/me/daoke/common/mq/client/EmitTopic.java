package me.daoke.common.mq.client;

import me.daoke.common.mq.util.ConnectionUtil;
import me.daoke.common.mq.util.MQConfig;

import java.io.IOException;

/**
 * 组播
 * 不处理路由键。你只需要简单的将队列绑定到交换机上。
 * 一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
 * 很像子网广播，每台子网内的主机都获得了一份复制的消息，但是根据。
 * User: chenlong
 * Date: 2014/12/22
 * Time: 16:06
 */
public class EmitTopic extends ConnectionUtil {


    /**
     * 是否持久化
     */
    protected static final boolean durable = true;
    /**
     * 提交的模式
     *  1.这种模式较为复杂，简单来说，就是每个队列都有其关心的主题，所有的消息都带有一个“标题”(RouteKey)，Exchange会将消息转发到所有关注主题能与RouteKey模糊匹配的队列。
        2.这种模式需要RouteKey，也许要提前绑定Exchange与Queue。
        3.在进行绑定时，要提供一个该队列关心的主题，如“#.log.#”表示该队列关心所有涉及log的消息(一个RouteKey为”MQ.log.error”的消息会被转发到该队列)。
        4.“#”表示0个或若干个关键字，“*”表示一个关键字。如“log.*”能与“log.warn”匹配，无法与“log.warn.timeout”匹配；但是“log.#”能与上述两者匹配。
        5.同样，如果Exchange没有发现能够与RouteKey匹配的Queue，则会抛弃此消息。
     */
    protected static final String type = "topic";

    public EmitTopic(MQConfig mqConfig) {
        super(mqConfig);
    }

    @Override
    protected void callBack() {
        if (logger.isInfoEnabled())
            logger.info("EmitTopic callback");
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
            channel.basicPublish(mqConfig.getExchangeName(), routingKey, null, message.getBytes());
        }
    }

}
