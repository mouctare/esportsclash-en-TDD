package esportsclash.pratique.player;

import esportsclash.pratique.MySQLContainerTestConfiguration;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MySQLContainerTestConfiguration.class)
public class CreatePlayerE2ETests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void shouldCreatePlayer() throws Exception {

        var playerDto = new CreatePlayerDTO("player");

       var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerDto)))
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andReturn();

       var idResponse = objectMapper.readValue(
               result.getResponse().getContentAsString(),
               PlayerIdResponse.class
       );

       var player = playerRepository.find(idResponse.getId());

        Assert.assertNotNull(player);
        Assert.assertEquals(playerDto.getName(), player.getName());

    }
}
