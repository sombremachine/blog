package com.testtask.containers;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class TestApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger log = LoggerFactory.getLogger(TestApplicationInitializer.class);
    private static final Map<String, Object> PARAMS = new HashMap<>();

    public static void addProperties(Map<String, Object> properties) {
        PARAMS.putAll(properties);
    }

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        String[] propertiesStrings = mapPropertiesToStrings();
        log.info("properties: {}", Arrays.toString(propertiesStrings));
        TestPropertyValues.of(Stream.of(propertiesStrings)).applyTo(applicationContext);
    }

    private String[] mapPropertiesToStrings() {
        return PARAMS.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).toArray(String[]::new);
    }
}
