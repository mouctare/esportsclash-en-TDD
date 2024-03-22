package esportsclash.pratique.player;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.application.usecases.RenamePlayerCommand;
import esportsclash.pratique.player.application.usecases.RenamePlayerCommandHandler;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RenamePlayerTest extends IntegrationTests {
    private final InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();
   private RenamePlayerCommandHandler createHandler(){

       return new RenamePlayerCommandHandler(playerRepository);

   }
    @Test
    void shouldRenamePlayer(){
        var player = new Player("123", "old name");
        playerRepository.save(player);

        var command = new RenamePlayerCommand(player.getId(), "new name");

        var commandHandler = createHandler();
        commandHandler.handle(command);

        Player actualPlayer = playerRepository.findById(player.getId()).get();

        Assert.assertEquals(command.getName(), actualPlayer.getName());

    }

    @Test
    void whenPlayerDoesNotExist_shouldThrowNotFound(){
        var command = new RenamePlayerCommand("garbage", "new name");
        var commandHandler = createHandler();

        var exception = Assert.assertThrows(NotFoundException.class,
                () -> commandHandler.handle(command));
        Assert.assertEquals("Player with the key garbage not found", exception.getMessage());

    }
}
