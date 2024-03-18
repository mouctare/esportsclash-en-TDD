package esportsclash.pratique.auth.application.ports;

import esportsclash.pratique.auth.domain.model.User;
import esportsclash.pratique.core.infrastructure.persistance.BaseRepository;

public interface UserRepository extends BaseRepository<User> {
}
