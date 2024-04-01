package esportsclash.pratique.team.e2e;

import esportsclash.pratique.IntegrationTests;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Role;
import esportsclash.pratique.team.domain.Team;
import esportsclash.pratique.team.domain.TeamViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class GetTeamByIdE2ETests extends IntegrationTests {
    Team team;
    Player player;
    @Autowired
     TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @BeforeEach
    public void setUp(){
        player = new Player("123", "player");
        team = new Team("123", "team");

        team.addMember(player.getId(), Role.TOP);

        playerRepository.save(player);
        teamRepository.save(team);

        clearDataBseCache();

    }

    @Test
    public void shouldGetTheTeam() throws Exception {

       var result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/teams/" + team.getId())
                                .header("Authorization", createJWT())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var viewModel = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                TeamViewModel.class
        );

        Assert.assertEquals(team.getId(), viewModel.getId());
        Assert.assertEquals(team.getName(), viewModel.getName());

        var firstMember = viewModel.getMembers().get(0);
        Assert.assertEquals(player.getId(), firstMember.getPlayerId());
        Assert.assertEquals(player.getName(), firstMember.getPlayerName());
        Assert.assertEquals("TOP", firstMember.getRole());


    }
}
