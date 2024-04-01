package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.team.application.ports.TeamQueries;
import esportsclash.pratique.team.domain.TeamViewModel;

public class GetTeamByIdCommandHandler implements Command.Handler<GetTeamByIdCommand, TeamViewModel> {
    // Interface
    private final TeamQueries teamQueries;

    public GetTeamByIdCommandHandler(TeamQueries teamQueries){
        this.teamQueries = teamQueries;
    }

    @Override
    public TeamViewModel handle(GetTeamByIdCommand command) {
        return teamQueries.getTeamById(command.getId());
    }
}
