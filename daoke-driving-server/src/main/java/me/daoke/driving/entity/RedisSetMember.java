package me.daoke.driving.entity;

import org.springframework.data.redis.connection.RedisZSetCommands;

/**
 * User: chenlong
 * Date: 2015/5/22
 * Time: 14:22
 */
public class RedisSetMember implements RedisZSetCommands.Tuple   {


    @Override
    public byte[] getValue() {
        return new byte[0];
    }

    @Override
    public Double getScore() {
        return this.getScore();
    }

    @Override
    public int compareTo(Double o) {
        return 0;
    }
}
