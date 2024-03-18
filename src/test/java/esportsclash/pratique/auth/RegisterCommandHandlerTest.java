package esportsclash.pratique.auth;

import esportsclash.pratique.auth.application.infrastructure.persistence.ram.InMemoryUserRepository;
import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.application.useCases.RegisterCommand;
import esportsclash.pratique.auth.application.useCases.RegisterCommandHandler;
import esportsclash.pratique.auth.domain.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RegisterCommandHandlerTest {
   private UserRepository repository = new InMemoryUserRepository();

   public RegisterCommandHandler createCommandHandler(){
       return new RegisterCommandHandler(repository);
   }
    @Test
    public void shouldRegister(){
        RegisterCommand command = new RegisterCommand("test@yahoo.fr", "password");
        RegisterCommandHandler commandHandler = createCommandHandler();

        var response = commandHandler.handle(command);

        var actualUser = repository.findById(response.getId()).get();

        Assert.assertEquals(command.getEmailAdresse(), actualUser.getEmailAdresse());
        Assert.assertEquals(command.getPassword(), actualUser.getPassword());

    }
}
