package com.example.RESTAttempt.controller.dto.request;

import com.example.RESTAttempt.enums.Nations;

public class NationalityPOJO {
    private Nations nationality;

    public NationalityPOJO() {};

    public void setNationality(Nations nationality) {
        this.nationality = nationality;
    }

    public Nations getNationality() {
        return nationality;
    }
}