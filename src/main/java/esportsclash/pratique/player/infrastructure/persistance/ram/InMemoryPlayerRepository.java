package esportsclash.pratique.player.infrastructure.persistance.ram;

import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPlayerRepository implements PlayerRepository {

  // This class represents a simple in-memory player repository.
  private Map<String, Player> players = new HashMap<>();

  /**
   * Finds a player by their ID.
   *
   * @param playerId The ID of the player to find.
   * @return The player object if found, null otherwise.
   */
  @Override
  public Player findById(String playerId) {
    return players.get(playerId);
  }

  /**
   * Saves a player to the in-memory repository.
   *
   * @param player The player object to save.
   */
  @Override
  public void save(Player player) {
    this.players.put(player.getId(), player);
  }
}