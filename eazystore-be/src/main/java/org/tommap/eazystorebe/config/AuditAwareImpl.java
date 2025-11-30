package org.tommap.eazystorebe.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    private final Random random = new Random();

    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO: update using Spring Security Context
        String[] users = {"admin", "user"};
        return Optional.of(users[random.nextInt(users.length)]);
    }
}
