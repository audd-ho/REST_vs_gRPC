package com.example.gRPCAttempt.gRPC.service;

import com.example.gRPCAttempt.entity.Person;
import com.example.gRPCAttempt.enums.Nations;
import com.example.gRPCAttempt.gRPC.people.PeopleInfoGrpc;
import com.example.gRPCAttempt.gRPC.people.*;
import com.example.gRPCAttempt.repository_JPA.PersonRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import org.springframework.grpc.service.GrpcService;

@GrpcService
public class PeopleInfoService extends PeopleInfoGrpc.PeopleInfoImplBase {

    private final PersonRepository personRepository;

    public PeopleInfoService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void nationality(NationalityDTO nationality, StreamObserver<PersonProtobufListDTO> responseObserver) {
        Long time0 = System.currentTimeMillis();

        Nations nat = Nations.valueOf(nationality.getNationality().name());
        Optional<List<Person>> people_list = personRepository.findByNationality(nat);
        Long time1 = System.currentTimeMillis();

        List<PersonProtobufDTO> peopleProtobuf_list_DTO =  people_list.orElse((new ArrayList<>())).stream().map(Person::toPersonProtobufDTO).collect(Collectors.toList());
        PersonProtobufListDTO nationalityResponse = PersonProtobufListDTO.newBuilder().addAllPersonProtobufListDTO(peopleProtobuf_list_DTO).build();
        Long time2 = System.currentTimeMillis();

        responseObserver.onNext(nationalityResponse);
        Long time3 = System.currentTimeMillis();

        System.out.println(time1-time0);

        System.out.println(time2-time1);
        System.out.println(time3-time1);
        responseObserver.onCompleted();
    }

    @Override
    public void person(IdentificationPassportNationalityDTO personRequest, StreamObserver<PersonProtobufDTO> responseObserver) {
        PersonProtobufDTO personResponse = personRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequest.getIdentificationNumber(), personRequest.getPassportNumber(), Nations.valueOf(personRequest.getNationality().name()))
                .orElse(new Person())
                .toPersonProtobufDTO();
        responseObserver.onNext(personResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void peopleEmail(NationalityDTO nationality, StreamObserver<PersonNameEmailListDTO> responseObserver) {
        Nations nat = Nations.valueOf(nationality.getNationality().name());

        Optional<List<Person>> people_list = personRepository.findByNationality(nat);

        PersonNameEmailListDTO peopleEmailResponse = PersonNameEmailListDTO.newBuilder().addAllPersonNameEmailList(
            people_list.orElse(new ArrayList<>()).stream().map((people) -> {
                return (PersonNameEmailDTO.newBuilder()
                        .setName(people.getName())
                        .setEmail(people.getEmail())
                        .build()
                );
            }).collect(Collectors.toList())
        ).build();

        responseObserver.onNext(peopleEmailResponse);
        responseObserver.onCompleted();
        /*
        String message = "Hello, " + name + "!";

        HelloReply reply = HelloReply.newBuilder()
                .setMessage(message)
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        */
    }
}
