package esportsclash.pratique.auth.application.infrastructure.spring;

import esportsclash.pratique.auth.application.ports.AuthContext;
import esportsclash.pratique.auth.domain.model.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringAuthContext implements AuthContext {
    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .isAuthenticated();
    }

    @Override
    public Optional<AuthUser> getUser() {
        return Optional.ofNullable(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        ).map(auth -> {
            if(auth.getPrincipal() instanceof  AuthUser){
                return (AuthUser) auth.getPrincipal();
            }
            return null;
        });
    }
}
