package esportsclash.pratique.player.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.core.domain.exception.NotFoundException;
import esportsclash.pratique.player.application.ports.PlayerRepository;
import esportsclash.pratique.player.domain.model.Player;

import java.util.Optional;

public class RenamePlayerCommandHandler implements Command.Handler<RenamePlayerCommand, Void> {
   private final PlayerRepository playerRepository;

    public RenamePlayerCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public Void handle(RenamePlayerCommand renamePlayerCommand) {
        var player = playerRepository.findById(renamePlayerCommand.getId()).orElseThrow(
                () -> new NotFoundException("Player", renamePlayerCommand.getId()));

        player.rename(renamePlayerCommand.getName());
        playerRepository.save(player);
        return null;
    }
}
