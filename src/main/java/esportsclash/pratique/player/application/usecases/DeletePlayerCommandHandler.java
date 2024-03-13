package esportsclash.pratique.player.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.application.ports.PlayerRepository;

public class DeletePlayerCommandHandler implements Command.Handler<DeletePlayerCommand, Void> {
    private final PlayerRepository playerRepository;

    public DeletePlayerCommandHandler(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
    @Override
    public Void handle(DeletePlayerCommand deleteCommand) {
        var player = playerRepository.findById(deleteCommand.getId()).orElseThrow(
                () -> new NotFoundException("Player", deleteCommand.getId())
        );
        playerRepository.delete(player);
        return null;
    }
}
