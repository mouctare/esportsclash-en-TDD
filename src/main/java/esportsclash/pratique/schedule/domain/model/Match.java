package esportsclash.pratique.schedule.domain.model;

import esportsclash.pratique.player.domain.model.BaseEntity;


public class Match extends BaseEntity<Match> {
    private String firstTeamId;

    private String secondTeamId;

    public Match(String id, String firstTeamId, String secondTeamId){
        this.id = id;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
    }

    public boolean featuresTeam(String id){
        return firstTeamId.equals(id) || secondTeamId.equals(id);
    }
    @Override
    public Match deepClone() {
        return null;
    }
}
