package com.example.documents.model;

public class CredentialsDto {

    private String benutzername;
    private String password;

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CredentialsDto{" +
                "benutzername='" + benutzername + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
