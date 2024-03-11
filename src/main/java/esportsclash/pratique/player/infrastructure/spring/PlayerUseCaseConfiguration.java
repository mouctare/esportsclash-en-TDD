package esportsclash.pratique.player.infrastructure.spring;

import esportsclash.pratique.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import esportsclash.pratique.player.application.usecases.CreatePlayerCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerUseCaseConfiguration {
    @Bean
    public CreatePlayerCommandHandler createPlayerUseCase(InMemoryPlayerRepository playerRepository){
        return new CreatePlayerCommandHandler(playerRepository);
    }
}
