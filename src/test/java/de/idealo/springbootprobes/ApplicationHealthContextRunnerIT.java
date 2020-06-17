package de.idealo.springbootprobes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

class ApplicationHealthContextRunnerIT {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    void healthShouldBeUp() {

        contextRunner
                .withUserConfiguration(TestConfig.class)
                .withPropertyValues("management.health.probes.enabled=true")
                .run(context -> {
                    final HealthEndpoint healthEndpoint = context.getBean(HealthEndpoint.class);
                    assertThat(healthEndpoint.health().getStatus()).isEqualTo(Status.UP);
                });
    }

    @Configuration
    @EnableAutoConfiguration
    static class TestConfig {}
}
