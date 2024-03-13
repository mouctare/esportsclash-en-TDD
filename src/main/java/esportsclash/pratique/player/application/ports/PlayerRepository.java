package esportsclash.pratique.player.application.ports;

import esportsclash.pratique.player.domain.model.Player;

import java.util.Optional;

public interface PlayerRepository {
   Optional<Player> findById(String playerId);

    void save(Player player);

    void delete(Player player);
}
