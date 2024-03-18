package esportsclash.pratique.auth.domain.model;

import esportsclash.pratique.player.domain.model.BaseEntity;

public class User extends BaseEntity {
    private String emailAdresse;
    private String password;

    public User() {
    }

    public User(String id, String emailAdresse, String password) {
        super(id);
        this.emailAdresse = emailAdresse;
        this.password = password;
    }

    public String getEmailAdresse() {
        return emailAdresse;
    }

    public void setEmailAdresse(String emailAdresse) {
        this.emailAdresse = emailAdresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
