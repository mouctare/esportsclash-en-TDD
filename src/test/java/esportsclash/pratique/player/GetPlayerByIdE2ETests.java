package esportsclash.pratique.player;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.domain.viewmodel.PlayerViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class GetPlayerByIdE2ETests extends IntegrationTests {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void shouldGetPlayerById() throws Exception {
        // Given
        var player = new Player("123", "player");
        playerRepository.save(player);

        // When
       var result = mockMvc
               .perform(MockMvcRequestBuilders
                       .get("/players/" + player.getId())
                       .header("Authorization", createJWT())
               )
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

        mockMvc.perform(MockMvcRequestBuilders.delete("/players/garbage/")
                        .header("Authorization", createJWT()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
