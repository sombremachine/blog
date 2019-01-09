package com.testtask.containers;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerRunner {

    public static void run() {
        if (!dataSourceParamsProvided()) {
            PostgreSQLContainer postgresContainer = new PostgreSQLContainer();
            postgresContainer.start();

            TestApplicationInitializer.addProperties(ImmutableMap.of(
                "spring.datasource.url", postgresContainer.getJdbcUrl(),
                "spring.datasource.username", postgresContainer.getUsername(),
                "spring.datasource.password", postgresContainer.getPassword()
            ));
        }
    }

    private static boolean dataSourceParamsProvided() {
        String url = System.getProperty("spring.datasource.url");
        String username = System.getProperty("spring.datasource.username");
        String password = System.getProperty("spring.datasource.password");

        return StringUtils.isNoneBlank(url, username, password);
    }
}
