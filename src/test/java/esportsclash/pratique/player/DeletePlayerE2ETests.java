package esportsclash.pratique.player;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class DeletePlayerE2ETests extends IntegrationTests {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void shouldDeletePlayer() throws Exception {
        // Given
        var existingPlayer = new Player("123", "player");
        playerRepository.save(existingPlayer);

        // When
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/players/" + existingPlayer.getId()
                        ).header("Authorization", createJWT()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Then
        var playerQuery = playerRepository.findById(existingPlayer.getId());
        Assert.assertTrue(playerQuery.isEmpty());
    }

    @Test
    public void whenPlayerDoesNotExist_shouldFail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/players/garbage/")
                        .header("Authorization", createJWT()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
