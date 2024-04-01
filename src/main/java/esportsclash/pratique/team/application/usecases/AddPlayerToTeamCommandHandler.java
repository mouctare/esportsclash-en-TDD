package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.core.domain.exception.BadRequestException;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.team.application.ports.TeamRepository;

public class AddPlayerToTeamCommandHandler implements Command.Handler<AddPlayerToTeamCommand, Void> {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public AddPlayerToTeamCommandHandler(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Void handle(AddPlayerToTeamCommand command) {
        var player = playerRepository
                .findById(command.getPlayerId())
                .orElseThrow(() -> new NotFoundException("Player", command.getPlayerId()));

        var team = teamRepository
                .findById(command.getTeamId())
                .orElseThrow(() -> new NotFoundException("Team", command.getTeamId()));

        var teamPlayerBelongsTo = teamRepository.findByPlayerId(player.getId());
        if (teamPlayerBelongsTo.isPresent()){
            throw new BadRequestException("This player is already in a team");
        }

        team.addMember(player.getId(), command.getRole());

        teamRepository.save(team);

        return null;

    }
}
