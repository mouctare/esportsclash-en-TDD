package esportsclash.pratique.core.infrastructure.persistance.ram;

import esportsclash.pratique.core.infrastructure.persistance.BaseRepository;
import esportsclash.pratique.player.domain.model.BaseEntity;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    private Map<String, T> entities = new HashMap<>();


    @Override
    public Optional<T> findById(String playerId) {
        return Optional.ofNullable(this.entities.get(playerId));
    }


    @Override
    public void save(T entity) {
        this.entities.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        this.entities.remove(entity.getId());
    }
}
