package esportsclash.pratique.player;

import esportsclash.pratique.MySQLContainerTestConfiguration;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.domain.viewmodel.PlayerViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MySQLContainerTestConfiguration.class)
@Transactional

public class GetPlayerByIdE2ETests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void shouldGetPlayerById() throws Exception {
        // Given
        var player = new Player("123", "player");
        playerRepository.save(player);

        // When
       var result = mockMvc
               .perform(MockMvcRequestBuilders.get("/players/" + player.getId()))
                       .andReturn();
       var viewModel = objectMapper.readValue(
               result.getResponse().getContentAsString(),
               PlayerViewModel.class
       );

       // Then
        Assert.assertEquals(player.getId(), viewModel.getId());
        Assert.assertEquals(player.getName(), viewModel.getName());
    }

    @Test
    public void whenPlayerDoesNotExist_shouldFail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/players/garbage/"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
