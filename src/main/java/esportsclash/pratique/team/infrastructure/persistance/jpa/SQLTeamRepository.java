package esportsclash.pratique.team.infrastructure.persistance.jpa;

import esportsclash.pratique.core.infrastructure.persistance.sql.SQLBaseRepository;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Team;
import jakarta.persistence.EntityManager;

public class SQLTeamRepository extends SQLBaseRepository<Team> implements TeamRepository {
    public SQLTeamRepository(EntityManager entityManager){
       super(entityManager);
    }
    @Override
    public Class<Team> getEntityClass() {
        return Team.class;
    }
}
