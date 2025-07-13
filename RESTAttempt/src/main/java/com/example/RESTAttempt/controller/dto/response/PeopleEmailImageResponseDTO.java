package com.example.RESTAttempt.controller.dto.response;

public class PeopleEmailImageResponseDTO {
    private String name;
    private String email;
    private String biometrics;

    public PeopleEmailImageResponseDTO() {};
    public PeopleEmailImageResponseDTO(String name, String email, String biometrics) {
        this.name = name;
        this.email = email;
        this.biometrics = biometrics;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getBiometrics() {
        return biometrics;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setBiometrics(String biometrics) {
        this.biometrics = biometrics;
    }
}