package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.auth.domain.model.viewModel.IdResponse;

public class DeleteTeamCommand implements Command<Void> {
    private String id;

    public DeleteTeamCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
