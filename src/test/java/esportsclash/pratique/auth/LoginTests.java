package esportsclash.pratique.auth;

import esportsclash.pratique.auth.application.infrastructure.persistence.ram.InMemoryUserRepository;
import esportsclash.pratique.auth.application.services.jwtservice.ConcreteJwtService;
import esportsclash.pratique.auth.application.services.jwtservice.JwtService;
import esportsclash.pratique.auth.application.services.passwordHasher.BcryptPasswordHasher;
import esportsclash.pratique.auth.application.useCases.LoginCommand;
import esportsclash.pratique.auth.application.useCases.LoginCommandHandler;
import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.auth.domain.viewModel.LoggedInUserViewModel;
import esportsclash.pratique.core.domain.exception.BadRequestException;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginTests {
    private final InMemoryUserRepository userRepository = new InMemoryUserRepository();
    private final JwtService jwtService = new ConcreteJwtService("sooper_sekret_please_do_not_share", 60);
    private final User user = new User("123", "test@yahoo.fr", new BcryptPasswordHasher().hash("password") );

    LoginCommandHandler createHandler(){
        return new LoginCommandHandler(userRepository, jwtService, new BcryptPasswordHasher());
    }

    @BeforeEach
    void setUp(){
        userRepository.clear();
        userRepository.save(user);

    }

    @Nested
    class HappyPath {
       @Test
       void shouldReturnTheUser() {
           var command = new LoginCommand("test@yahoo.fr", "password");
           var commandHandler = createHandler();
           LoggedInUserViewModel result = commandHandler.handle(command);

           Assert.assertEquals(result.getId(), user.getId());
           Assert.assertEquals(user.getEmailAddress(), result.getEmailAddress());

           var authenticatedUser = jwtService.parse(result.getToken());
           Assert.assertEquals(
                   user.getEmailAddress(),
                   authenticatedUser.getEmailAddress()
           );
       }
    }

    @Nested
    class Scenario_TheEmailAddressIsIncorrect {
        @Test
        void shouldThrowNotFound() {
            var command = new LoginCommand("jhon@yahoo.fr", "password");
            var commandHandler = createHandler();

           var exception =  assertThrows(NotFoundException.class, () ->
                    commandHandler.handle(command)
           );
        }
    }

    @Nested
    class Scenario_ThePasswordIsIncorrect {
        @Test
        void shouldThrowInvalidPassWord() {
            var command = new LoginCommand("test@yahoo.fr", "not-correct");
            var commandHandler = createHandler();

         var exception =   assertThrows(BadRequestException.class, () ->
                    commandHandler.handle(command)
            );
         Assert.assertEquals(
                 "Invalid password",
                 exception.getMessage()
         );
        }
    }
}
