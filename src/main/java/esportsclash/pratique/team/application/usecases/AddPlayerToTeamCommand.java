package esportsclash.pratique.team.application.usecases;

import an.awesome.pipelinr.Command;
import esportsclash.pratique.team.domain.Role;

public class AddPlayerToTeamCommand implements Command<Void> {
    private String playerId;
    private String teamId;
    private  Role role;

    public AddPlayerToTeamCommand(String playerId, String teamId, Role role) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.role = role;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTeamId() {
        return teamId;
    }

    public Role getRole() {
        return role;
    }
}
