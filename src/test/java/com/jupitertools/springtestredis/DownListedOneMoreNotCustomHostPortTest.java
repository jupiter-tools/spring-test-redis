package com.jupitertools.springtestredis;


import org.assertj.core.api.Assertions;
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
class DownListedOneMoreNotCustomHostPortTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        assertThat(redisTemplate).isNotNull();
        redisTemplate.opsForValue().set("test3", "sabracadabra");
        Boolean hasKey = redisTemplate.hasKey("test3");

        Assertions.assertThat(hasKey).isTrue();

        String res = (String) redisTemplate.opsForValue().get("test3");
        System.out.println(res);
        assertThat(res).isEqualTo("sabracadabra");
    }

}
