package com.example.gRPCAttempt.entity;

import com.example.gRPCAttempt.enums.Nations;
import com.example.gRPCAttempt.enums.MaritalStatus;
import jakarta.persistence.*;

import com.example.gRPCAttempt.gRPC.people.PersonProtobufDTO;
//import com.example.gRPCAttempt.gRPC.people.Nations;
//import com.example.gRPCAttempt.gRPC.people.MaritalStatus;
/*
import com.example.gRPCAttempt.gRPC.people.Enums.*;
import com.example.gRPCAttempt.gRPC.people.Person.*;
*/

@Entity
@Table(
        name = "person",
        uniqueConstraints = @UniqueConstraint(columnNames = {"identification_number", "passport_number", "nationality"})
)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(/*unique = true, */name = "identification_number")
    private String identificationNumber;

    @Column(/*unique = true, */name = "passport_number")
    private String passportNumber;

    @Column(unique = true)
    private String email;

    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Nations nationality;
    @Enumerated(EnumType.STRING)
    @Column(name = "current_country")
    private Nations currentCountry;
    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;
    private int children;

    public Person(String identificationNumber, String passportNumber, String email, String name, int age, Nations nationality, Nations currentCountry, MaritalStatus maritalStatus, int children) {
        this.identificationNumber = identificationNumber;
        this.passportNumber = passportNumber;
        this.email = email;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.currentCountry = currentCountry;
        this.maritalStatus = maritalStatus;
        this.children = children;
    }

    public Person() {}

    public Person(PersonProtobufDTO protobufPerson) {
        this(protobufPerson.getIdentificationNumber(), protobufPerson.getPassportNumber(), protobufPerson.getEmail(), protobufPerson.getName(), protobufPerson.getAge(), Nations.valueOf(protobufPerson.getNationality().name()), Nations.valueOf(protobufPerson.getCurrentCountry().name()), MaritalStatus.valueOf(protobufPerson.getMaritalStatus().name()), protobufPerson.getChildren());
    }

    public PersonProtobufDTO toPersonProtobufDTO() {
        return PersonProtobufDTO.newBuilder()
                .setId(this.getId())
                .setIdentificationNumber(this.getIdentificationNumber())
                .setPassportNumber(this.getPassportNumber())
                .setEmail(this.getEmail())
                .setName(this.getName())
                .setAge(this.getAge())
                .setNationality(com.example.gRPCAttempt.gRPC.people.Nations.valueOf(this.getNationality().name()))
                .setCurrentCountry(com.example.gRPCAttempt.gRPC.people.Nations.valueOf(this.getCurrentCountry().name()))
                .setMaritalStatus(com.example.gRPCAttempt.gRPC.people.MaritalStatus.valueOf(this.getMaritalStatus().name()))
                .setChildren(this.getChildren())
                .build();

    }

    public Person fromPersonProtobufDTO(PersonProtobufDTO protobufPerson) {
        // public Person(String identificationNumber, String passportNumber, String email, String name, int age, Nations nationality, Nations currentCountry, MaritalStatus maritalStatus, int children) {
        return new Person(protobufPerson.getIdentificationNumber(), protobufPerson.getPassportNumber(), protobufPerson.getEmail(), protobufPerson.getName(), protobufPerson.getAge(), Nations.valueOf(protobufPerson.getNationality().name()), Nations.valueOf(protobufPerson.getCurrentCountry().name()), MaritalStatus.valueOf(protobufPerson.getMaritalStatus().name()), protobufPerson.getChildren());
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setNationality(Nations nationality) {
        this.nationality = nationality;
    }
    public void setCurrentCountry(Nations currentCountry) {
        this.currentCountry = currentCountry;
    }
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public void setChildren(int children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public String getPassportNumber() {
        return passportNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Nations getNationality() {
        return nationality;
    }
    public Nations getCurrentCountry() {
        return currentCountry;
    }
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }
    public int getChildren() {
        return children;
    }
}

