package esportsclash.pratique.team.domain;

import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TeamTests {
    @Nested
    class AddMember{
        @Test
        void shouldJoiTeam() {
            var team = new Team("123", "Team 1");
            team.addMember("player1", Role.TOP);

            Assert.assertTrue(team.hasMember("player1", Role.TOP));
        }

        @Test
        void whenThePlayerIsAlreadyInTheTeam_shouldThrowException() {
            var team = new Team("123", "Team 1");
            team.addMember("player1", Role.TOP);

            var exception = Assert.assertThrows(
                    IllegalArgumentException.class,
                    () -> team.addMember("player1", Role.BOTTOM)
            );

            Assert.assertEquals("Player already in team", exception.getMessage());
        }

        @Test
        void whenTheRoleIsAlreadyTaken_shouldThrowException() {
            var team = new Team("123", "Team 1");
            team.addMember("player1", Role.TOP);

            var exception = Assert.assertThrows(
                    IllegalArgumentException.class,
                    () -> team.addMember("player2", Role.TOP)
            );

            Assert.assertEquals("Role is already taken", exception.getMessage());
        }
    }

    @Nested
    class RemoveMember {
        @Test
        void shouldRemoveMember() {
            var team = new Team("123", "Team 1");
            team.addMember("player1", Role.TOP);
            team.removeMember("player1");

            Assert.assertFalse(team.hasMember("player1", Role.TOP));
        }

        @Test
        void whenPlayerIsNotInTheTeam_shouldThrow() {
            var team = new Team("123", "Team 1");

            var exception = Assert.assertThrows(
                    IllegalArgumentException.class,
                   () -> team.removeMember("player1")
            );

            Assert.assertEquals("Player not in team", exception.getMessage());

        }
    }
}
