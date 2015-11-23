package me.daoke.common.mq;


import com.rabbitmq.client.*;
import me.daoke.common.mq.util.*;
import me.daoke.common.mq.client.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class Test {

    private static ConnectionFactory factory = new ConnectionFactory(); //创建连接工厂

    @org.junit.Test
    public void publishTest() throws Exception {


        factory.setVirtualHost("/poweroff");
        factory.setUsername("chenlong");
        factory.setPassword("abc123");
        Address[] addresses = new Address[]{new Address("192.168.1.13", 5672)};
        Connection connection = factory.newConnection(addresses);
        connection.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                System.out.println("断开连接");
            }
        });
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("poweroff", "direct", true);


        int i = 0;
        while (true) {

            PowerOff powerOf = new PowerOff("MQ" + i++, "2015-03-31", UUID.randomUUID().toString(), UUID.randomUUID().toString(), "4234235" + i, 10);
            String powerOffStr = JsonMapper.toJson(powerOf, true);
            // Thread.sleep(20);
            //basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
            // MessageProperties.PERSISTENT_TEXT_PLAIN 消息持久化
            channel.basicPublish("poweroff", "rout_poweroff", MessageProperties.PERSISTENT_TEXT_PLAIN, powerOffStr.getBytes());
        }
    }

    @org.junit.Test
    public void publishDirectTest() {
        MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost("/poweroff");
        mqConfig.setUserName("chenlong");
        mqConfig.setPassWord("abc123");
        mqConfig.setHost1("192.168.1.13");
        mqConfig.setPort1(5672);
        //mqConfig.setQueueName("que_poweroff");
        mqConfig.setExchangeName("poweroff");

        EmitDirect emitDirect = new EmitDirect(mqConfig);
        try {
            while (true) {
                Thread.sleep(20);
                emitDirect.publish("rout_poweroff", "测试Rabbitmq");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    List<Double[]> list = new ArrayList<Double[]>();

    public void loadFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("e:/a.txt")));//构造一个BufferedReader类来读取文件
        String s = null;
        StringBuilder builder = new StringBuilder();
        while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
            //result = result + "\n" + s;
            builder.append(s).append("\n");
        }
        br.close();
        //System.out.println("builder:"+builder.toString());

        Map map = (Map) JsonMapper.fromJson(builder.toString(), Map.class);
        if (map != null) {
            List<Map> features = (List) map.get("features");
            for (Map map1 : features) {
                if (map1 != null) {
                    Map map2 = (Map) map1.get("geometry");
                    if (map2 != null) {
                        List<Double> coordinates = (List<Double>) map2.get("coordinates");
                        Double[] aa = new Double[]{coordinates.get(0), coordinates.get(1)};
                        list.add(aa);
                    }
                }
            }
        }
    }


    List<GPSData> list1 = new ArrayList<GPSData>();

    @org.junit.Test
    public void loadFile1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("e:/d.txt")));//构造一个BufferedReader类来读取文件
        String s = null;
        int x = 0;
        List list = new ArrayList();
        while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
            String[] sl = s.split("\\s+");
            try {
                if (sl[8].equals("-1")) {
                    continue;
                }

                Location location = new Location(Double.valueOf(sl[6]), Double.valueOf(sl[5]), Integer.valueOf(sl[9]), Integer.valueOf(sl[0].substring(0, sl[0].length() - 1)), Short.valueOf(sl[7]), Short.valueOf(sl[8]));

                x++;

                if (x == 5) {
                    GPSData gpsData = new GPSData();
                    gpsData.setAccountID(sl[4]);
                    gpsData.setIMEI(sl[3].substring(0, sl[3].length() - 1));
                    gpsData.setTokenCode(sl[10]);
                    gpsData.setLocations(list);
                    Collections.sort(list);
                    x = 0;
                    list1.add(gpsData);
                    list = new ArrayList();
                    //System.out.print("gpsData:" + gpsData);
                }
                list.add(location);
            }catch (Exception e){
                System.out.print("sl:" + sl[0]);
                e.printStackTrace();
            }




        }
        br.close();
//        Double[] dd = null;
//        Double[] dd1 = null;
//        long distance = 0;
//        for (int i = 0; i < list.size() - 1; i++) {
//            if (i % 5 == 0) {
//                i++;
//            }
//            if (i == list.size() - 1)
//                break;
//
//            dd = list.get(i);
//            dd1 = list.get(i + 1);
//            distance += CalDistanceUtil.getDistance(dd[0], dd[1], dd1[0], dd1[1]);
//        }
//        System.out.println("list.size:"+list.size());
//        System.out.println("distance:"+distance);
    }


    @org.junit.Test
    public void publishGpsDataDirectTest() throws IOException {
        MQConfig mqConfig = new MQConfig();
        //mqConfig.setVirtuaHost("/poweroff");
        mqConfig.setVirtuaHost("/v_gps_data");
        mqConfig.setUserName("chenlong");
        mqConfig.setPassWord("abc123");
        mqConfig.setHost1("115.231.73.73");
        mqConfig.setPort1(5672);

        mqConfig.setExchangeName("ex_gps_data");
        EmitDirect emitDirect = new EmitDirect(mqConfig);
        try {
            loadFile1();

            for (int i = 0; i < list1.size(); i++) {
                GPSData gpsData =  list1.get(i);
              //  System.out.println("gpsDate:" + gpsData);
                // gpsData.setGPSTime(new Integer[]{1428371231, 1428371232, 1428371233, 1428371234, 1428371235});
                String gpsDataStr = JsonMapper.toJson(gpsData, true);
             emitDirect.publish("rout_gps_data" + Test.additiveHash(gpsData.getTokenCode(), 4), gpsDataStr);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


     /**/

    /**
     * 加法hash
     *
     * @param key   字符串
     * @param prime 一个质数
     * @return hash结果
     */
    public static int additiveHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return (hash % prime);
    }

    @org.junit.Test
    public void publishFanoutTest() {
        MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost("/poweroff");
        mqConfig.setUserName("chenlong");
        mqConfig.setPassWord("abc123");
        mqConfig.setHost1("192.168.1.13");
        mqConfig.setPort1(5672);
        //mqConfig.setQueueName("que_poweroff");
        mqConfig.setExchangeName("poweroffTest");

        EmitFanout emitFanout = new EmitFanout(mqConfig);
        try {
            while (true) {
                System.out.println("生产消息");
                Thread.sleep(20);
                emitFanout.publish("测试Rabbitmq Fanout");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void consumerTest() throws Exception {
        factory.setVirtualHost("/v_gps_data");
        factory.setUsername("chenlong");
        factory.setPassword("abc123");
        Address[] addresses = new Address[]{new Address("192.168.1.14", 5672), new Address("192.168.1.13", 5672)};
        ExecutorService es = Executors.newFixedThreadPool(4);
        Connection connection = factory.newConnection(addresses);
        connection.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                System.out.println("断开连接");
            }
        });
        final Channel channel = connection.createChannel();
        channel.queueDeclare("q_gps_data", true, false, false, null);
        channel.basicQos(100); //每次取得的消息条数

        final QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume("q_gps_data", false, consumer);

//        for (int i = 0; i < 4; i++) {

        // es.execute(new Runnable() {
        //   @Override
        //  public void run() {
        try {

            while (true) {
                QueueingConsumer.Delivery deliver = consumer.nextDelivery();
                String message = new String(deliver.getBody());
                channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
                // System.out.println("message:" + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  }
        //  });
        // }


        es.shutdown();


    }

    @org.junit.Test
    public void receiveMessage() throws Exception {
        MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost("/v_gps_data");
        mqConfig.setUserName("chenlong");
        mqConfig.setPassWord("abc123");
        mqConfig.setHost1("192.168.1.14");
        mqConfig.setPort1(5672);
        mqConfig.setHost2("192.168.1.13");
        mqConfig.setPort2(5672);
        mqConfig.setQueueName("q_gps_data");
        mqConfig.setExchangeName("ex_gps_data");
        mqConfig.setPrefetchCount(100);
        // mqConfig.setAutoAck(true);
        ReceiveMessage receiveMesasge = new ReceiveMessage(mqConfig);

        while (true) {
            // Thread.sleep(20);
            String message = receiveMesasge.receive();
            // System.out.println("message:" + message);
        }
    }


}
