package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.team.application.ports.TeamRepository;

public class RemovePlayerFromTeamCommandHandler implements Command.Handler<RemovePlayerFromTeamCommand, Void> {
    private final TeamRepository teamRepository;

    public RemovePlayerFromTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Void handle(RemovePlayerFromTeamCommand command) {
        var team = teamRepository
                .findById(command.getTeamId())
                .orElseThrow(
                        () -> new NotFoundException("Team", command.getTeamId())
                );
        team.removeMember(command.getPlayerId());

        teamRepository.save(team);

        return null;
    }
}
