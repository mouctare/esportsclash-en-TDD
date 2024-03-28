package esportsclash.pratique.team.e2e;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Team;
import esportsclash.pratique.team.infrastructure.persistance.spring.dto.CreateTeamDTO;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class DeleteTeamE2Tests extends IntegrationTests {
    @Autowired
    private TeamRepository teamRepository;

    Team team = new Team("123", "team");

    @BeforeEach
    public void setUp(){
        teamRepository.clear();
        teamRepository.save(team);
    }
    @Test
    public void shouldDeleteTeam() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/teams/" + team.getId())
                        .header("Authorization", createJWT())
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        var teamQuery = teamRepository.findById(team.getId());

        Assert.assertFalse(teamQuery.isPresent());

    }
}
