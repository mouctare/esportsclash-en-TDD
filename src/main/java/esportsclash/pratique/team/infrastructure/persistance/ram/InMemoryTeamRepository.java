package esportsclash.pratique.team.infrastructure.persistance.ram;

import esportsclash.pratique.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Team;

import java.util.Optional;

public class InMemoryTeamRepository extends InMemoryBaseRepository<Team> implements TeamRepository {
    @Override
    public Optional<Team> findByPlayerId(String playerId) {
        return entities.values().stream()
                .filter(team -> team.getMembers().stream().anyMatch(member -> member.getPlayerId().equals(playerId)))
                .findFirst();
    }
}
