package org.tommap.eazystorebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EazystoreBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EazystoreBeApplication.class, args);
    }

}
