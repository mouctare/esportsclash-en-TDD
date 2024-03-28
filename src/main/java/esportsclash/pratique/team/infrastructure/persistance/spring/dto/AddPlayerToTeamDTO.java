package esportsclash.pratique.team.infrastructure.persistance.spring.dto;

import esportsclash.pratique.team.domain.Role;

public class AddPlayerToTeamDTO {
    private String playerId;
    private String teamId;
    private String role;

    public AddPlayerToTeamDTO(){

    }

    public AddPlayerToTeamDTO(String playerId, String teamId, String role) {
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
        return Role.fromString(role);
    }
}
