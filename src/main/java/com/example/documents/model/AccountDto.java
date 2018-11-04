package com.example.documents.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class AccountDto {

    private String name;
    private String alter;
    private String telefonNummer;
    private String email;
    private MultipartFile imageFile;

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

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
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
                ", imageFile=" + imageFile +
                '}';
    }

    public Account toAccount() throws IOException {
        Account destAccount = new Account();
        destAccount.setId(UUID.randomUUID());
        destAccount.setTelefonNummer(this.getTelefonNummer());
        destAccount.setAlter(Integer.parseInt(this.getAlter()));
        destAccount.setName(this.getName());
        destAccount.setEmailAdresse(this.email);
        if(this.imageFile.getBytes() != null) {
            destAccount.setImage(this.imageFile.getBytes());
        }

        return destAccount;
    }
}
