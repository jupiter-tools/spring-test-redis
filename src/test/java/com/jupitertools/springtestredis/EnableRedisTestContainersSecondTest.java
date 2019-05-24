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
@EnableRedisTestContainers
class EnableRedisTestContainersSecondTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        assertThat(redisTemplate).isNotNull();

        redisTemplate.opsForValue().set("second", 123);

        int res = (int) redisTemplate.opsForValue().get("second");
        System.out.println(res);
        assertThat(res).isEqualTo(123);
    }

}
