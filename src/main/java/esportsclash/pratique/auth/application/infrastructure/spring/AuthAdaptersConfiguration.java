package esportsclash.pratique.auth.application.infrastructure.spring;

import esportsclash.pratique.auth.application.infrastructure.persistence.ram.InMemoryUserRepository;
import esportsclash.pratique.auth.application.ports.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAdaptersConfiguration {
    @Bean
    public UserRepository getUserRepository(){
        return new InMemoryUserRepository();
    }
}
