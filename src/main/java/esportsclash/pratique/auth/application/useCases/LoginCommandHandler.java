package esportsclash.pratique.auth.application.useCases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.application.services.jwtservice.JwtService;
import esportsclash.pratique.auth.application.services.passwordHasher.PasswordHasher;
import esportsclash.pratique.auth.domain.viewModel.LoggedInUserViewModel;
import esportsclash.pratique.core.domain.exception.BadRequestException;
import esportsclash.pratique.core.domain.exception.NotFoundException;

public class LoginCommandHandler implements Command.Handler<LoginCommand, LoggedInUserViewModel> {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final PasswordHasher passwordHash;

    public LoginCommandHandler(UserRepository userRepository, JwtService jwtService, PasswordHasher passwordHash){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordHash = passwordHash;
    }

    @Override
    public LoggedInUserViewModel handle(LoginCommand command) {
        var user = this.userRepository
                .findByEmailAddress(command.getEmailAddress())
                .orElseThrow(() -> new NotFoundException("User"));

        var match = this.passwordHash.match(command.getPassword(), user.getPassword());

        if(!match){
            throw new BadRequestException("Invalid password");
        }

        var token = this.jwtService.tokenize(user);

        return new LoggedInUserViewModel(user.getId(), user.getEmailAddress(), token);
    }
}
