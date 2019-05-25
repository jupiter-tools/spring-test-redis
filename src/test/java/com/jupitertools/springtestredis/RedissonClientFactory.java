package com.jupitertools.springtestredis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * {@link RedissonClient} factory to use in tests
 */
public class RedissonClientFactory {

    public static RedissonClient getClient(String host, String port) {
        Config config = new Config();

        String url = String.format("redis://%s:%s", host, port);

        config.useSingleServer().setAddress(url);
        return Redisson.create(config);
    }

}
