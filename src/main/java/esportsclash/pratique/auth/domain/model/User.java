package esportsclash.pratique.auth.domain.model;

import esportsclash.pratique.player.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password_hash")
    private String passwordHash;

    public User() {
    }

    public User(String id, String emailAddress, String passwordHash) {
        super(id);
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


}
