package esportsclash.pratique.team.infrastructure.persistance.spring.dto;

public class RemovePlayerFromTeamDTO {
    private String playerId;
    private String teamId;


    public RemovePlayerFromTeamDTO() {

    }

    public RemovePlayerFromTeamDTO(String playerId, String teamId) {
        this.playerId = playerId;
        this.teamId = teamId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTeamId() {
        return teamId;
    }

}
