package esportsclash.pratique.player;

import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.application.usecases.DeletePlayerCommandHandler;
import esportsclash.pratique.player.application.usecases.DeletePlayerCommand;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class DeletePlayerTest {
    private final InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();
   private DeletePlayerCommandHandler deleteHandler(){

       return new DeletePlayerCommandHandler(playerRepository);

   }
    @Test
    void shouldDeletePlayer(){
        var player = new Player("123", "old name");
        playerRepository.save(player);

        var command = new DeletePlayerCommand("123");

        var commandHandler = deleteHandler();
        commandHandler.handle(command);

        var playerQuery = playerRepository.findById(player.getId());

        Assert.assertTrue(playerQuery.isEmpty());

    }

    @Test
    void whenPlayerDoesNotExist_shouldThrowNotFound(){
        var command = new DeletePlayerCommand("garbage");
        var commandHandler = deleteHandler();

        var exception = Assert.assertThrows(NotFoundException.class,
                () -> commandHandler.handle(command));
        Assert.assertEquals("Player with the key garbage not found", exception.getMessage());

    }
}
