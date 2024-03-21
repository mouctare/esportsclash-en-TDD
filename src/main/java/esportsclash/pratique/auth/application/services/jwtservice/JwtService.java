package esportsclash.pratique.auth.application.services.jwtservice;

import esportsclash.pratique.auth.domain.model.AuthUser;
import esportsclash.pratique.auth.domain.model.User;

public interface JwtService {
    String tokenize(User user);
    AuthUser parse(String token);
}
