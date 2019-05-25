package com.jupitertools.springtestredis;

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
@Repeatable(RedisTestContainers.class)
public @interface RedisTestContainer {

    /**
     * @return In this property will be set the value of Redis Host after start a container
     */
    String hostTargetProperty() default "spring.redis.host";

    /**
     * @return In this property will be set the value of Redis Port after start a container
     */
    String portTargetProperty() default "spring.redis.port";
}
