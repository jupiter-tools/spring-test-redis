package com.jupitertools.springtestredis.customizer;

import com.jupitertools.springtestredis.RedisTestContainer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.MergedContextConfiguration;
import org.testcontainers.containers.GenericContainer;

import java.util.Objects;
import java.util.Set;

/**
 * Created on 2019-05-23
 * <p>
 * The ContextCustomizer to make a different between context configurations
 * of tests where used different container ports in {@link RedisTestContainer} annotation.
 * In order to reload the spring context cache if it's necessary.
 *
 * @author Korovin Anatoliy
 */
public class PropertyMutationContextCustomizer implements ContextCustomizer {

    private Set<RedisContainerDescription> descriptions;

    private static final Integer REDIS_PORT = 6379;

    public PropertyMutationContextCustomizer(Set<RedisContainerDescription> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void customizeContext(ConfigurableApplicationContext context,
                                 MergedContextConfiguration mergedConfig) {

        for (RedisContainerDescription description : descriptions) {

            System.out.println("Start REDIS TestContainer");
            GenericContainer redis = new GenericContainer("redis:latest").withExposedPorts(REDIS_PORT);
            redis.start();

            System.setProperty(description.getHostPropertyName(),
                               redis.getContainerIpAddress());

            System.setProperty(description.getPortPropertyName(),
                               redis.getMappedPort(REDIS_PORT).toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        PropertyMutationContextCustomizer that = (PropertyMutationContextCustomizer) o;
        return Objects.equals(descriptions, that.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptions);
    }
}
