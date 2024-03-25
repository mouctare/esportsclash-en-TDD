package esportsclash.pratique.core.infrastructure.persistance.ram;

import esportsclash.pratique.core.infrastructure.persistance.BaseRepository;
import esportsclash.pratique.player.domain.model.BaseEntity;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryBaseRepository<T extends BaseEntity<T>> implements BaseRepository<T> {
    protected Map<String, T> entities = new HashMap<>();


    @Override
    public Optional<T> findById(String id) {
        var entity = this.entities.get(id);
        return entity == null ? Optional.empty() : Optional.of(entity.deepClone());
    }


    @Override
    public void save(T entity) {
        this.entities.put(entity.getId(), entity.deepClone());
    }

    @Override
    public void delete(T entity) {
        this.entities.remove(entity.getId());
    }

    public void clear(){
        this.entities.clear();
    }
}
