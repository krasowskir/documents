package com.example.documents.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.UUID;

import static org.hibernate.annotations.CascadeType.PERSIST;

@Entity
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String password;

    @OneToOne
    @Cascade(value = PERSIST)
    private Account account;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", account=" + account +
                '}';
    }
}
