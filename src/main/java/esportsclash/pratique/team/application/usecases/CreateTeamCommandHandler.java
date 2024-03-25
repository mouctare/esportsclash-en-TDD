package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Team;

import java.util.UUID;

public class CreateTeamCommandHandler implements Command.Handler<CreateTeamCommand, IdResponse> {
    private final TeamRepository teamRepository;

    public CreateTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    @Override
    public IdResponse handle(CreateTeamCommand createTeamCommand) {
        var team = new Team(
                UUID.randomUUID().toString(),
                createTeamCommand.getName());

        teamRepository.save(team);

        return new IdResponse(team.getId());
    }
}
