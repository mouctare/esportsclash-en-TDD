package esportsclash.pratique.auth.application.infrastructure.spring;

import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import esportsclash.pratique.auth.application.useCases.RegisterCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfiguration {
    @Bean
    public RegisterCommandHandler registerCommandHandler(UserRepository userRepository, PasswordHasher passwordHasher){
        return new RegisterCommandHandler(userRepository, passwordHasher);
    }
}
