package esportsclash.pratique.auth.application.infrastructure.spring;

import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.application.services.jwtservice.JwtService;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import esportsclash.pratique.auth.application.useCases.LoginCommandHandler;
import esportsclash.pratique.auth.application.useCases.RegisterCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfiguration {
    @Bean
    public RegisterCommandHandler registerCommandHandler(UserRepository userRepository, PasswordHasher passwordHasher){
        return new RegisterCommandHandler(userRepository, passwordHasher);
    }

    @Bean
    LoginCommandHandler loginCommandHandler(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordHasher passwordHasher

    ){
        return new LoginCommandHandler(userRepository, jwtService, passwordHasher);
    }
}
