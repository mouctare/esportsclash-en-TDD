package esportsclash.pratique.player.infrastructure.persistance.jpa;

import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;

public class SQLPlayerRepository implements PlayerRepository {
    private final SQLPlayerDataAccessor dataAccessor;

    public SQLPlayerRepository(SQLPlayerDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    @Override
    public Player findById(String playerId) {
        var sqlPlayerQuery = dataAccessor.findById(playerId);
        if (sqlPlayerQuery.isEmpty()) {
            return null;
        }

        var sqlPlayer = sqlPlayerQuery.get();

        var player = new Player(sqlPlayer.getId(), sqlPlayer.getName());
        return player;
    }

    @Override
    public void save(Player player) {
        var sqlPlayer = new SQLPlayer(
                player.getId(),
                player.getName()
        );
        dataAccessor.save(sqlPlayer);
    }
}
