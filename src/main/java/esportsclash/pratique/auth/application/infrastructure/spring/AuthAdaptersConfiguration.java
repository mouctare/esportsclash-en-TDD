package esportsclash.pratique.auth.application.infrastructure.spring;

import esportsclash.pratique.auth.application.infrastructure.persistence.jpa.SQLUserAccessor;
import esportsclash.pratique.auth.application.infrastructure.persistence.jpa.SQLUserRepository;
import esportsclash.pratique.auth.application.infrastructure.persistence.ram.InMemoryUserRepository;
import esportsclash.pratique.auth.application.ports.AuthContext;
import esportsclash.pratique.auth.application.ports.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAdaptersConfiguration {
    @Bean
    public UserRepository getUserRepository(EntityManager entityManager, SQLUserAccessor userAccessor){
        return new SQLUserRepository(entityManager, userAccessor);
    }

    @Bean
    public AuthContext getAuthContext(){
        return new SpringAuthContext();
    }
}
