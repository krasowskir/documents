package com.example.documents.model;

import org.springframework.web.multipart.MultipartFile;

public class FormDataWithFile {

    private String name;
    private String alter;
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

    @Override
    public String toString() {
        return "FormDataWithFile{" +
                "name='" + name + '\'' +
                ", alter='" + alter + '\'' +
                ", email='" + email + '\'' +
                ", imageFile=" + imageFile +
                '}';
    }
}
