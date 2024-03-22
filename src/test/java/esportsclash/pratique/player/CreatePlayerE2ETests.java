package esportsclash.pratique.player;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;
import esportsclash.pratique.player.infrastructure.spring.CreatePlayerDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CreatePlayerE2ETests extends IntegrationTests {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void shouldCreatePlayer() throws Exception {

        var playerDto = new CreatePlayerDTO("player");

       var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerDto))
                        .header("Authorization", createJWT())
                    )
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andReturn();

       var idResponse = objectMapper.readValue(
               result.getResponse().getContentAsString(),
               PlayerIdResponse.class
       );

       var player = playerRepository.findById(idResponse.getId()).get();

        Assert.assertNotNull(player);
        Assert.assertEquals(playerDto.getName(), player.getName());

    }
}
