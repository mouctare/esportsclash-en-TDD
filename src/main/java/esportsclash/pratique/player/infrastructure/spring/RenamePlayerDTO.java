package esportsclash.pratique.player.infrastructure.spring;

public class RenamePlayerDTO {
    public String name;

    public RenamePlayerDTO(String name) {
        this.name = name;
    }

    public RenamePlayerDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
