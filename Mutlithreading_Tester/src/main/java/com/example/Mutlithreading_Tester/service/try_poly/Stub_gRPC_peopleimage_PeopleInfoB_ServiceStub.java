package com.example.Mutlithreading_Tester.service.try_poly;

import com.example.Mutlithreading_Tester.entity.PersonImageBytes;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.gRPC.peopleimage.*;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Mutlithreading_Tester.service.dto.response.PeopleEmailImageBytesResponseDTO_gRPC;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Stub_gRPC_peopleimage_PeopleInfoB_ServiceStub {
    private final PeopleInfoBGrpc.PeopleInfoBBlockingStub peopleInfoStub;

    public Stub_gRPC_peopleimage_PeopleInfoB_ServiceStub(@Qualifier("gRPCAttemptChannel") ManagedChannel gRPCAttemptChannel) {
        this.peopleInfoStub = PeopleInfoBGrpc.newBlockingStub(gRPCAttemptChannel);
    }

    public List<PersonImageBytes> nationalityImageBytes(Nations nationality) {

        NationalityDTO nationalityDTO = NationalityDTO.newBuilder()
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                .build();

        PersonImageBytesProtobufListDTO personImageBytesProtobufListDTO = peopleInfoStub.nationalityImageBytes(nationalityDTO);

        return personImageBytesProtobufListDTO.getPersonImageBytesProtobufListDTOList().stream().map(PersonImageBytes::fromPersonImageBytesProtobufDTO).toList();

    }

    public PersonImageBytes personImageBytes(PersonRequestDTO personRequestDTO) {

        IdentificationPassportNationalityDTO identificationPassportNationalityDTO = IdentificationPassportNationalityDTO.newBuilder()
                .setIdentificationNumber(personRequestDTO.getIdentificationNumber())
                .setPassportNumber(personRequestDTO.getPassportNumber())
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(personRequestDTO.getNationality().name()))
                .build();

        PersonImageBytesProtobufDTO personImageBytesProtobufDTO = peopleInfoStub.personImageBytes(identificationPassportNationalityDTO);

        return PersonImageBytes.fromPersonImageBytesProtobufDTO(personImageBytesProtobufDTO);

    }

    public List<PeopleEmailImageBytesResponseDTO_gRPC> peopleEmailImageBytes(Nations nationality) {

        NationalityDTO nationalityDTO = NationalityDTO.newBuilder()
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                .build();

        PersonImageBytesNameEmailListDTO personImageBytesNameEmailListDTO = peopleInfoStub.peopleEmailImageBytes(nationalityDTO);

        return personImageBytesNameEmailListDTO.getPersonImageBytesNameEmailListList().stream().map(personImageBytesNameEmailDTO -> new PeopleEmailImageBytesResponseDTO_gRPC(personImageBytesNameEmailDTO.getName(), personImageBytesNameEmailDTO.getEmail(), personImageBytesNameEmailDTO.getBiometrics().toByteArray())).toList();

    }

}
