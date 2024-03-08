package esportsclash.pratique.player;

import java.util.UUID;

public class CreatePlayerUseCase {
    private final PlayerRepository playerRepository;

    public CreatePlayerUseCase (PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    PlayerIdResponse execute(String name){
        var player = new Player(UUID.randomUUID().toString(), name);
        playerRepository.save(player);

        return new PlayerIdResponse(player.getId());

    }
}
