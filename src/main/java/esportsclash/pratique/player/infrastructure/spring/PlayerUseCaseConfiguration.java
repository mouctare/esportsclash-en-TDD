package esportsclash.pratique.player.infrastructure.spring;

import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.application.usecases.CreatePlayerCommandHandler;
import esportsclash.pratique.player.application.usecases.RenamePlayerCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerUseCaseConfiguration {
    @Bean
    public CreatePlayerCommandHandler createPlayerUseCase(PlayerRepository playerRepository){
        return new CreatePlayerCommandHandler(playerRepository);
    }

    @Bean
    public RenamePlayerCommandHandler renamePlayerUseCase(PlayerRepository playerRepository){
        return new RenamePlayerCommandHandler(playerRepository);
    }
}
