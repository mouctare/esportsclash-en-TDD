package esportsclash.pratique.team.infrastructure.persistance.ram;

import esportsclash.pratique.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Team;

public class InMemoryTeamRepository extends InMemoryBaseRepository<Team> implements TeamRepository {
}
