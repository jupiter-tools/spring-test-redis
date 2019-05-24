package com.jupitertools.springtestredis;

import org.junit.jupiter.api.Tag;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

/**
 * Created on 2019-05-23
 * <p>
 * Start a redis docker container with a Spring Application context
 *
 * @author Korovin Anatoliy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Tag("antkorwin-redis-testcontainers")
@ActiveProfiles("antkorwin-redis-testcontainers")
@Repeatable(EnableRedisTestContainersMultiple.class)
public @interface EnableRedisTestContainers {

    /**
     * @return In this property will be set the value of Redis Host after start a container
     */
    String hostPropertyName() default "spring.redis.host";

    /**
     * @return In this property will be set the value of Redis Port after start a container
     */
    String portPropertyName() default "spring.redis.port";
}
