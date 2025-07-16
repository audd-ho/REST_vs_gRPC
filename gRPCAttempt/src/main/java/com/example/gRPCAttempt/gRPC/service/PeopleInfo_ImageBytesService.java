package com.example.gRPCAttempt.gRPC.service;

import com.example.gRPCAttempt.entity.PersonImage;
import com.example.gRPCAttempt.entity.PersonImageBytes;
import com.example.gRPCAttempt.enums.Nations;
import com.example.gRPCAttempt.gRPC.peopleimage.PeopleInfoBGrpc;
import com.example.gRPCAttempt.gRPC.peopleimage.*;
import com.example.gRPCAttempt.repository_JPA.PersonImageBytesRepository;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;
//import org.springframework.grpc.service.GrpcService;

@GrpcService
public class PeopleInfo_ImageBytesService extends PeopleInfoBGrpc.PeopleInfoBImplBase {

    private final PersonImageBytesRepository personImageBytesRepository;

    public PeopleInfo_ImageBytesService(PersonImageBytesRepository personImageBytesRepository) {
        this.personImageBytesRepository = personImageBytesRepository;
    }

    @Override
    public void nationalityImageBytes(NationalityDTO nationality, StreamObserver<PersonImageBytesProtobufListDTO> responseObserver) {

        Nations nat = Nations.valueOf(nationality.getNationality().name());
        List<PersonImageBytes> people_list = personImageBytesRepository.findByNationality(nat);

        List<PersonImageBytesProtobufDTO> peopleImageBytesProtobuf_list_DTO = people_list.stream().map(PersonImageBytes::toPersonImageBytesProtobufDTO).collect(Collectors.toList());
        PersonImageBytesProtobufListDTO nationalityResponse = PersonImageBytesProtobufListDTO.newBuilder().addAllPersonImageBytesProtobufListDTO(peopleImageBytesProtobuf_list_DTO).build();

        responseObserver.onNext(nationalityResponse);

        responseObserver.onCompleted();
    }

    @Override
    public void personImageBytes(IdentificationPassportNationalityDTO personRequest, StreamObserver<PersonImageBytesProtobufDTO> responseObserver) {
        PersonImageBytes pre_personImageBytesResponse = personImageBytesRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequest.getIdentificationNumber(), personRequest.getPassportNumber(), Nations.valueOf(personRequest.getNationality().name()))
                .orElse(new PersonImageBytes());
        if (pre_personImageBytesResponse.getId() == null) {
            responseObserver.onNext(PersonImageBytesProtobufDTO.newBuilder().build());
            responseObserver.onCompleted();
            return;
        }
        PersonImageBytesProtobufDTO personImageBytesResponse = pre_personImageBytesResponse.toPersonImageBytesProtobufDTO();
        responseObserver.onNext(personImageBytesResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void peopleEmailImageBytes(NationalityDTO nationality, StreamObserver<PersonImageBytesNameEmailListDTO> responseObserver) {
        Nations nat = Nations.valueOf(nationality.getNationality().name());

        List<PersonImageBytes> people_list = personImageBytesRepository.findByNationality(nat);

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
