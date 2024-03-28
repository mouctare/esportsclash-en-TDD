package esportsclash.pratique.team.infrastructure.persistance.spring.controller;

import an.awesome.pipelinr.Pipeline;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;
import esportsclash.pratique.player.application.usecases.CreatePlayerCommand;
import esportsclash.pratique.player.application.usecases.DeletePlayerCommand;
import esportsclash.pratique.player.application.usecases.GetPlayerByIdCommand;
import esportsclash.pratique.player.application.usecases.RenamePlayerCommand;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;
import esportsclash.pratique.player.domain.viewmodel.PlayerViewModel;
import esportsclash.pratique.player.infrastructure.spring.CreatePlayerDTO;
import esportsclash.pratique.player.infrastructure.spring.RenamePlayerDTO;
import esportsclash.pratique.team.application.usecases.AddPlayerToTeamCommand;
import esportsclash.pratique.team.application.usecases.CreateTeamCommand;
import esportsclash.pratique.team.application.usecases.DeleteTeamCommand;
import esportsclash.pratique.team.application.usecases.RemovePlayerFromTeamCommand;
import esportsclash.pratique.team.infrastructure.persistance.spring.dto.AddPlayerToTeamDTO;
import esportsclash.pratique.team.infrastructure.persistance.spring.dto.CreateTeamDTO;
import esportsclash.pratique.team.infrastructure.persistance.spring.dto.RemovePlayerFromTeamDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@Transactional
public class TeamController {
    private Pipeline pipeline;

    public TeamController(Pipeline pipeline){
        this.pipeline = pipeline;
    }

    @PostMapping
    public ResponseEntity<IdResponse> createTeam(@RequestBody CreateTeamDTO createTeamDTO){
        var result = this.pipeline.send(new CreateTeamCommand(createTeamDTO.getName()));
        return new ResponseEntity<>(new IdResponse(result.getId()), HttpStatus.CREATED);
    }

    @PostMapping("/add-player-to-team")
    public ResponseEntity<Void> addPlayerToTeam(@RequestBody AddPlayerToTeamDTO dto){
        var result = this.pipeline.send(new AddPlayerToTeamCommand(
                dto.getPlayerId(),
                dto.getTeamId(),
                dto.getRole()
        ));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/remove-player-to-team")
    public ResponseEntity<Void> removePlayerFromTeam(@RequestBody RemovePlayerFromTeamDTO dto){
        var result = this.pipeline.send(new RemovePlayerFromTeamCommand(
                dto.getPlayerId(),
                dto.getTeamId()

        ));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id){
        this.pipeline.send(new DeleteTeamCommand(id));
        return ResponseEntity.noContent().build();
    }

}
