package esportsclash.pratique.player.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import esportsclash.pratique.player.domain.model.Player;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;

import java.util.UUID;

public class CreatePlayerCommandHandler implements Command.Handler<CreatePlayerCommand, PlayerIdResponse>{
    private final InMemoryPlayerRepository playerRepository;

    public CreatePlayerCommandHandler(InMemoryPlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerIdResponse handle(CreatePlayerCommand command){
        var player = new Player(UUID.randomUUID().toString(), command.getName());
        playerRepository.save(player);

        return new PlayerIdResponse(player.getId());

    }


}
