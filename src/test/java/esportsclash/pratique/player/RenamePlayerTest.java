package esportsclash.pratique.player;

import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.application.usecases.RenamePlayerCommand;
import esportsclash.pratique.player.application.usecases.RenamePlayerCommandHandler;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RenamePlayerTest {

    @Test
    void shouldRenamePlayer(){
        var playerRepository = new InMemoryPlayerRepository();
        var player = new Player("123", "old name");
        playerRepository.save(player);

        var command = new RenamePlayerCommand(player.getId(), "new name");

        var commandHandler = new RenamePlayerCommandHandler(playerRepository);
        commandHandler.handle(command);

        Player actualPlayer = playerRepository.findById(player.getId()).get();

        Assert.assertEquals(command.getName(), actualPlayer.getName());

    }

    @Test
    void whenPlayerDoesNotExist_shouldThrowNotFound(){
        var playerRepository = new InMemoryPlayerRepository();
        var command = new RenamePlayerCommand("garbage", "new name");
        var commandHandler = new RenamePlayerCommandHandler(playerRepository);

        var exception = Assert.assertThrows(NotFoundException.class,
                () -> commandHandler.handle(command));
        Assert.assertEquals("Player with the key garbage not found", exception.getMessage());

    }
}
