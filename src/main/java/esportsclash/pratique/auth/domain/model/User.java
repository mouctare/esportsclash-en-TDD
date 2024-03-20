package esportsclash.pratique.auth.domain.model;

import esportsclash.pratique.player.domain.model.BaseEntity;

public class User extends BaseEntity {
    private String emailAddress;
    private String password;

    public User() {
    }

    public User(String id, String emailAddress, String password) {
        super(id);
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
