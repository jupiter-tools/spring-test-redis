package com.jupitertools.springtestredis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 2019-05-24
 * <p>
 * TODO: replace on the JavaDoc
 *
 * @author Korovin Anatoliy
 */
@SpringBootTest
@EnableRedisTestContainers
@EnableRedisTestContainers(hostPropertyName = "my.host", portPropertyName = "my.port")
class MultipleContainerInOneTest {

    private String KEY_NAME = "multi_container";

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void getProps() {
        System.out.println("host : " + System.getProperty("my.host") + " " +
                           "port : " + System.getProperty("my.port"));

        assertThat(System.getProperty("my.port")).isNotEmpty();
        assertThat(System.getProperty("my.host")).isNotEmpty();

        String url = String.format("redis://%s:%s",
                                   System.getProperty("my.host"),
                                   System.getProperty("my.port"));

        Config config = new Config();
        // use single Redis server
        config.useSingleServer().setAddress(url);
        RedissonClient redisson = Redisson.create(config);

        RBucket<String> bucket = redisson.getBucket("simpleObject");
        bucket.set("This is object value =)");

        String value = (String) redisson.getBucket("simpleObject").get();
        System.out.println(value);

        assertThat(value).isEqualTo("This is object value =)");
    }

    @Test
    void readWriteValue() {
        assertThat(redisTemplate).isNotNull();
        redisTemplate.opsForValue().set(KEY_NAME, "sabracadabra");
        Boolean hasKey = redisTemplate.hasKey(KEY_NAME);

        Assertions.assertThat(hasKey).isTrue();

        String res = (String) redisTemplate.opsForValue().get(KEY_NAME);
        System.out.println(res);
        assertThat(res).isEqualTo("sabracadabra");
    }
}
