package esportsclash.pratique.player;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.infrastructure.spring.RenamePlayerDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class RenamePlayerE2ETests extends IntegrationTests {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void givenExistingPlayer_whenRenamePlayer_thenPlayerIsSuccessfullyRenamed() throws Exception {
        // Given
        var existingPlayer = new Player("123", "player");
        playerRepository.save(existingPlayer);

        var playerDto = new RenamePlayerDTO("new name");

        // When
        mockMvc.perform(MockMvcRequestBuilders.patch("/players/" + existingPlayer.getId() + "/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerDto))
                        .header("Authorization", createJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Then
        var player = playerRepository.findById(existingPlayer.getId()).get();
        Assert.assertEquals(playerDto.getName(), player.getName());
    }

    @Test
    public void whenPlayerDoesNotExist_shouldFail() throws Exception {
        // Given

        var playerDto = new RenamePlayerDTO("new name");

        // When
        mockMvc.perform(MockMvcRequestBuilders.patch("/players/garbage/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerDto))
                        .header("Authorization", createJWT()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();


    }
}
