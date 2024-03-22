package esportsclash.pratique.auth.application.infrastructure.persistence.jpa;

import esportsclash.pratique.auth.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface SQLUserAccessor extends CrudRepository<User, String> {
    boolean existsByEmailAddress(String emailAddress);
    User findByEmailAddress(String emailAddress);
}
