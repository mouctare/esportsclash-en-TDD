package esportsclash.pratique.player;

import esportsclash.pratique.MySQLContainerTestConfiguration;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;
import esportsclash.pratique.player.infrastructure.spring.CreatePlayerDTO;
import esportsclash.pratique.player.infrastructure.spring.RenamePlayerDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MySQLContainerTestConfiguration.class)
@Transactional
public class RenamePlayerE2ETests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
                        .content(objectMapper.writeValueAsString(playerDto)))
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
                        .content(objectMapper.writeValueAsString(playerDto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();


    }
}
