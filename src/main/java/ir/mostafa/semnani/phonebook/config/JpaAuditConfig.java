package ir.mostafa.semnani.phonebook.config;

import ir.mostafa.semnani.phonebook.security.model.AppUserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        if (SecurityContextHolder
                .getContext()
                .getAuthentication() != null) {
            return Optional.ofNullable(
                    ((AppUserDetails) SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal()
                    )
                            .getUsername());
        } else
            return Optional.of("");
    }

}
