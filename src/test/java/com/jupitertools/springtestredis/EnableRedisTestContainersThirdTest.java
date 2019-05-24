package com.jupitertools.springtestredis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created on 2019-05-23
 * <p>
 * TODO: replace on the JavaDoc
 *
 * @author Korovin Anatoliy
 */
@SpringBootTest
@EnableRedisTestContainers(hostPropertyName = "my.second.host", portPropertyName = "my.second.port")
class EnableRedisTestContainersThirdTest {

    @Test
    void getProps() {

        System.out.println("host : "+ System.getProperty("my.second.host") + " "+
                           "port : "+ System.getProperty("my.second.port"));

        assertThat(System.getProperty("my.second.port")).isNotEmpty();
        assertThat(System.getProperty("my.second.host")).isNotEmpty();
    }
}
