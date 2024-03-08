package esportsclash.pratique.player;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @PostMapping
    public ResponseEntity<PlayerIdResponse> createPlayer(@RequestBody CreatePlayerDTO createPlayerDTO){
        var playerUseCase = new CreatePlayerUseCase(playerRepository);
        var result = playerUseCase.execute(createPlayerDTO.getName());
        return new ResponseEntity<>(new PlayerIdResponse(result.getId()), HttpStatus.CREATED);
    }
}
