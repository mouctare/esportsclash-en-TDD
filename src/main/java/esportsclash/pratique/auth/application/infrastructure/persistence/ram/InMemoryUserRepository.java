package esportsclash.pratique.auth.application.infrastructure.persistence.ram;

import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.core.infrastructure.persistance.ram.InMemoryBaseRepository;

import java.util.Optional;

public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {
    @Override
    public boolean isEmailAddressAvailable(String emailAddress) {
        return entities
                .values().stream()
                .noneMatch(user -> user.getEmailAddress().equals(emailAddress));
    }

    @Override
    public Optional<User> findByEmailAddress(String emailAddress) {
        return entities
                .values()
                .stream()
                .filter(user -> user.getEmailAddress().equals(emailAddress))
                .findFirst();
    }
}
