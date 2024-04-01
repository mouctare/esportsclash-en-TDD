package esportsclash.pratique.team.application.ports;

import esportsclash.pratique.core.infrastructure.persistance.BaseRepository;
import esportsclash.pratique.team.domain.Team;

import java.util.Optional;

public interface TeamRepository extends BaseRepository<Team> {
    Optional<Team> findByPlayerId(String playerId);
}
