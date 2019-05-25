package com.jupitertools.springtestredis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 2019-05-24
 *
 * @author Korovin Anatoliy
 */
@SpringBootTest
@RedisTestContainer
@RedisTestContainer(hostTargetProperty = "my.host", portTargetProperty = "my.port")
class MultipleContainerInOneTest {

    private String KEY_NAME = "multi_container";

    @Autowired
    private RedisTemplate redisTemplate;

    private RedissonClient redisClient;

    @BeforeEach
    void setUp() {
        redisClient =
                RedissonClientFactory.getClient(System.getProperty("my.host"),
                                                System.getProperty("my.port"));
    }

    @Test
    void readWriteByRedissonClient() {
        // Act
        RBucket<String> bucket = redisClient.getBucket("simpleObject");
        bucket.set("This is object value =)");
        // Assert
        String value = (String) redisClient.getBucket("simpleObject").get();
        assertThat(value).isEqualTo("This is object value =)");
    }

    @Test
    void readWriteValueByRedisTemplate() {
        assertThat(redisTemplate).isNotNull();
        // Act
        redisTemplate.opsForValue().set(KEY_NAME, "sabracadabra");
        // Asserts
        Assertions.assertThat(redisTemplate.hasKey(KEY_NAME)).isTrue();

        String res = (String) redisTemplate.opsForValue().get(KEY_NAME);
        assertThat(res).isEqualTo("sabracadabra");
    }
}
