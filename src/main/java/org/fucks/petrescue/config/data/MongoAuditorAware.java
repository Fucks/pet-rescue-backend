package org.fucks.petrescue.config.data;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class MongoAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        var authenticated = SecurityContextHolder.getContext().getAuthentication();

        if(authenticated != null) {
            return Optional.of(authenticated.getName());
        }

        return Optional.of("System");
    }
}
