package com.example.Multithreading_Tester.service.dto.response;

public class PeopleEmailImageBytesResponseDTO_gRPC {
    private String name;
    private String email;
    private byte[] biometrics;

    public PeopleEmailImageBytesResponseDTO_gRPC() {};
    public PeopleEmailImageBytesResponseDTO_gRPC(String name, String email, byte[] biometrics) {
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
    public byte[] getBiometrics() {
        return biometrics;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setBiometrics(byte[] biometrics) {
        this.biometrics = biometrics;
    }
}