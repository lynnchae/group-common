package me.daoke.driving.testrule;

import me.daoke.common.mq.client.ReceiveMessage;
import me.daoke.common.mq.util.MQConfig;
import me.daoke.driving.BaseTest;
import me.daoke.driving.common.service.RedisService;
import me.daoke.driving.util.ConstantsUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chenlong
 * Date: 2015/4/8
 * Time: 15:04
 */
public class TestRedis extends BaseTest {

    @Autowired
    private RedisService redisService;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;



    @Test
    public void testSortedSetAdd(){
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializerStr =  (RedisSerializer<String>)redisTemplate.getStringSerializer();
                redisConnection.zAdd(serializerStr.serialize("myzset3"),11112D,serializerStr.serialize("444533"));
                return null;
            }
        });
    }

     @Test
    public void testSortedSetRem(){
         String[] a ={"aa","bb","cc"};

         String[] b =new String[]{"aa","bb","cc"};
         System.out.println(a[0]);
         System.out.println(b[0]);

        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                RedisSerializer serializer =  redisTemplate.getStringSerializer();


                return null;
            }
        }) ;
    }

    @Test
    public void testGetHashValue() {

        RedisSerializer redisSerializer = redisTemplate.getHashValueSerializer();

    }




    @Test
    public void testHset() {
        System.out.println("-----begin execute hashset -----");
        final String accountID = "lu64o3ul5m";
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                boolean flag = redisConnection.hSet(serializer.serialize(ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY),
                        serializer.serialize(accountID), serializer.serialize("14286504350020006"));
                return flag;
            }
        });
        System.out.println("------end execute hashset ------");
    }


    @Test
    public void testHget() {
        System.out.println("-----begin execute hashset -----");
        final String accountID = "lu64o3ul5n";
        final String sValue = "165350001";
        Long value = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = redisConnection.hGet(serializer.serialize(ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY), serializer.serialize(accountID));




                return Long.parseLong(serializer.deserialize(value));
            }
        });
        System.out.println("value: " + value);
        System.out.println("------end execute hashset ------");
    }

    @Test
    public void testHincrby() {
        System.out.println("-----begin execute hashset -----");
        final String accountID = "lu64o3ul5m";
        Long value = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                Long value = redisConnection.hIncrBy(serializer.serialize(ConstantsUtil.EveryDay.POWEROFF_FIVE_DAY), serializer.serialize(accountID), 10L);


                return value;
            }
        });
        System.out.println("value: " + value);
        System.out.println("------end execute hashset ------");
    }

    @Test
    public void testSave() {
        final User user = new User("a01", "road1");

        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.set(
                        redisTemplate.getStringSerializer().serialize(
                                "user.uid." + user.getUid()),
                        redisTemplate.getStringSerializer().serialize(
                                user.getAddress()));
                return null;
            }
        });
    }


    @Test
    public void testRead() {
        final String uid = "a01";
        User user = redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize(
                        "user.uid." + uid);
                if (connection.exists(key)) {
                    byte[] value = connection.get(key);
                    String address = redisTemplate.getStringSerializer()
                            .deserialize(value);
                    User user = new User();
                    user.setAddress(address);
                    user.setUid(uid);
                    return user;
                }
                return null;
            }
        });

        System.out.println("user:" + user.toString());
    }


    @Test
    public void testDateConvert() {

        HashSet set = new HashSet<String>();
        set.add("aa");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        //  long a = 14286504350020006L;
        // long b = a / 10000;

        long c = (cal.getTimeInMillis() / 1000) / (24 * 60 * 60);
        //   System.out.println("b: "+ b);
        System.out.println("c: " + c);
        long d = c * 10000 + 30;
        System.out.println("d: " + d);
        System.out.println("e: " + d % 10000);

        Calendar cal1 = Calendar.getInstance();

        cal1.set(Calendar.DAY_OF_WEEK, -3);
        System.out.println("cal time:" + sdf.format(cal.getTime()));
        System.out.println("cal long time:" + cal.getTimeInMillis());
        Date date = new Date();
        System.out.println("date:" + cal1.getTimeInMillis() / (24 * 60 * 60 * 1000));

        System.out.println("d: " + cal1.getTimeInMillis());

        System.out.println("cal1 time:" + sdf.format(cal1.getTime()));


    }

    @Test
    public void testget() {
        try {
            String lon_lat_last_1 = (String) redisService.getHGetValue("tokenCodeLastLonlat", "8uml0n4sMN2");
            System.out.println(lon_lat_last_1);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        final ReceiveMessage receiveMesasge = new ReceiveMessage(mqConfig);
        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            es.execute(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        // Thread.sleep(20);
                        try {
                            String message = receiveMesasge.receive();
                            // System.out.println("message:" + message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            });
        }
    }

    @Test
    public void test1(){


        redisService.setHRuleValue("aaaaa","key",111l);

        redisService.setKeyExpire("Qmlhz8lAkn",10);


       // redisService.zAddSortedSet("xKQLTJStLl",1433239675L,"20");

        //redisService.zAddSortedSet("xKQLTJStLl",14332396760L,"1200");

//        Set<RedisZSetCommands.Tuple> set  =   redisService.zRangByScoreSortedSet("xKQLTJStLl", 1433239675L, 14332396760L);
//        if(set != null && set.size() > 0){
//            Iterator iterator = set.iterator();
//            while (iterator.hasNext()){
//                RedisZSetCommands.Tuple tuple = (RedisZSetCommands.Tuple) iterator.next();
//                System.out.println(" score :"+ tuple.getScore().intValue() +"  value:" + new String(tuple.getValue()) );
//            }
//        }

       // redisService.zRemRangeByScoreSortedSet();
    }

}
