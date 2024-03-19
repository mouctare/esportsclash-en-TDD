package esportsclash.pratique.auth.application.ports;

import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.core.infrastructure.persistance.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    boolean isEmailAddressAvailable(String emailAddress);

}
