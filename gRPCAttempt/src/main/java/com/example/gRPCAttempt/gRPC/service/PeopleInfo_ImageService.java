package com.example.gRPCAttempt.gRPC.service;

import com.example.gRPCAttempt.entity.Person;
import com.example.gRPCAttempt.entity.PersonImage;
import com.example.gRPCAttempt.entity.PersonImageBytes;
import com.example.gRPCAttempt.enums.Nations;
import com.example.gRPCAttempt.gRPC.peopleimage.*;
//import com.example.gRPCAttempt.gRPC;
import com.example.gRPCAttempt.repository_JPA.PersonImageBytesRepository;
import com.example.gRPCAttempt.repository_JPA.PersonImageRepository;
import com.example.gRPCAttempt.repository_JPA.PersonRepository;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import org.springframework.grpc.service.GrpcService;

@GrpcService
public class PeopleInfo_ImageService extends PeopleInfoGrpc.PeopleInfoImplBase {

    private final PersonImageRepository personImageRepository;

    public PeopleInfo_ImageService(PersonImageRepository personImageRepository) {
        this.personImageRepository = personImageRepository;
    }

    @Override
    public void nationalityImage(NationalityDTO nationality, StreamObserver<PersonImageProtobufListDTO> responseObserver) {
        //Long time0 = System.currentTimeMillis();

        Nations nat = Nations.valueOf(nationality.getNationality().name());
        List<PersonImage> people_list = personImageRepository.findByNationality(nat);
        //Long time1 = System.currentTimeMillis();

        List<PersonImageProtobufDTO> peopleImageProtobuf_list_DTO =  people_list.stream().map(PersonImage::toPersonImageProtobufDTO).collect(Collectors.toList());
        PersonImageProtobufListDTO nationalityResponse = PersonImageProtobufListDTO.newBuilder().addAllPersonImageProtobufListDTO(peopleImageProtobuf_list_DTO).build();
        //Long time2 = System.currentTimeMillis();

        responseObserver.onNext(nationalityResponse);
        //Long time3 = System.currentTimeMillis();

        //System.out.println(time1-time0);

        //System.out.println(time2-time1);
        //System.out.println(time3-time1);
        responseObserver.onCompleted();
    }

    @Override
    public void personImage(IdentificationPassportNationalityDTO personRequest, StreamObserver<PersonImageProtobufDTO> responseObserver) {
        PersonImage pre_personImageBytesResponse = personImageRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequest.getIdentificationNumber(), personRequest.getPassportNumber(), Nations.valueOf(personRequest.getNationality().name()))
                .orElse(new PersonImage());

        if (pre_personImageBytesResponse.getId() == null) {
            responseObserver.onNext(PersonImageProtobufDTO.newBuilder().build());
            responseObserver.onCompleted();
        }
        PersonImageProtobufDTO personImageResponse = pre_personImageBytesResponse.toPersonImageProtobufDTO();
        responseObserver.onNext(personImageResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void peopleEmailImage(NationalityDTO nationality, StreamObserver<PersonImageNameEmailListDTO> responseObserver) {
        Nations nat = Nations.valueOf(nationality.getNationality().name());

        List<PersonImage> people_list = personImageRepository.findByNationality(nat);

        PersonImageNameEmailListDTO peopleImageEmailResponse = PersonImageNameEmailListDTO.newBuilder().addAllPersonImageNameEmailList(
            people_list.stream().map((people_image) -> {
                return (PersonImageNameEmailDTO.newBuilder()
                        .setName(people_image.getName())
                        .setEmail(people_image.getEmail())
                        .build()
                );
            }).collect(Collectors.toList())
        ).build();

        responseObserver.onNext(peopleImageEmailResponse);
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






    @Override
    public void nationalityImageBytes(NationalityDTO nationality, StreamObserver<PersonImageBytesProtobufListDTO> responseObserver) {

        Nations nat = Nations.valueOf(nationality.getNationality().name());
        List<com.example.gRPCAttempt.entity.PersonImageBytes> people_list = personImageRepository.findByNationality(nat).stream().map(PersonImage::toPersonImageBytes).toList();

        List<PersonImageBytesProtobufDTO> peopleImageBytesProtobuf_list_DTO =  people_list.stream().map(com.example.gRPCAttempt.entity.PersonImageBytes::toPersonImageBytesProtobufDTO).collect(Collectors.toList());
        PersonImageBytesProtobufListDTO nationalityResponse = PersonImageBytesProtobufListDTO.newBuilder().addAllPersonImageBytesProtobufListDTO(peopleImageBytesProtobuf_list_DTO).build();

        responseObserver.onNext(nationalityResponse);

        responseObserver.onCompleted();
    }

    @Override
    public void personImageBytes(IdentificationPassportNationalityDTO personRequest, StreamObserver<PersonImageBytesProtobufDTO> responseObserver) {
        com.example.gRPCAttempt.entity.PersonImage pre_personImageBytesResponse = personImageRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequest.getIdentificationNumber(), personRequest.getPassportNumber(), Nations.valueOf(personRequest.getNationality().name()))
                .orElse(new com.example.gRPCAttempt.entity.PersonImage());

        if (pre_personImageBytesResponse.getId() == null) {
            responseObserver.onNext(PersonImageBytesProtobufDTO.newBuilder().build());
            responseObserver.onCompleted();
        }

        PersonImageBytesProtobufDTO personImageBytesResponse = pre_personImageBytesResponse.toPersonImageBytes()
                .toPersonImageBytesProtobufDTO();
        responseObserver.onNext(personImageBytesResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void peopleEmailImageBytes(NationalityDTO nationality, StreamObserver<PersonImageBytesNameEmailListDTO> responseObserver) {
        Nations nat = Nations.valueOf(nationality.getNationality().name());

        List<PersonImageBytes> people_list = personImageRepository.findByNationality(nat).stream().map(PersonImage::toPersonImageBytes).toList();

        //System.out.println(people_list);

        PersonImageBytesNameEmailListDTO peopleImageBytesEmailResponse = PersonImageBytesNameEmailListDTO.newBuilder().addAllPersonImageBytesNameEmailList(
                people_list.stream().map((people_image_bytes) -> {
                    return (PersonImageBytesNameEmailDTO.newBuilder()
                            .setName(people_image_bytes.getName())
                            .setEmail(people_image_bytes.getEmail())
                            .setBiometrics(ByteString.copyFrom(people_image_bytes.getBiometrics()))
                            .build()
                    );
                }).collect(Collectors.toList())
        ).build();

        responseObserver.onNext(peopleImageBytesEmailResponse);
        responseObserver.onCompleted();
    }
}
