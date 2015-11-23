package me.daoke.common.mq.util;

/**
 *
 * 消息队列连接配置类
 * User: chenlong
 * Date: 2014/12/18
 * Time: 19:06
 */
public class MQConfig {

    /**
     * 主机IP1
     */
    private String host1;
    /**
     * 主机IP2
     */
    private String host2;
    /**
     * 主机ip1对应的端口
     */
    private int port1;
    /**
     * 主机ip2对应的端口
     */
    private int port2;
    /**
     * MQ中虚拟主机
     */
    private String virtuaHost;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 交换机名称
     */
    private String exchangeName;
    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 消息接收确认  false确认  true不需确认
     */
    private boolean autoAck = false;

    /**
     * 是否消息持久化
     */
    private boolean durable = true;

    /**
     * 取消息的数量
     */
    private Integer prefetchCount =1 ;


    public String getHost1() {
        return host1;
    }

    public void setHost1(String host1) {
        this.host1 = host1;
    }

    public String getHost2() {
        return host2;
    }

    public void setHost2(String host2) {
        this.host2 = host2;
    }

    public int getPort1() {
        return port1;
    }

    public void setPort1(int port1) {
        this.port1 = port1;
    }

    public int getPort2() {
        return port2;
    }

    public void setPort2(int port2) {
        this.port2 = port2;
    }

    public String getVirtuaHost() {
        return virtuaHost;
    }

    public void setVirtuaHost(String virtuaHost) {
        this.virtuaHost = virtuaHost;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public boolean isAutoAck() {
        return autoAck;
    }

    public void setAutoAck(boolean autoAck) {
        this.autoAck = autoAck;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public Integer getPrefetchCount() {
        return prefetchCount;
    }

    public void setPrefetchCount(Integer prefetchCount) {
        this.prefetchCount = prefetchCount;
    }

    @Override
    public String toString() {
        return "MQConfig{" +
                "host1='" + host1 + '\'' +
                ", host2='" + host2 + '\'' +
                ", port1=" + port1 +
                ", port2=" + port2 +
                ", virtuaHost='" + virtuaHost + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", exchangeName='" + exchangeName + '\'' +
                ", queueName='" + queueName + '\'' +
                ", autoAck=" + autoAck +
                ", durable=" + durable +
                ", prefetchCount=" + prefetchCount +
                '}';
    }
}
