package esportsclash.pratique.auth.application.useCases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.application.exceptions.EmailAddressUnavailableException;
import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.core.domain.exception.BadRequestException;

import java.util.UUID;

public class RegisterCommandHandler implements Command.Handler<RegisterCommand, IdResponse> {
    private final UserRepository repository;

    // Injection de l'interface
    private final PasswordHasher passwordHasher;

    public RegisterCommandHandler(UserRepository repository, PasswordHasher passwordHasher) {
        this.repository = repository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public IdResponse handle(RegisterCommand command) {
        var isEmailAddressAvailable = repository.isEmailAddressAvailable(command.getEmailAdresse());

        if (isEmailAddressAvailable == false){
            throw new EmailAddressUnavailableException();
        }
         var user = new User(
                UUID.randomUUID().toString(),
                command.getEmailAdresse(),
                 passwordHasher.hash(command.getPassword()
                ));
         repository.save(user);
        return new IdResponse(user.getId());
    }
}
