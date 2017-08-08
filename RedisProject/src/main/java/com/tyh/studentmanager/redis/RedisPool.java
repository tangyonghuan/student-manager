package com.tyh.studentmanager.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by DWade on 2017/8/8.
 */
public class RedisPool {
    public static final int DEFAULT_MAX_TOTAL = 8;
    public static final int DEFAULT_MAX_IDLE = 8;
    public static final int DEFAULT_MIN_IDLE = 0;
    private static JedisPool jedisPool = null;
    private static String addr = "localhost";
    private static int port = 6379;


    static{
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            /**
             * 最大空闲连接数, 默认8个
             */
            config.setMaxIdle(DEFAULT_MAX_IDLE);

            /**
             * 最大连接数, 默认8个
             */
            config.setMaxTotal(DEFAULT_MAX_TOTAL);

            /**
             * 获取连接时的最大等待毫秒数,如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
             */
            config.setMaxWaitMillis(-1);

            /**
             * 最小空闲连接数, 默认0
             */
            config.setMinIdle(DEFAULT_MIN_IDLE);


            /**
             * 在获取连接的时候检查有效性, 默认false
             */
            config.setTestOnBorrow(false);


            jedisPool = new JedisPool(config, addr, port, 3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
