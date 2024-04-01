package esportsclash.pratique.team.infrastructure.persistance.jpa;

import esportsclash.pratique.core.infrastructure.persistance.sql.SQLBaseRepository;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Team;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SQLTeamRepository extends SQLBaseRepository<Team> implements TeamRepository {
    public SQLTeamRepository(EntityManager entityManager){
       super(entityManager);
    }
    @Override
    public Class<Team> getEntityClass() {
        return Team.class;
    }

    @Override
    public Optional<Team> findByPlayerId(String playerId) {
        var query = entityManager.createQuery(
                "SELECT t FROM Team t JOIN t.members m WHERE m.playerId = :playerId", Team.class
        );

        query.setParameter("playerId", playerId);

        return query
                .getResultList()
                .stream()
                .findFirst();
    }
}
