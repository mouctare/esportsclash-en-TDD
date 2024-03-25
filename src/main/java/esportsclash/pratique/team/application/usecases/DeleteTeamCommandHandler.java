package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.team.application.ports.TeamRepository;
import esportsclash.pratique.team.domain.Team;

import java.util.UUID;

public class DeleteTeamCommandHandler implements Command.Handler<DeleteTeamCommand, Void> {
    private final TeamRepository teamRepository;

    public DeleteTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Void handle(DeleteTeamCommand command) {
        var team = teamRepository.findById(command.getId()).orElseThrow(
                () -> new NotFoundException("Team", command.getId())
        );

        teamRepository.delete(team);

        return null;

    }
}
