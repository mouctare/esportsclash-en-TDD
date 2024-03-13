package esportsclash.pratique.player.infrastructure.persistance.jpa;

import esportsclash.pratique.core.infrastructure.persistance.sql.SQLBaseRepository;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import jakarta.persistence.EntityManager;

public class SQLPlayerRepository extends SQLBaseRepository<Player> implements PlayerRepository {

    public SQLPlayerRepository(EntityManager entityManager) {
        super(entityManager);
    }
<<<<<<< HEAD

    @Override
    public Class<Player> getEntityClass() {
        return Player.class;
    }
=======
>>>>>>> parent of fc30f66 (Mise en place du CRUD PLayer avec les tests nécéssaires)
}
