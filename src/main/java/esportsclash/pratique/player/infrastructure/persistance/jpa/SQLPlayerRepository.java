package esportsclash.pratique.player.infrastructure.persistance.jpa;

import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;

import java.util.Optional;

public class SQLPlayerRepository implements PlayerRepository {
    private final SQLPlayerDataAccessor dataAccessor;

    public SQLPlayerRepository(SQLPlayerDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    @Override
    public Optional<Player> findById(String playerId) {
       return dataAccessor.findById(playerId);
    }

    @Override
    public void save(Player player) {
        dataAccessor.save(player);
    }
}
