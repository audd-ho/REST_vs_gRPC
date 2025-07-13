package com.example.Mutlithreading_Tester.service.dto.request;

import com.example.Mutlithreading_Tester.enums.Nations;

public class NationalityPOJO {
    private Nations nationality;

    public NationalityPOJO() {};

    public NationalityPOJO(Nations nationality) {
        this.nationality = nationality;
    }

    public void setNationality(Nations nationality) {
        this.nationality = nationality;
    }

    public Nations getNationality() {
        return nationality;
    }
}