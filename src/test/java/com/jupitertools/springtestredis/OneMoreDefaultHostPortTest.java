package com.jupitertools.springtestredis;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * one more test class to check context cache while running all test suite
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@RedisTestContainer
class OneMoreDefaultHostPortTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        assertThat(redisTemplate).isNotNull();
        // Act
        redisTemplate.opsForValue().set("test3", "sabracadabra");
        // Asserts
        Assertions.assertThat(redisTemplate.hasKey("test3")).isTrue();

        String res = (String) redisTemplate.opsForValue().get("test3");
        assertThat(res).isEqualTo("sabracadabra");
    }

}
