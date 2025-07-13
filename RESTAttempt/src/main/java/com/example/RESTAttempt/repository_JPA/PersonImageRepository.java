package com.example.RESTAttempt.repository_JPA;

import com.example.RESTAttempt.entity.Person;
import com.example.RESTAttempt.entity.PersonImage;
import com.example.RESTAttempt.enums.Nations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonImageRepository extends JpaRepository<PersonImage, Long> {
    List<PersonImage> findByNationality(Nations nationality);
    Optional<PersonImage> findByIdentificationNumberAndPassportNumberAndNationality(String identificationNumber, String passportNumber, Nations nationality);

    List<PersonImage> findByEmailContaining(String email);
    //Optional<Person> findByPassportNumber(String passportNumber);
    List<PersonImage> findByNameStartsWith(String name);




}