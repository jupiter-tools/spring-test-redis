package com.jupitertools.springtestredis.customizer;

import com.jupitertools.springtestredis.EnableRedisTestContainers;
import com.jupitertools.springtestredis.EnableRedisTestContainersMultiple;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 2019-05-23
 *
 * @author Korovin Anatoliy
 */
public class PropertyMutationContextCustomizerFactory implements ContextCustomizerFactory {

    @Override
    public ContextCustomizer createContextCustomizer(Class<?> testClass,
                                                     List<ContextConfigurationAttributes> configAttributes) {

        Set<EnableRedisTestContainers> annotations =
                AnnotationUtils.getRepeatableAnnotations(testClass,
                                                         EnableRedisTestContainers.class,
                                                         EnableRedisTestContainersMultiple.class);

        Set<RedisContainerDescription> descriptions = annotations.stream()
                                                                 .map(a -> new RedisContainerDescription(a.hostPropertyName(),
                                                                                                         a.portPropertyName()))
                                                                 .collect(Collectors.toSet());

        return new PropertyMutationContextCustomizer(descriptions);
    }
}
