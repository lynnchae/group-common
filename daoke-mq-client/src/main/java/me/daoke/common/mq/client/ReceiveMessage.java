package me.daoke.common.mq.client;

import com.rabbitmq.client.QueueingConsumer;
import me.daoke.common.mq.util.ConnectionUtil;
import me.daoke.common.mq.util.MQConfig;

/**
 * 从队列中接收消费并消费
 * <p/>
 * User: chenlong
 * Date: 2014/12/22
 * Time: 9:56
 */
public class ReceiveMessage extends ConnectionUtil {


    /**
     * 消息接收确认  false确认  true不需确认
     */
    protected static final boolean autoAck = false;

    /**
     * 消费持久化
     */
    protected static final boolean durable = true;

    /**
     * 队列消费者
     */
    public QueueingConsumer consumer;


    public ReceiveMessage(MQConfig mqConfig) {
        super(mqConfig);
    }

    /**
     *
     */
    @Override
    protected void callBack() {
        try {
            channel.queueDeclare(mqConfig.getQueueName(), durable, false, false, null);
            channel.basicQos(mqConfig.getPrefetchCount()); //每次取得的消息条数
            consumer = new QueueingConsumer(channel);
            channel.basicConsume(mqConfig.getQueueName(), autoAck, consumer);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * 获取消息的方法，用法如下，此方法一旦调用，消息就会ack，自己得维护消息的安全：
     * while(true){
     * try{receive}catch(Exception){}
     * }
     *
     * @return 消息
     * @throws InterruptedException
     * @throws Exception
     */
    public String receive() throws Exception {
        QueueingConsumer.Delivery deliver = consumer.nextDelivery();
        String message = new String(deliver.getBody());
        channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
        return message;
    }


    /**
     * 获取消息的方法，用法如下，此方法一旦调用，消息就会ack，自己得维护消息的安全：
     * while(true){
     * try{receive}catch(Exception){}
     * }
     *
     * @return 消息
     * @throws InterruptedException
     * @throws Exception
     */
    public byte[] receiveToByte() throws Exception {
        QueueingConsumer.Delivery deliver = consumer.nextDelivery();
       // String message = new String(deliver.getBody());
        channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
        return deliver.getBody();
    }
}
