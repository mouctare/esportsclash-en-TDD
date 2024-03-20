package esportsclash.pratique.core.infrastructure.persistance.sql;

import esportsclash.pratique.core.infrastructure.persistance.BaseRepository;
import esportsclash.pratique.player.domain.model.BaseEntity;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public abstract class SQLBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    private final EntityManager entityManager;

    public SQLBaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<T> getEntityClass();

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(T entity) {
      entityManager.remove(entity);
    }

    @Override
    public Optional findById(String id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(), id));
    }

    @Override
    public void clear(){
        entityManager.createQuery("DELETE FROM " + getEntityClass().getSimpleName()).executeUpdate();
    }
}
