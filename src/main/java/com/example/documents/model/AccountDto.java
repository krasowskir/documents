package com.example.documents.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class AccountDto {

    private String name;
    private String alter;
    private String telefonNummer;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlter() {
        return alter;
    }

    public void setAlter(String alter) {
        this.alter = alter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "name='" + name + '\'' +
                ", alter='" + alter + '\'' +
                ", telefonNummer='" + telefonNummer + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
