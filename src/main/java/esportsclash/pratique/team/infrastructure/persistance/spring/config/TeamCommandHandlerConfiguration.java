package esportsclash.pratique.team.infrastructure.persistance.spring.config;

import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.application.usecases.CreateTeamCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamCommandHandlerConfiguration {
    @Bean
    public CreateTeamCommandHandler createTeamCommandHandler(TeamRepository teamRepository){
        return new CreateTeamCommandHandler(teamRepository);
    }
}
