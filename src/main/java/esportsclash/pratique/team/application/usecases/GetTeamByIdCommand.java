package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.team.domain.TeamViewModel;

public class GetTeamByIdCommand implements Command<TeamViewModel> {
    private String id;

    public GetTeamByIdCommand(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
