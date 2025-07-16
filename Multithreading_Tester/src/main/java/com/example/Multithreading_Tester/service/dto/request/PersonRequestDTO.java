package com.example.Multithreading_Tester.service.dto.request;

import com.example.Multithreading_Tester.enums.Nations;

public class PersonRequestDTO {
    private String identificationNumber;
    private String passportNumber;
    private Nations nationality;

    public PersonRequestDTO() {};

    public PersonRequestDTO(String identificationNumber, String passportNumber, Nations nationality) {
        this.identificationNumber = identificationNumber;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public String getPassportNumber() {
        return passportNumber;
    }
    public Nations getNationality() {
        return nationality;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    public void setNationality(Nations nationality) {
        this.nationality = nationality;
    }
}