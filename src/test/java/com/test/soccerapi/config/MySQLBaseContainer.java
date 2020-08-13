package sactio.reminderapi.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;


public class MySQLBaseContainer extends MySQLContainer<MySQLBaseContainer> {

    private static final String IMAGE_VERSION = "mysql:8.0.11";
    private static MySQLBaseContainer container;

    private MySQLBaseContainer() {
        super(IMAGE_VERSION);
    }

    public static MySQLBaseContainer getInstance() {
        if (container == null) {
            container = new MySQLBaseContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }


    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

    public static class Initializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.datasource.password=" + container.getPassword());
            values.applyTo(configurableApplicationContext);
        }
    }

}