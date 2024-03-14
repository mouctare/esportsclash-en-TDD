package esportsclash.pratique.player.infrastructure.persistance.jpa;

import esportsclash.pratique.core.infrastructure.persistance.sql.SQLBaseRepository;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import jakarta.persistence.EntityManager;

public class SQLPlayerRepository extends SQLBaseRepository<Player> implements PlayerRepository {

    public SQLPlayerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Player> getEntityClass() {
        return null;
    }

}
