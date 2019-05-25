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
class RedisTestContainerIntegerValueTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void readWriteIntegerValue() {
        // Act
        redisTemplate.opsForValue().set("second", 123);
        // Assert
        int res = (int) redisTemplate.opsForValue().get("second");
        assertThat(res).isEqualTo(123);
    }

}
