package esportsclash.pratique.team.e2e;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Role;
import esportsclash.pratique.team.domain.Team;
import esportsclash.pratique.team.infrastructure.persistance.spring.dto.AddPlayerToTeamDTO;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AddPlayerToTeamE2Tests extends IntegrationTests {
    Team team;
    Player player;
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @BeforeEach
    public void setUp(){
        teamRepository.clear();
        playerRepository.clear();

        team = new Team("123", "team");
        player = new Player("123", "player");

        teamRepository.save(team);
        playerRepository.save(player);
    }

    @Test
    public void shouldAddPlayerToTeam() throws Exception {

        var dto = new AddPlayerToTeamDTO(
                player.getId(),
                team.getId(),
                "TOP"
        );

        mockMvc
                .perform(MockMvcRequestBuilders.post("/teams/add-player-to-team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization", createJWT())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();



        var updatedTeam = teamRepository.findById(team.getId()).get();

        Assert.assertTrue(updatedTeam.hasMember(player.getId(), Role.TOP));

    }
}
