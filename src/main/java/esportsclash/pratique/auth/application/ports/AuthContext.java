package esportsclash.pratique.auth.application.ports;

import esportsclash.pratique.auth.domain.model.AuthUser;

import java.util.Optional;

public interface AuthContext {
    boolean isAuthenticated();
    Optional<AuthUser> getUser();
}
