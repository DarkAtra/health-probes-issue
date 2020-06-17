package de.idealo.springbootprobes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "management.health.probes.enabled=true")
class ApplicationHealthIT {

    @Autowired
    private HealthEndpoint healthEndpoint;

    @Test
    void healthShouldBeUp() {

        assertThat(healthEndpoint.health().getStatus()).isEqualTo(Status.UP);
    }
}
