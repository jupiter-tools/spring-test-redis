package com.jupitertools.springtestredis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 2019-05-23
 *
 * @author Korovin Anatoliy
 */
@SpringBootTest
@RedisTestContainer(hostTargetProperty = "my.host", portTargetProperty = "my.port")
class CustomizationHostAndPortTest {

    private RedissonClient redisClient;

    @BeforeEach
    void setUp() {
        redisClient =
                RedissonClientFactory.getClient(System.getProperty("my.host"),
                                                System.getProperty("my.port"));
    }

    @Test
    void testPropertiesAfterStartContext() {
        System.out.println("host : " + System.getProperty("my.host") + " " +
                           "port : " + System.getProperty("my.port"));

        assertThat(System.getProperty("my.port")).isNotEmpty();
        assertThat(System.getProperty("my.host")).isNotEmpty();
    }

    @Test
    void testRedisOnCustomHostPort() {
        // Arrange
        String key = "testKey";
        String value = "testValue";
        // Act
        redisClient.getBucket(key).set(value);
        // Assert
        assertThat(redisClient.getBucket(key).get()).isEqualTo(value);
    }
}
