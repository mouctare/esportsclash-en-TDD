package esportsclash.pratique.player.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.viewmodel.PlayerViewModel;

public class GetPlayerCommandHandler implements Command.Handler<GetPlayerByIdCommand, PlayerViewModel> {
    private final PlayerRepository playerRepository;

    public GetPlayerCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerViewModel handle(GetPlayerByIdCommand command) {
        var player = playerRepository.findById(command.getId()).orElseThrow(
                () -> new NotFoundException("Player not found", command.getId())
        );
        return new PlayerViewModel(
                player.getId(),
                player.getName()
        );
    }
}
