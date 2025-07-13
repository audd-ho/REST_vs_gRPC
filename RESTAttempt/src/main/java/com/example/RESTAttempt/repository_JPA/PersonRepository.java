package com.example.RESTAttempt.repository_JPA;

import java.util.List;
import com.example.RESTAttempt.entity.Person;
import com.example.RESTAttempt.enums.Nations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<List<Person>> findByNationality(Nations nationality);
    Optional<List<Person>> findByEmailContaining(String email);
    //Optional<Person> findByPassportNumber(String passportNumber);
    Optional<Person> findByIdentificationNumberAndPassportNumberAndNationality(String identificationNumber, String passportNumber, Nations nationality);
    Optional<List<Person>> findByNameStartsWith(String name);




}