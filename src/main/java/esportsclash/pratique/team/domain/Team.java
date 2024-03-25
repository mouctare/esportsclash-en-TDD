package esportsclash.pratique.team.domain;

import esportsclash.pratique.player.domain.model.BaseEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// Aggregate
public class Team  extends BaseEntity {
    private String name;
    private Set<TeamMember> members;

    public Team(String id, String name) {
        this.name = name;
        this.id = id;
        this.members = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addMember(String playerId, Role role) {
        if (this.members.stream().anyMatch(member -> member.playerId.equals(playerId))) {
            throw new IllegalArgumentException("Player already in team");
        }


        if (this.members.stream().anyMatch(member -> member.role == role)) {
            throw new IllegalArgumentException("Role is already taken");
        }

        var member = new TeamMember(UUID.randomUUID().toString(), playerId, role);
        this.members.add(member);
    }


    public void removeMember(String playerId){
        if (this.members.stream().noneMatch(member -> member.playerId.equals(playerId))) {
            throw  new IllegalArgumentException("Player not in team");
        }

        this.members.removeIf(member -> member.playerId.equals(playerId));
    }

    public boolean hasMember(String playerId, Role role){
     return this.members
                .stream()
                .anyMatch(member -> member.playerId.equals(playerId) && member.role.equals(role));
    }

    // cette classe n'a pas de raison d'exister sans Team
    class TeamMember {
        private String id;
        private String playerId;
        private Role role;

        public TeamMember(String id, String playerId, Role role){
            this.id = id;
            this.playerId = playerId;
            this.role = role;
        }
    }
}
