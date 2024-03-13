package esportsclash.pratique.player.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import esportsclash.pratique.player.application.usecases.CreatePlayerCommand;
import esportsclash.pratique.player.application.usecases.DeletePlayerCommand;
import esportsclash.pratique.player.application.usecases.GetPlayerByIdCommand;
import esportsclash.pratique.player.application.usecases.RenamePlayerCommand;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;
import esportsclash.pratique.player.domain.viewmodel.PlayerViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@Transactional
public class PlayerController {

    // ¨Patern mediator
    private Pipeline pipeline;

    public PlayerController(Pipeline pipeline){
        this.pipeline = pipeline;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerViewModel> getById(@PathVariable("id") String id){
        var result = this.pipeline.send(new GetPlayerByIdCommand(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlayerIdResponse> createPlayer(@RequestBody CreatePlayerDTO createPlayerDTO){
       var result = this.pipeline.send(new CreatePlayerCommand(createPlayerDTO.getName()));
        return new ResponseEntity<>(new PlayerIdResponse(result.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Void> changePlayerName(@RequestBody RenamePlayerDTO renamePlayerDTO,
                                                 @PathVariable("id") String playerId){
         this.pipeline.send(new RenamePlayerCommand(playerId, renamePlayerDTO.getName()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerName(@PathVariable("id") String playerId){
        this.pipeline.send(new DeletePlayerCommand(playerId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
