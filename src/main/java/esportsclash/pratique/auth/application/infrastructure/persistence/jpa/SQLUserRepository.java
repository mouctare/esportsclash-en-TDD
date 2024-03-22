package esportsclash.pratique.auth.application.infrastructure.persistence.jpa;

import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.core.infrastructure.persistance.sql.SQLBaseRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SQLUserRepository extends SQLBaseRepository<User> implements UserRepository {
    private final SQLUserAccessor userAccessor;

    public SQLUserRepository(EntityManager entityManager, SQLUserAccessor userAccessor){
        super(entityManager);
        this.userAccessor = userAccessor;
    }
    @Override
    public boolean isEmailAddressAvailable(String emailAddress) {
        return !userAccessor.existsByEmailAddress(emailAddress);
    }

    @Override
    public Optional<User> findByEmailAddress(String emailAddress) {
        return Optional.ofNullable(userAccessor.findByEmailAddress(emailAddress));
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
