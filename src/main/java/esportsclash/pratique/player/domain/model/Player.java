package esportsclash.pratique.player.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")

public class Player extends BaseEntity {
    @Column
    private String name;

    public Player() {
    }


    public Player(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId(){
        return id;
    }

    public void rename(String newName){
        this.name = newName;
    }
}
