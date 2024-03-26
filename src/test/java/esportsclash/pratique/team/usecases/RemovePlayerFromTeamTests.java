package esportsclash.pratique.team.usecases;

import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.team.application.usecases.RemovePlayerFromTeamCommand;
import esportsclash.pratique.team.application.usecases.RemovePlayerFromTeamCommandHandler;
import esportsclash.pratique.team.domain.Role;
import esportsclash.pratique.team.domain.Team;
import esportsclash.pratique.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RemovePlayerFromTeamTests {
    InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();
    Team team;
    String playerId = "playerId";
    Role role = Role.TOP;

    private RemovePlayerFromTeamCommandHandler createHandler() {
        return new RemovePlayerFromTeamCommandHandler(teamRepository);
    }

    @BeforeEach
    void setUp() {
        teamRepository.clear();
        team = new Team("1", "Team");
        team.addMember(playerId, Role.TOP);
        teamRepository.save(team);
    }

    @Test
    void shouldRemovePlayerFromTeam() {
        var command = new RemovePlayerFromTeamCommand(
                playerId,
                team.getId()
        );

        var commandHandler = createHandler();
        commandHandler.handle(command);

        var team = teamRepository.findById(command.getTeamId()).get();

        Assert.assertFalse(
                team.hasMember(command.getPlayerId(), role)
        );

    }

    @Test
    void whenTeamDoesNotExist_shouldThrowNotFound(){
        var command = new RemovePlayerFromTeamCommand(
                playerId,
                "garbage");


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
