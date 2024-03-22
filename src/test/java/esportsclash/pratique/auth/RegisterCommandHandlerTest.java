package esportsclash.pratique.auth;

import esportsclash.pratique.auth.application.exceptions.EmailAddressUnavailableException;
import esportsclash.pratique.auth.application.infrastructure.persistence.ram.InMemoryUserRepository;
import esportsclash.pratique.auth.application.services.passwordHasher.BcryptPasswordHasher;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import esportsclash.pratique.auth.application.useCases.RegisterCommand;
import esportsclash.pratique.auth.application.useCases.RegisterCommandHandler;
import esportsclash.pratique.auth.domain.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterCommandHandlerTest {
   private InMemoryUserRepository repository = new InMemoryUserRepository();
   private PasswordHasher passwordHasher = new BcryptPasswordHasher();

   public RegisterCommandHandler createCommandHandler(){
       return new RegisterCommandHandler(repository, passwordHasher);
   }

   @BeforeEach
   public void setup(){
       repository.clear();
   }
    @Test
    public void shouldRegister(){
        RegisterCommand command = new RegisterCommand("test@yahoo.fr", "password");
        RegisterCommandHandler commandHandler = createCommandHandler();

        var response = commandHandler.handle(command);

        var actualUser = repository.findById(response.getId()).get();

        Assert.assertEquals(command.getEmailAddress(), actualUser.getEmailAddress());
        Assert.assertTrue(
             passwordHasher.match(command.getPassword(), actualUser.getPasswordHash()));

    }

    @Test
    public void whenEmailAddressIsInUse_shouldThrow(){
       var existingUser = new User("123", "test@yahoo.fr", "password");
           repository.save(existingUser);

        RegisterCommand command = new RegisterCommand(existingUser.getEmailAddress(), "password");
        var commandHandler = createCommandHandler();
        Assert.assertThrows(
                EmailAddressUnavailableException.class,
                () -> commandHandler.handle(command)
        );
   }
}
