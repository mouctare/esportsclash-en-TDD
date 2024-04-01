package esportsclash.pratique.team.infrastructure.persistance.spring.config;

import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.team.application.ports.TeamQueries;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamCommandHandlerConfiguration {
    @Bean
    public CreateTeamCommandHandler createTeamCommandHandler(TeamRepository teamRepository){
        return new CreateTeamCommandHandler(teamRepository);
    }

    @Bean
    public DeleteTeamCommandHandler deleteTeamCommandHandler(TeamRepository teamRepository){
        return new DeleteTeamCommandHandler(teamRepository);
    }

    @Bean
    public AddPlayerToTeamCommandHandler addPlayerToTeamCommandHandler(
            PlayerRepository playerRepository, TeamRepository teamRepository){
        return new AddPlayerToTeamCommandHandler(playerRepository, teamRepository);
    }

    @Bean
    public RemovePlayerFromTeamCommandHandler removePlayerFromTeamCommandHandler(TeamRepository teamRepository){
        return new RemovePlayerFromTeamCommandHandler(teamRepository);
    }

    @Bean
    public GetTeamByIdCommandHandler getTeamByIdCommandHandler(TeamQueries teamQueries){
        return new GetTeamByIdCommandHandler(teamQueries);
    }
}
