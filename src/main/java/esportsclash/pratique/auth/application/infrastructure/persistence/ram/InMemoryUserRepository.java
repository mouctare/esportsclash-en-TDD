package esportsclash.pratique.auth.application.infrastructure.persistence.ram;

import esportsclash.pratique.auth.application.ports.UserRepository;
import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.core.infrastructure.persistance.ram.InMemoryBaseRepository;

public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {
    @Override
    public boolean isEmailAddressAvailable(String emailAddress) {
        return entities
                .values().stream()
                .noneMatch(user -> user.getEmailAdresse().equals(emailAddress));
    }
}
