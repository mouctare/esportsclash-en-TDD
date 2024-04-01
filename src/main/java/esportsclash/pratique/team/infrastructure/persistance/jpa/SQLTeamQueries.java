package esportsclash.pratique.team.infrastructure.persistance.jpa;

import esportsclash.pratique.team.application.ports.TeamQueries;
import esportsclash.pratique.team.domain.Team;
import esportsclash.pratique.team.domain.TeamViewModel;
import jakarta.persistence.EntityManager;

public class SQLTeamQueries implements TeamQueries {
    private final EntityManager entityManager;

    public SQLTeamQueries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TeamViewModel getTeamById(String id) {
        var query = entityManager.createQuery(
                "SELECT DISTINCT t FROM Team t " +
                        "JOIN FETCH t.members m " +
                        "WHERE t.id = :id", Team.class
        );

        query.setParameter("id", id);

        var result = query.getSingleResult();

        var players = result.getMembers().stream()
                .map(member -> new TeamViewModel.TeamMember(
                            member.getId(),
                            member.getPlayer().getId(),
                            member.getPlayer().getName(),
                            member.getRole().name()
                    ))
                .toList();

        return new TeamViewModel(
                result.getId(),
                result.getName(),
                players
        );
    }
}
