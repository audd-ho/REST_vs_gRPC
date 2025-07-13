package com.example.Mutlithreading_Tester.service.gRPC_related;

import com.example.Mutlithreading_Tester.entity.PersonImage;
import com.example.Mutlithreading_Tester.entity.PersonImageBytes;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.gRPC.peopleimage.*;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Mutlithreading_Tester.service.dto.response.PeopleEmailImageBytesResponseDTO_gRPC;
import com.example.Mutlithreading_Tester.service.dto.response.PeopleEmailImageResponseDTO;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Stub_gRPC_peopleimage_PeopleInfo_ServiceStub {
    private final PeopleInfoGrpc.PeopleInfoBlockingStub peopleInfoStub;

    public Stub_gRPC_peopleimage_PeopleInfo_ServiceStub(@Qualifier("gRPCAttemptChannel") ManagedChannel gRPCAttemptChannel) {
        this.peopleInfoStub = PeopleInfoGrpc.newBlockingStub(gRPCAttemptChannel);
    }

    // ImageString

    public List<PersonImage> nationalityImage(Nations nationality) {

        NationalityDTO nationalityDTO = NationalityDTO.newBuilder()
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                .build();

        PersonImageProtobufListDTO personImageProtobufListDTO = peopleInfoStub.nationalityImage(nationalityDTO);

        return personImageProtobufListDTO.getPersonImageProtobufListDTOList().stream().map(PersonImage::fromPersonImageProtobufDTO).toList();

    }

    public PersonImage personImage(PersonRequestDTO personRequestDTO) {

        IdentificationPassportNationalityDTO identificationPassportNationalityDTO = IdentificationPassportNationalityDTO.newBuilder()
                .setIdentificationNumber(personRequestDTO.getIdentificationNumber())
                .setPassportNumber(personRequestDTO.getPassportNumber())
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(personRequestDTO.getNationality().name()))
                .build();

        PersonImageProtobufDTO personImageProtobufDTO = peopleInfoStub.personImage(identificationPassportNationalityDTO);

        return PersonImage.fromPersonImageProtobufDTO(personImageProtobufDTO);

    }

    public List<PeopleEmailImageResponseDTO> peopleEmailImage(Nations nationality) {

        NationalityDTO nationalityDTO = NationalityDTO.newBuilder()
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                .build();

        PersonImageNameEmailListDTO personImageNameEmailListDTO = peopleInfoStub.peopleEmailImage(nationalityDTO);

        return personImageNameEmailListDTO.getPersonImageNameEmailListList().stream().map(personImageNameEmailDTO -> new PeopleEmailImageResponseDTO(personImageNameEmailDTO.getName(), personImageNameEmailDTO.getEmail(), personImageNameEmailDTO.getBiometrics())).toList();

    }

    // ImageBytes

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
