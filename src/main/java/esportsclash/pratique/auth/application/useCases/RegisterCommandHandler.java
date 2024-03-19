package esportsclash.pratique.auth.application.useCases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;

import java.util.UUID;

public class RegisterCommandHandler implements Command.Handler<RegisterCommand, IdResponse> {
    private final UserRepository repository;

    // Injection de l'interface
    private PasswordHasher passwordHasher;

    public RegisterCommandHandler(UserRepository repository, PasswordHasher passwordHasher) {
        this.repository = repository;
        this.passwordHasher = passwordHasher;
    }

    public RegisterCommandHandler(UserRepository repository){
        this.repository = repository;
    }
    @Override
    public IdResponse handle(RegisterCommand command) {
         var user = new User(
                UUID.randomUUID().toString(),
                command.getEmailAdresse(),
                 passwordHasher.hash(command.getPassword()
                ));
         repository.save(user);
        return new IdResponse(user.getId());
    }
}
