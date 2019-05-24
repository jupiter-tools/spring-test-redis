package com.jupitertools.springtestredis;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 2019-05-23
 *
 * @author Korovin Anatoliy
 */
@SpringBootTest
@EnableRedisTestContainers(hostPropertyName = "my.host", portPropertyName = "my.port")
class CustomizationHostAndPortTest {

    @Test
    void testPropertiesAfterStartContext() {
        System.out.println("host : " + System.getProperty("my.host") + " " +
                           "port : " + System.getProperty("my.port"));

        assertThat(System.getProperty("my.port")).isNotEmpty();
        assertThat(System.getProperty("my.host")).isNotEmpty();
    }

    @Test
    void testRedisOnCustomHostPort() {

        String key = "testKey";
        String value = "testValue";

        RedissonClient redisClient = getRedisClient(System.getProperty("my.host"),
                                                    System.getProperty("my.port"));

        redisClient.getBucket(key).set(value);

        assertThat(redisClient.getBucket(key).get()).isEqualTo(value);
    }


    private RedissonClient getRedisClient(String host, String port) {
        Config config = new Config();

        String url = String.format("redis://%s:%s",
                                   System.getProperty("my.host"),
                                   System.getProperty("my.port"));

        config.useSingleServer().setAddress(url);
        return Redisson.create(config);
    }
}
