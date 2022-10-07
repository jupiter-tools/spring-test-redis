package com.jupitertools.springtestredis.customizer;

import com.jupitertools.springtestredis.RedisTestContainer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log logger = LogFactory.getLog("RedisTestContainer");

    public PropertyMutationContextCustomizer(Set<RedisContainerDescription> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void customizeContext(ConfigurableApplicationContext context,
                                 MergedContextConfiguration mergedConfig) {

        for (RedisContainerDescription description : descriptions) {
            logger.info("Start REDIS TestContainer");
            GenericContainer redis = new GenericContainer("redis:7.0.5-alpine").withExposedPorts(REDIS_PORT);
            redis.withReuse(true);
            redis.start();

            System.setProperty(description.getHostPropertyName(),
                               redis.getHost());

            System.setProperty(description.getPortPropertyName(),
                               redis.getMappedPort(REDIS_PORT).toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PropertyMutationContextCustomizer that = (PropertyMutationContextCustomizer) o;
        return Objects.equals(descriptions, that.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptions);
    }
}
