package esportsclash.pratique.team.usecases;

import esportsclash.pratique.team.application.usecases.CreateTeamCommand;
import esportsclash.pratique.team.application.usecases.CreateTeamCommandHandler;
import esportsclash.pratique.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CreateTeamTests {
    InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

    CreateTeamCommandHandler createTeamCommandHandler(){
        return new CreateTeamCommandHandler(teamRepository);
    }

    @Test
    public void shouldCreateTeam(){
     var command = new CreateTeamCommand("Team");

     var commandHandler = createTeamCommandHandler();

     var response = commandHandler.handle(command);

     var teamQuery = teamRepository.findById(response.getId());
     Assert.assertTrue(teamQuery.isPresent());
     var team = teamQuery.get();
     Assert.assertEquals("Team" , team.getName());


    }
}
