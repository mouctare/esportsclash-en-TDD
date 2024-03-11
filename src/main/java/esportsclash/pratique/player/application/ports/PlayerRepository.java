package esportsclash.pratique.player.application.ports;

import esportsclash.pratique.player.domain.model.Player;

public interface PlayerRepository {
    Player findById(String playerId);

    void save(Player player);
}
