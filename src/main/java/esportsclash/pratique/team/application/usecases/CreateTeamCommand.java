package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;

public class CreateTeamCommand implements Command<IdResponse> {
    private String name;

    public CreateTeamCommand(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
