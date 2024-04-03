package esportsclash.pratique.schedule.usecases;

import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.schedule.application.usecases.OrganizeMatchCommand;
import esportsclash.pratique.schedule.application.usecases.OrganizeMatchCommandHandler;
import esportsclash.pratique.schedule.domain.model.Moment;
import esportsclash.pratique.schedule.domain.model.ScheduleDay;
import esportsclash.pratique.schedule.infrastructure.persistence.ram.InMemoryScheduleDayRepository;
import esportsclash.pratique.team.domain.Role;
import esportsclash.pratique.team.domain.Team;
import esportsclash.pratique.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OrganizeMatchTests {
    private InMemoryScheduleDayRepository scheduleDayRepository = new InMemoryScheduleDayRepository();
    private InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

    @BeforeEach
    void setUp(){
        scheduleDayRepository.clear();
        teamRepository.clear();
    }

    OrganizeMatchCommandHandler createHandler(){
        return new OrganizeMatchCommandHandler(teamRepository, scheduleDayRepository);
    }

    private Team createTeam(String id){
        var name = "Team" + id;
        var team = new Team(id, name);

        team.addMember(id + "-1", Role.TOP);
        team.addMember(id + "-2", Role.JUNGLE);
        team.addMember(id + "-3", Role.MIDDLE);
        team.addMember(id + "-4", Role.BOTTOM);
        team.addMember(id + "-5", Role.SUPPORT);

        return team;
    }

    @Test
    void shouldOrganizeMatch(){
      var t1 = createTeam("t1");
      var t2 = createTeam("t2");

      teamRepository.save(t1);
      teamRepository.save(t2);

      var date =  LocalDate.parse("2024-01-01");
      var command = new OrganizeMatchCommand(
              date,
              Moment.MORNING,
              t1.getId(),
              t2.getId()
      );

      var handler = createHandler();
      var response = handler.handle(command);

      var scheduleDayQuery = scheduleDayRepository.findByDate(date);
        Assert.assertTrue(scheduleDayQuery.isPresent());

        var scheduleDay = scheduleDayQuery.get();

        var matchQuery = scheduleDay.getAt(Moment.MORNING);
        Assert.assertTrue(matchQuery.isPresent());

        var match = matchQuery.get();

        Assert.assertEquals(response.getId(), match.getId());
        Assert.assertTrue(match.featuresTeam(t1.getId()));
        Assert.assertTrue(match.featuresTeam(t2.getId()));

    }

    @Test
    void whenScheduleDayAlreadyExists_shouldReuseIt(){
        var date =  LocalDate.parse("2024-01-01");

        var t1 = createTeam("t1");
        var t2 = createTeam("t2");

        teamRepository.save(t1);
        teamRepository.save(t2);


        var scheduleDay = new ScheduleDay("1", date);
        scheduleDayRepository.save(scheduleDay);


        var command = new OrganizeMatchCommand(
                date,
                Moment.MORNING,
                t1.getId(),
                t2.getId()
        );

        var handler = createHandler();
        var response = handler.handle(command);

        var updatedScheduleDayQuery = scheduleDayRepository.findById(scheduleDay.getId()).get();

        var matchQuery = updatedScheduleDayQuery.getAt(Moment.MORNING);
        Assert.assertTrue(matchQuery.isPresent());

        var match = matchQuery.get();

        Assert.assertEquals(response.getId(), match.getId());
        Assert.assertTrue(match.featuresTeam(t1.getId()));
        Assert.assertTrue(match.featuresTeam(t2.getId()));

    }

    @Test
    void whenFirstTeamDoesNExist_shouldThrow(){
        var t1 = createTeam("t1");
        teamRepository.save(t1);

        var date =  LocalDate.parse("2024-01-01");
        var command = new OrganizeMatchCommand(
                date,
                Moment.MORNING,
                "random",
                t1.getId()
        );

        var handler = createHandler();

        var exception = Assert.assertThrows(
                NotFoundException.class,
                () -> handler.handle(command)
        );

        Assert.assertEquals("Team with the key random not found", exception.getMessage());
    }

    @Test
    void whenSecondTeamDoesNExist_shouldThrow(){
        var t1 = createTeam("t1");
        teamRepository.save(t1);

        var date =  LocalDate.parse("2024-01-01");
        var command = new OrganizeMatchCommand(
                date,
                Moment.MORNING,
                t1.getId(),
                "random"
        );

        var handler = createHandler();

        var exception = Assert.assertThrows(
                NotFoundException.class,
                () -> handler.handle(command)
        );

        Assert.assertEquals("Team with the key random not found", exception.getMessage());
    }
}
