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

package com.example.Mutlithreading_Tester.entity;

import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.enums.MaritalStatus;
import com.example.Mutlithreading_Tester.entity.Person;
import com.example.Mutlithreading_Tester.entity.PersonImageBytes;
import com.example.Mutlithreading_Tester.enums.MaritalStatus;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.gRPC.peopleimage.PersonImageProtobufDTO;
import jakarta.persistence.*;

import java.util.Base64;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
        //name = "person",
        uniqueConstraints = @UniqueConstraint(columnNames = {"identification_number", "passport_number", "nationality"})
)
public class PersonImage {
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
    private String biometrics;

    public PersonImageProtobufDTO toPersonImageProtobufDTO() {
        return PersonImageProtobufDTO.newBuilder()
                .setId(this.getId())
                .setIdentificationNumber(this.getIdentificationNumber())
                .setPassportNumber(this.getPassportNumber())
                .setEmail(this.getEmail())
                .setName(this.getName())
                .setAge(this.getAge())
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(this.getNationality().name()))
                .setCurrentCountry(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(this.getCurrentCountry().name()))
                .setMaritalStatus(com.example.Mutlithreading_Tester.gRPC.people.MaritalStatus.valueOf(this.getMaritalStatus().name()))
                .setChildren(this.getChildren())
                .setBiometrics(this.getBiometrics())
                .build();

    }

    public static PersonImage fromPersonImageProtobufDTO(PersonImageProtobufDTO protobufPersonImage) {
        // public Person(String identificationNumber, String passportNumber, String email, String name, int age, Nations nationality, Nations currentCountry, MaritalStatus maritalStatus, int children) {
        return new PersonImage(protobufPersonImage.getId(), protobufPersonImage.getIdentificationNumber(), protobufPersonImage.getPassportNumber(), protobufPersonImage.getEmail(), protobufPersonImage.getName(), protobufPersonImage.getAge(), Nations.valueOf(protobufPersonImage.getNationality().name()), Nations.valueOf(protobufPersonImage.getCurrentCountry().name()), MaritalStatus.valueOf(protobufPersonImage.getMaritalStatus().name()), protobufPersonImage.getChildren(), protobufPersonImage.getBiometrics());
    }

    public PersonImageBytes toPersonImageBytes() {
        return new PersonImageBytes(this.getId(), this.getIdentificationNumber(), this.getPassportNumber(), this.getEmail(), this.getName(), this.getAge(), this.getNationality(), this.getCurrentCountry(), this.getMaritalStatus(), this.getChildren(), Base64.getDecoder().decode(this.getBiometrics()));
    }

    public static PersonImage fromPersonImageBytes(PersonImageBytes personImageBytes) {
        return new PersonImage(personImageBytes.getId(), personImageBytes.getIdentificationNumber(), personImageBytes.getPassportNumber(), personImageBytes.getEmail(), personImageBytes.getName(), personImageBytes.getAge(), personImageBytes.getNationality(), personImageBytes.getCurrentCountry(), personImageBytes.getMaritalStatus(), personImageBytes.getChildren(), Base64.getEncoder().encodeToString(personImageBytes.getBiometrics()));
    }

    public PersonImageBytes toPersonImageBytes_NoID() {
        return new PersonImageBytes(this.getIdentificationNumber(), this.getPassportNumber(), this.getEmail(), this.getName(), this.getAge(), this.getNationality(), this.getCurrentCountry(), this.getMaritalStatus(), this.getChildren(), Base64.getDecoder().decode(this.getBiometrics()));
    }

    public PersonImage(String identificationNumber, String passportNumber, String email, String name, int age, Nations nationality, Nations currentCountry, MaritalStatus maritalStatus, int children, String biometrics) {
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

    public PersonImage(Long id, String identificationNumber, String passportNumber, String email, String name, int age, Nations nationality, Nations currentCountry, MaritalStatus maritalStatus, int children, String biometrics) {
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

    public PersonImage() {}

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
    public void setBiometrics(String biometrics) {
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
    public String getBiometrics() {
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