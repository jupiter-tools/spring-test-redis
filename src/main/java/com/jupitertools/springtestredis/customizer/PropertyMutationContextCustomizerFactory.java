package com.jupitertools.springtestredis.customizer;

import com.jupitertools.springtestredis.RedisTestContainer;
import com.jupitertools.springtestredis.RedisTestContainers;
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

        Set<RedisTestContainer> annotations =
                AnnotationUtils.getRepeatableAnnotations(testClass,
                                                         RedisTestContainer.class,
                                                         RedisTestContainers.class);

        Set<RedisContainerDescription> descriptions = annotations.stream()
                                                                 .map(a -> new RedisContainerDescription(a.hostTargetProperty(),
                                                                                                         a.portTargetProperty()))
                                                                 .collect(Collectors.toSet());

        return new PropertyMutationContextCustomizer(descriptions);
    }
}
