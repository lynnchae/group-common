package me.daoke.driving.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * User: chenlong
 * Date: 2015/3/26
 * Time: 13:08
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * hash 根据变量名 域名 取值/ 根据变量名 域名 value设置值
     *
     * @param ruleCode  规则码
     * @param ruleKey    规则对应的KEY
     * @param ruleValue  规则的值
     * @return
     */
    public Long getHRuleValue(final String ruleCode, final String ruleKey, final Long ruleValue) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                //先取出判断是否已存在
                byte[] value = redisConnection.hGet(serializer.serialize(ruleCode), serializer.serialize(ruleKey));
                if (value == null) {
                    boolean flag = redisConnection.hSet(serializer.serialize(ruleCode),
                            serializer.serialize(ruleKey), serializer.serialize((ruleValue == null ? 0 :  ruleValue) + ""));
                    if(flag){
                        return (ruleValue == null ? 0 :  ruleValue);
                    }
                    return 0L;
                }
                return Long.parseLong(serializer.deserialize(value));
            }
        });
    }


    /**
     * hash 根据变量名 域名 取值/ 根据变量名 域名 value设置值
     *
     * @param ruleCode  规则码
     * @param ruleKey    规则对应的KEY
     * @return
     */
    public Object getHGetValue(final String ruleCode, final String ruleKey) {
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                //先取出判断是否已存在
                byte[] value = redisConnection.hGet(serializer.serialize(ruleCode), serializer.serialize(ruleKey));
                if(value == null){
                    return null;
                }
                return serializer.deserialize(value);
            }
        });
    }

    /**
     * 设置 hash 的值
     * @param key
     * @param filed
     * @param value
     * @return
     */
    public Object setHSetValue(final String key, final String filed, final Object value) {
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return redisConnection.hSet(serializer.serialize(key),
                        serializer.serialize(filed), serializer.serialize(value+""));
            }
        });
    }


    /**
     * 设置hash rule 的值
     * @param ruleCode
     * @param ruleKey
     * @param ruleValue
     * @return
     */
    public boolean setHRuleValue(final String ruleCode, final String ruleKey, final Long ruleValue) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return redisConnection.hSet(serializer.serialize(ruleCode),
                        serializer.serialize(ruleKey), serializer.serialize(ruleValue + ""));
            }
        });
    }

    /**
     * 操作规则的值
     * @param ruleCode  规则码
     * @param ruleKey    规则对应的KEY
     * @param increment   操作多大的值
     */
    public Long operatingHRuleValue(final String ruleCode, final String ruleKey, final Long increment ) {
        return  redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer =  redisTemplate.getStringSerializer();
                Long value = redisConnection.hIncrBy(serializer.serialize(ruleCode),serializer.serialize(ruleKey) ,increment);
                return  value;
            }
        });
    }

    /**
     * 操作sortset 类型  往其中添加成员
     * @param key
     * @param score
     * @param member
     * @return
     */
    public boolean zAddSortedSet(final String key ,final long score, final String member){
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer =  redisTemplate.getStringSerializer();

              return  redisConnection.zAdd(serializer.serialize(key),score,serializer.serialize(member));
            }
        });

    }

    /**
     * sortedSet中 删除key中的成员
     * @param key
     * @param member
     * @return
     */
    public boolean zRemSortedSet(final String key , final String member){
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer =  redisTemplate.getStringSerializer();
                return   redisConnection.zRem(serializer.serialize(key),serializer.serialize(member));
            }
        });
    }


    /**
     * sortedSet中 查询成员及成员的score
     * @param key
     * @return
     */
    public Set<RedisZSetCommands.Tuple> zRangByScoreSortedSet(final String key , final double min,final double max){
        return redisTemplate.execute(new RedisCallback<Set<RedisZSetCommands.Tuple>>() {
            @Override
            public Set<RedisZSetCommands.Tuple> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer =  redisTemplate.getStringSerializer();

                Set<RedisZSetCommands.Tuple> set =  redisConnection.zRangeByScoreWithScores(serializer.serialize(key), min, max);

                return set;
            }
        });
    }

    /**
     * sortedSet中 移除定score分数内的成员的score
     * @param key
     * @param minScore
     * @param maxScore
     * @return
     */
    public Long  zRemRangeByScoreSortedSet(final String key , final double minScore,final double maxScore){
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer =  redisTemplate.getStringSerializer();
                return redisConnection.zRemRangeByScore(serializer.serialize(key), minScore, maxScore);
            }
        });
    }


    /**
     * 设置key 的过期时间
     * @param key
     * @param seconds
     * @return
     */
    public boolean setKeyExpire(final String key , final long seconds){
       return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer =  redisTemplate.getStringSerializer();
               return redisConnection.expire(serializer.serialize(key),seconds);
            }
        }) ;
    }




}
