package esportsclash.pratique.auth.application.infrastructure.spring;

import esportsclash.pratique.auth.application.services.passwordHasher.BcryptPasswordHasher;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthServicesConfiguration {
    @Bean
    public PasswordHasher passwordHasher(){
        return new BcryptPasswordHasher();
    }
}
