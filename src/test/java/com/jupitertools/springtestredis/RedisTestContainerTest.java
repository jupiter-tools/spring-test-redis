package com.jupitertools.springtestredis;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RedisTestContainer
class RedisTestContainerTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void readWriteValueByRedisTemplate() {
        String key = "test";
        String value = "sabracadabra";
        // Act
        redisTemplate.opsForValue().set(key, value);
        // Assert
        assertThat(redisTemplate.opsForValue().get(key)).isEqualTo(value);
    }

}
