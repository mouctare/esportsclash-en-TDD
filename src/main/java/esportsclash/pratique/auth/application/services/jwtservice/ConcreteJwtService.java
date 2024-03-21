package esportsclash.pratique.auth.application.services.jwtservice;

import esportsclash.pratique.auth.domain.model.AuthUser;
import esportsclash.pratique.auth.domain.model.User;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ConcreteJwtService implements JwtService {
    private final SecretKey secretKey;
    private final JwtParser jwtParser;
    private final long expiration;

    public ConcreteJwtService(String secret, long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtParser = Jwts.parser().verifyWith(secretKey).build();
        this.expiration = expiration;
    }

    @Override
    public String tokenize(User user) {
        var claims = Jwts.claims()
                .subject(user.getId())
                .add("emailAddress", user.getEmailAddress())
                .build();
        var createdAt = LocalDateTime.now();
        var expiresAt = createdAt.plusSeconds(this.expiration);
        return Jwts.builder()
                .claims(claims)
                .issuedAt(Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant()))
                .expiration(Date.from(expiresAt.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(this.secretKey)
                .compact();
    }
    @Override
    public AuthUser parse(String token) {
        var claims = this.jwtParser.parseSignedClaims(token).getPayload();

        var id = claims.getSubject();
        var emailAddress = claims.get("emailAddress", String.class);

        return new AuthUser(id, emailAddress);
    }

}
