package me.daoke.poweroff;


import com.rabbitmq.client.*;
import me.daoke.common.mq.client.EmitDirect;
import me.daoke.common.mq.client.EmitFanout;
import me.daoke.common.mq.client.ReceiveMessage;
import me.daoke.common.mq.util.JsonMapper;
import me.daoke.common.mq.util.MQConfig;
import me.daoke.poweroff.entity.PowerOffClient;
import me.daoke.poweroff.entity.PowerOffClientProtoBuf;
import me.daoke.poweroff.util.ConstantsUtil;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 *
 */
public class Test {


    @org.junit.Test
    public void publishDirectTest() {
        MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost("/v_poweroff");
        mqConfig.setUserName("chenlong");
        mqConfig.setPassWord("abc123");
        mqConfig.setHost1("192.168.1.13");
        mqConfig.setPort1(5672);
        //mqConfig.setQueueName("que_poweroff");
        mqConfig.setExchangeName("ex_poweroff");
        EmitDirect emitDirect = new EmitDirect(mqConfig);
       // PowerOffClient powerOffClient = new PowerOffClient(10, "dd9KdS5J0t", "zdfeqE74Vi", "526121488151431", "14050100000000151431", 1401437989);
        PowerOffClientProtoBuf.KeyValue.Builder clientPro = PowerOffClientProtoBuf.KeyValue.newBuilder();
        clientPro.setNormal(1);
        clientPro.setImei("4352525325");
        clientPro.setAccountID("ffwe5refefed");
        clientPro.setTokenCode("fds5fef");
        clientPro.setTravelID("143434") ;
        try {
            //String powerOffString = JsonMapper.toJson(powerOffClient, true);
            byte[] body =  clientPro.build().toByteArray();
           // emitDirect.publish("rout_poweroff",   body);
            PowerOffClientProtoBuf.KeyValue.Builder clientPro1 =  PowerOffClientProtoBuf.KeyValue.parseFrom(body).toBuilder();
            System.out.print(clientPro1.getImei());


            //PowerOffClientProtoBuf.KeyValue.Builder a =  clientPro.mergeFrom(body);

//            while (true) {
//                // Thread.sleep(1);
//                byte[] body =  clientPro.build().toByteArray();
//                emitDirect.publish("rout_poweroff",   body);
//                PowerOffClientProtoBuf.KeyValue.Builder a =  clientPro.mergeFrom(body);
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void receiveMessage() throws Exception {
        MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost("/v_poweroff");
        mqConfig.setUserName("chenlong");
        mqConfig.setPassWord("abc123");
        mqConfig.setHost1("192.168.1.14");
        mqConfig.setPort1(5672);
        mqConfig.setQueueName("v_poweroff");
        mqConfig.setExchangeName("ex_poweroff");
        ReceiveMessage receiveMesasge = new ReceiveMessage(mqConfig);

        while (true) {
            // Thread.sleep(20);
            String message = receiveMesasge.receive();
            //  System.out.println("message:"+ message);
        }
    }

    @org.junit.Test
    public void publishFanoutTest() {
        MQConfig mqConfig = new MQConfig();
        mqConfig.setVirtuaHost("/v_gps_data");
        mqConfig.setUserName("chenlong");
        mqConfig.setPassWord("abc123");
        mqConfig.setHost1("192.168.1.13");
        mqConfig.setPort1(5672);
        //mqConfig.setQueueName("que_poweroff");
        mqConfig.setExchangeName("q_gps_data");

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
    public void test111() throws Exception {
        String aa = "{\"longitude\":[116.3693195,116.3691452,116.3689673,116.3687873,116.3686067,116.3684278],\"latitude\":[37.4376097,37.4376103,37.4376121,37.437613,37.4376153,37.437619],\"IMEI\":\"103781733780001\",\"model\":\"V141224_64\",\"collect\":true,\"speed\":[54,55,56,57,57,56],\n" +
                "\"tokenCode\":\"gggggggggg\",\"accountID\":\"\",\"GPSTime\":[1427795456,1427795457,1427795458,1427795459,1427795461,1427795460],\"altitude\":[11,11,11,11,11,11],\"direction\":[270,270,270,269,270,270],\n" +
                "\"extragps\":{\"longitude\":[116.3725235,116.3709857,116.3709857],\"latitude\":[37.4375223,37.4375602,37.4375602],\"speed\":[40,23,23],\"GPSTime\":[1427795445,1427795429,1427795429],\"altitude\":[11,13,13],\"direction\":[273,273,273]}}\n";

        Map map = (Map) JsonMapper.fromJson(aa, Map.class);

        if (map.get("longitude") instanceof List) {
            System.out.println((List) map.get("longitude"));
        }

    }

    @org.junit.Test
    public void test222() {


        System.out.println(StringUtils.hasText(""));
        long startTime = System.currentTimeMillis();
//
        for (int j = 0; j < 1; j++) {
            String testAccountID = "abc,efg";
            String accountID = "efg";
            boolean isTestAccountID = false;
            if (StringUtils.hasText(testAccountID)) {
                StringTokenizer tokens = new StringTokenizer(testAccountID, ",");
                while (tokens.hasMoreTokens()) {
                    String token = tokens.nextToken();
                    if (accountID.equals(token)) {
                        isTestAccountID = true;
                        break;
                    }
                }

                if (!isTestAccountID) {
                   // System.out.println("aaaa");
                } else {
                   // System.out.println("bbbb");
                }
            }
        }
        System.out.println(System.currentTimeMillis() - startTime);
//        long startTime = System.currentTimeMillis();
//
//        for (int j = 0; j < 10000000; j++) {
//            StringTokenizer token = new StringTokenizer("abc,efg", ",");
//           // System.out.println("countTokens:" + token.countTokens());
//            while (token.hasMoreTokens()){
//                token.nextToken();
//              //  System.out.println("tokens:" + token.nextToken());
//            }
//
//
//        }
//        System.out.println(System.currentTimeMillis() - startTime);
//        long startTime1 = System.currentTimeMillis();
//        for (int j = 0; j < 10000000; j++) {
//
//            String accountID = "abc";
//            if (StringUtils.hasText(accountID)) {
//                String accountIDs[] = "abc,efg".split(",");
////                for (int i = 0; i < accountIDs.length; i++) {
////                    //System.out.println(accountIDs[i]);
////                    if (accountID.equals(accountIDs[i])) {
////                    }
////                }
//            }
//
//        }
        //  System.out.println(System.currentTimeMillis() - startTime1);

    }


}
