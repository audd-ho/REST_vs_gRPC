/*package com.example.RESTAttempt.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class PersonImage extends Person {
    private String biometrics;

}
*/

package com.example.gRPCAttempt.entity;

import com.example.gRPCAttempt.enums.MaritalStatus;
import com.example.gRPCAttempt.enums.Nations;
import com.example.gRPCAttempt.gRPC.peopleimage.PersonImageBytesProtobufDTO;
import com.google.protobuf.ByteString;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Base64;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
        //name = "person",
        uniqueConstraints = @UniqueConstraint(columnNames = {"identification_number", "passport_number", "nationality"})
)
public class PersonImageBytes {
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

    //private int DOB; // DDMMYYYY
    @Column(unique = true)
    @Lob
    private byte[] biometrics;

    public PersonImageBytesProtobufDTO toPersonImageBytesProtobufDTO() {
        return PersonImageBytesProtobufDTO.newBuilder()
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
                .setBiometrics(ByteString.copyFrom(this.getBiometrics()))
                .build();

    }

    public PersonImageBytes fromPersonImageBytesProtobufDTO(PersonImageBytesProtobufDTO protobufPersonImageBytes) {
        // public Person(String identificationNumber, String passportNumber, String email, String name, int age, Nations nationality, Nations currentCountry, MaritalStatus maritalStatus, int children) {
        return new PersonImageBytes(protobufPersonImageBytes.getId(), protobufPersonImageBytes.getIdentificationNumber(), protobufPersonImageBytes.getPassportNumber(), protobufPersonImageBytes.getEmail(), protobufPersonImageBytes.getName(), protobufPersonImageBytes.getAge(), Nations.valueOf(protobufPersonImageBytes.getNationality().name()), Nations.valueOf(protobufPersonImageBytes.getCurrentCountry().name()), MaritalStatus.valueOf(protobufPersonImageBytes.getMaritalStatus().name()), protobufPersonImageBytes.getChildren(), protobufPersonImageBytes.getBiometrics().toByteArray());
    }

    public PersonImage toPersonImage() {
        return new PersonImage(this.getId(), this.getIdentificationNumber(), this.getPassportNumber(), this.getEmail(), this.getName(), this.getAge(), com.example.gRPCAttempt.enums.Nations.valueOf(this.getNationality().name()), com.example.gRPCAttempt.enums.Nations.valueOf(this.getCurrentCountry().name()), com.example.gRPCAttempt.enums.MaritalStatus.valueOf(this.getMaritalStatus().name()), this.getChildren(), Base64.getEncoder().encodeToString(this.getBiometrics()));
    }

    public PersonImageBytes fromPersonImage(PersonImage personImage) {
        return new PersonImageBytes(personImage.getId(), personImage.getIdentificationNumber(), personImage.getPassportNumber(), personImage.getEmail(), personImage.getName(), personImage.getAge(), Nations.valueOf(personImage.getNationality().name()), Nations.valueOf(personImage.getCurrentCountry().name()), MaritalStatus.valueOf(personImage.getMaritalStatus().name()), personImage.getChildren(), Base64.getDecoder().decode(personImage.getBiometrics()));
    }

    public PersonImageBytes(String identificationNumber, String passportNumber, String email, String name, int age, Nations nationality, Nations currentCountry, MaritalStatus maritalStatus, int children, byte[] biometrics) {
        this.identificationNumber = identificationNumber;
        this.passportNumber = passportNumber;
        this.email = email;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.currentCountry = currentCountry;
        this.maritalStatus = maritalStatus;
        this.children = children;
        this.biometrics = biometrics;
    }

    public PersonImageBytes(Long id, String identificationNumber, String passportNumber, String email, String name, int age, Nations nationality, Nations currentCountry, MaritalStatus maritalStatus, int children, byte[] biometrics) {
        this.id = id;
        this.identificationNumber = identificationNumber;
        this.passportNumber = passportNumber;
        this.email = email;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.currentCountry = currentCountry;
        this.maritalStatus = maritalStatus;
        this.children = children;
        this.biometrics = biometrics;
    }

    public PersonImageBytes() {}

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
    public void setBiometrics(byte[] biometrics) {
        this.biometrics = biometrics;
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
    public byte[] getBiometrics() {
        return biometrics;
    }

}




// base 64 image 18kb
// put into database
// call new database entity with image

// rest + protobuf as payload type

// client <- rest json/protobuf -> s1 <- grpc protobuf -> s2

// TRY MAINTAIN CONNECTION GRPC

// multithread on client to simulate multiple clients to a server

// multithread server to see diff? maybe?

//refactor Optional

//tls plain data, secrity, auth