package esportsclash.pratique.team.e2e;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.infrastructure.persistance.spring.dto.CreateTeamDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CreateTeamE2Tests extends IntegrationTests {
    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void shouldCreateTeam() throws Exception {

        var teamDto = new CreateTeamDTO("Team1");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamDto))
                        .header("Authorization", createJWT())
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var idResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                PlayerIdResponse.class
        );

        var team = teamRepository.findById(idResponse.getId()).get();

        Assert.assertNotNull(team);
        Assert.assertEquals(teamDto.getName(), team.getName());

    }
}
