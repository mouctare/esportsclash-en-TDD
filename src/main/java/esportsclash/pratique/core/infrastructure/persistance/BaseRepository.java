package esportsclash.pratique.core.infrastructure.persistance;

import esportsclash.pratique.player.domain.model.BaseEntity;

import java.util.Optional;

public interface BaseRepository<T extends BaseEntity> {
        void save(T entity);
        void delete(T entity);
        Optional<T> findById(String id);
        public void clear();
}
