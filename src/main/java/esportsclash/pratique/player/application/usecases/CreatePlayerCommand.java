package esportsclash.pratique.player.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.player.domain.viewmodel.PlayerIdResponse;

public class CreatePlayerCommand implements Command<PlayerIdResponse> {
    private String name;

    public CreatePlayerCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
