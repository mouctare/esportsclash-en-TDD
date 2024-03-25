package esportsclash.pratique.team.usecases;

import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.application.usecases.DeletePlayerCommand;
import esportsclash.pratique.team.application.usecases.DeleteTeamCommand;
import esportsclash.pratique.team.application.usecases.DeleteTeamCommandHandler;
import esportsclash.pratique.team.domain.Team;
import esportsclash.pratique.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteTeamTests {
    InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

    Team team = new Team("1", "Team");
    private DeleteTeamCommandHandler deleteHandler(){
        return new DeleteTeamCommandHandler(teamRepository);
    }

    @BeforeEach
    void setUp(){
      teamRepository.clear();
      teamRepository.save(team);
    }

    @Test
    void shouldDeleteTeam(){
        var command = new DeleteTeamCommand(team.getId());
        var commandHandler = deleteHandler();
        commandHandler.handle(command);

        var teamQuery = teamRepository.findById(team.getId());

        Assert.assertFalse(teamQuery.isPresent());

    }

    @Test
    void whenTeamDoesNotExist_shouldThrowNotFound(){
        var command = new DeleteTeamCommand("garbage");
        var commandHandler = deleteHandler();

        var exception = Assert.assertThrows(NotFoundException.class,
                () -> commandHandler.handle(command));
        Assert.assertEquals("Team with the key garbage not found", exception.getMessage());

    }
}
