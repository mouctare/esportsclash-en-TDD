package esportsclash.pratique.player.application.ports;

import esportsclash.pratique.core.infrastructure.persistance.BaseRepository;
import esportsclash.pratique.player.domain.model.Player;

<<<<<<< HEAD
public interface PlayerRepository extends BaseRepository<Player> {
=======
import java.util.Optional;

public interface PlayerRepository {
   Optional<Player> findById(String playerId);

    void save(Player player);
>>>>>>> parent of fc30f66 (Mise en place du CRUD PLayer avec les tests nécéssaires)
}
