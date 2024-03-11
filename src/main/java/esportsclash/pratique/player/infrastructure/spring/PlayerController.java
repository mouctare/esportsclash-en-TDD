package esportsclash.pratique.player.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import esportsclash.pratique.player.application.usecases.CreatePlayerCommand;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    // ¨Patern mediator
    private Pipeline pipeline;

    public PlayerController(Pipeline pipeline){
        this.pipeline = pipeline;
    }

    @PostMapping
    public ResponseEntity<PlayerIdResponse> createPlayer(@RequestBody CreatePlayerDTO createPlayerDTO){
       var result = this.pipeline.send(new CreatePlayerCommand(createPlayerDTO.getName()));
        return new ResponseEntity<>(new PlayerIdResponse(result.getId()), HttpStatus.CREATED);
    }
}
