package esportsclash.pratique.team.application.ports;

import esportsclash.pratique.team.domain.TeamViewModel;

public interface TeamQueries {
    TeamViewModel getTeamById(String id);
}
