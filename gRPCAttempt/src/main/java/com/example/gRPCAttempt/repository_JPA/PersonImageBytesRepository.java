package com.example.gRPCAttempt.repository_JPA;

import com.example.gRPCAttempt.entity.PersonImageBytes;
import com.example.gRPCAttempt.enums.Nations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonImageBytesRepository extends JpaRepository<PersonImageBytes, Long> {
    List<PersonImageBytes> findByNationality(Nations nationality);
    Optional<PersonImageBytes> findByIdentificationNumberAndPassportNumberAndNationality(String identificationNumber, String passportNumber, Nations nationality);

    List<PersonImageBytes> findByEmailContaining(String email);
    //Optional<Person> findByPassportNumber(String passportNumber);
    List<PersonImageBytes> findByNameStartsWith(String name);




}