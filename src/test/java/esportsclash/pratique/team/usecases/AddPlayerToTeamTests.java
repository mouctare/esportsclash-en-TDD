package esportsclash.pratique.team.usecases;

import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import esportsclash.pratique.team.application.usecases.AddPlayerToTeamCommand;
import esportsclash.pratique.team.application.usecases.AddPlayerToTeamCommandHandler;
import esportsclash.pratique.team.domain.Role;
import esportsclash.pratique.team.domain.Team;
import esportsclash.pratique.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddPlayerToTeamTests {
    InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();
    InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

    Team team = new Team("1", "Team");
    Player player = new Player("1", "player");

    private AddPlayerToTeamCommandHandler createHandler(){
        return new AddPlayerToTeamCommandHandler(playerRepository, teamRepository);
    }

    @BeforeEach
    void setUp(){
      teamRepository.clear();
      playerRepository.clear();
      playerRepository.save(player);
      teamRepository.save(team);
    }

    @Test
    void shouldAddPlayerToTeam(){
        var command = new AddPlayerToTeamCommand(
                player.getId(),
                team.getId(),
                Role.TOP
        );

        var commandHandler = createHandler();
        commandHandler.handle(command);

        var team = teamRepository.findById(command.getTeamId()).get();

        // Test de la méthode hasMember dans la class Team
        Assert.assertTrue(team.hasMember(command.getPlayerId(), command.getRole()));

    }

    @Test
    void whenPlayerDoesNotExist_shouldThrowNotFound(){
        var command = new AddPlayerToTeamCommand(
                "garbage",
                team.getId(),
                Role.TOP);
        var commandHandler = createHandler();

        var exception = Assert.assertThrows(
                NotFoundException.class,
                () -> commandHandler.handle(command)
        );

        Assert.assertEquals(
                "Player with the key garbage not found",
                exception.getMessage()
        );
    }

    @Test
    void whenTeamDoesNotExist_shouldThrowNotFound(){
        var command = new AddPlayerToTeamCommand(
                player.getId(),
                "garbage",
                Role.TOP);
        var commandHandler = createHandler();

        var exception = Assert.assertThrows(
                NotFoundException.class,
                () -> commandHandler.handle(command)
        );

        Assert.assertEquals(
                "Team with the key garbage not found",
                exception.getMessage()
        );
    }
}
