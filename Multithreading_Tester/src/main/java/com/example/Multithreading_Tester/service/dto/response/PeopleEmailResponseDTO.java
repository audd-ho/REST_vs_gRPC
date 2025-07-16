package com.example.Multithreading_Tester.service.dto.response;

import com.example.Multithreading_Tester.enums.Nations;

public class PeopleEmailResponseDTO {
    private String name;
    private String email;

    public PeopleEmailResponseDTO() {};
    public PeopleEmailResponseDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}