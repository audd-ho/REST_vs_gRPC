package com.example.gRPCAttempt;

import com.example.gRPCAttempt.entity.PersonImage;
import com.example.gRPCAttempt.repository_JPA.PersonImageBytesRepository;
import com.example.gRPCAttempt.repository_JPA.PersonImageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetUp_PersonImageBytesRepo implements CommandLineRunner {
    private final PersonImageRepository personImageRepository;
    private final PersonImageBytesRepository personImageBytesRepository;

    public SetUp_PersonImageBytesRepo(PersonImageRepository personImageRepository, PersonImageBytesRepository personImageBytesRepository) {
        this.personImageRepository = personImageRepository;
        this.personImageBytesRepository = personImageBytesRepository;
    }

    // set up method
    public void Initialise_PersonImageBytesRepository_From_personImageRepository() {
        List<PersonImage> personImageAll_BioString = personImageRepository.findAll();
        personImageBytesRepository.saveAll(personImageAll_BioString.stream().map(PersonImage::toPersonImageBytes_NoID).toList());
    }

    @Override
    public void run(String... args) throws Exception {
        // call set up method!!
        Initialise_PersonImageBytesRepository_From_personImageRepository();
    }
}
