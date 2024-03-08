package esportsclash.pratique.player;

import java.util.HashMap;
import java.util.Map;

public class PlayerRepository {

  // This class represents a simple in-memory player repository.
  private Map<String, Player> players = new HashMap<>();

  /**
   * Finds a player by their ID.
   *
   * @param playerId The ID of the player to find.
   * @return The player object if found, null otherwise.
   */
  public Player find(String playerId) {
    return players.get(playerId);
  }

  /**
   * Saves a player to the in-memory repository.
   *
   * @param player The player object to save.
   */
  public void save(Player player) {
    this.players.put(player.getId(), player);
  }
}