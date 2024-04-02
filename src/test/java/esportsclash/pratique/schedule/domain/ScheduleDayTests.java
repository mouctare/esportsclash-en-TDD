package esportsclash.pratique.schedule.domain;

import esportsclash.pratique.schedule.domain.model.Moment;
import esportsclash.pratique.schedule.domain.model.ScheduleDay;
import esportsclash.pratique.team.domain.Role;
import esportsclash.pratique.team.domain.Team;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ScheduleDayTests {
    Team createTeam(String id){
        var name = "Team" + id;
        var team = new Team(id, name);

        team.addMember(id + "-1", Role.TOP);
        team.addMember(id + "-2", Role.JUNGLE);
        team.addMember(id + "-3", Role.MIDDLE);
        team.addMember(id + "-4", Role.BOTTOM);
        team.addMember(id + "-5", Role.SUPPORT);

        return team;
    }

    Team createIncompleteTeam(String id) {
        var name = "Team" + id;
        var team = new Team(id, name);

        team.addMember(id + "-1", Role.TOP);
        team.addMember(id + "-2", Role.JUNGLE);
        team.addMember(id + "-3", Role.MIDDLE);
        team.addMember(id + "-4", Role.BOTTOM);

        return team;
    }

    @Test
    void shouldOrganize(){
        // var team1 = new Team("t1", "Team 1");
        // var team2 = new Team("t2", "Team 2");
        var team1 = createTeam("t1");
        var team2 = createTeam("t2");
        var moment = Moment.MORNING;

        var scheduleDay = new ScheduleDay();

        scheduleDay.organizeAmeeting(team1, team2, moment);

        var match = scheduleDay.getAt(Moment.MORNING);
        Assert.assertTrue(match.isPresent());
    }

    @Test
    void whenMomentIsUnavailable_shouldThrow(){
        var t1 = createTeam("t1");
        var t2 = createTeam("t2");
        var t3 = createTeam("t3");
        var t4 = createTeam("t4");

        var scheduleDay = new ScheduleDay();

        scheduleDay.organizeAmeeting(t1, t2, Moment.MORNING);

        // On veut une exception, lorsqu'on organise deux rencontres au meme moment
        var exception = Assert.assertThrows(
                IllegalStateException.class,
                () -> scheduleDay.organizeAmeeting(t3, t4, Moment.MORNING)
        );

        Assert.assertEquals("Moment MORNING is already taken", exception.getMessage());
    }

    @Test
    void whenTeamAlreadyPlaysInTheMorning_shouldFail(){
        var t1 = createTeam("t1");
        var t2 = createTeam("t2");
        var t3 = createTeam("t3");
        var t4 = createTeam("t4");

        var scheduleDay = new ScheduleDay();

        // Cette foi-ci si on veut organiser une rencontre alors que l'équipe joue déjà exemple entre t1 et t3, on s'attends à une exception
        scheduleDay.organizeAmeeting(t1, t3, Moment.MORNING);

        // On veut une exception, lorsqu'on organise deux rencontres au meme moment
        var exception = Assert.assertThrows(
                IllegalStateException.class,
                () -> scheduleDay.organizeAmeeting(t3, t4, Moment.AFTERNOON)
        );

        Assert.assertTrue(exception.getMessage().matches("^Team .+ is already playing$"));
    }

    @Test
    void whenTeamAlreadyPlaysInTheAfternoon_shouldFail(){
        var t1 = createTeam("t1");
        var t2 = createTeam("t2");
        var t3 = createTeam("t3");
        var t4 = createTeam("t4");

        var scheduleDay = new ScheduleDay();
        scheduleDay.organizeAmeeting(t1, t3, Moment.AFTERNOON);

        var exception = Assert.assertThrows(
                IllegalStateException.class,
                () -> scheduleDay.organizeAmeeting(t3, t4, Moment.MORNING)
        );

        Assert.assertTrue(exception.getMessage().matches("^Team .+ is already playing$"));
    }

    @Test
    void whenFirstTeamIsIncomplete_shouldFail(){
        var t1 = createIncompleteTeam("t1");
        var t2 = createTeam("t2");


        var scheduleDay = new ScheduleDay();

        var exception = Assert.assertThrows(
                IllegalStateException.class,
                () -> scheduleDay.organizeAmeeting(t1, t2, Moment.MORNING)
        );

        Assert.assertEquals("One of the teams is not complete", exception.getMessage());
    }

    @Test
    void whenSecondTeamIsIncomplete_shouldFail(){
        var t1 = createTeam ("t1");
        var t2 = createIncompleteTeam("t2");


        var scheduleDay = new ScheduleDay();

        var exception = Assert.assertThrows(
                IllegalStateException.class,
                () -> scheduleDay.organizeAmeeting(t1, t2, Moment.MORNING)
        );

        Assert.assertEquals("One of the teams is not complete", exception.getMessage());
    }
}
