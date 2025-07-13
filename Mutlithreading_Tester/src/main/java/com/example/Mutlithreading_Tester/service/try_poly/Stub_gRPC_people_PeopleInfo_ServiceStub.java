package com.example.Mutlithreading_Tester.service.try_poly;

import com.example.Mutlithreading_Tester.entity.Person;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.gRPC.people.*;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Mutlithreading_Tester.service.dto.response.PeopleEmailResponseDTO;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Stub_gRPC_people_PeopleInfo_ServiceStub implements GeneralStub {
    private final PeopleInfoGrpc.PeopleInfoBlockingStub peopleInfoStub;

    public Stub_gRPC_people_PeopleInfo_ServiceStub(@Qualifier("gRPCAttemptChannel") ManagedChannel gRPCAttemptChannel) {
        this.peopleInfoStub = PeopleInfoGrpc.newBlockingStub(gRPCAttemptChannel);
    }

    @Override
    public List<Person> nationality(Nations nationality) {

        NationalityDTO nationalityDTO = NationalityDTO.newBuilder()
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                .build();

        PersonProtobufListDTO personProtobufListDTO = peopleInfoStub.nationality(nationalityDTO);

        return personProtobufListDTO.getPersonProtobufListDTOList().stream().map(Person::fromPersonProtobufDTO).toList();

    }

    @Override
    public Person person(PersonRequestDTO personRequestDTO) {

        IdentificationPassportNationalityDTO identificationPassportNationalityDTO = IdentificationPassportNationalityDTO.newBuilder()
                .setIdentificationNumber(personRequestDTO.getIdentificationNumber())
                .setPassportNumber(personRequestDTO.getPassportNumber())
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(personRequestDTO.getNationality().name()))
                .build();

        PersonProtobufDTO personProtobufDTO = peopleInfoStub.person(identificationPassportNationalityDTO);

        return Person.fromPersonProtobufDTO(personProtobufDTO);

    }

    @Override
    public List<PeopleEmailResponseDTO> peopleEmail(Nations nationality) {

        NationalityDTO nationalityDTO = NationalityDTO.newBuilder()
                .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                .build();

        PersonNameEmailListDTO personNameEmailListDTO = peopleInfoStub.peopleEmail(nationalityDTO);

        return personNameEmailListDTO.getPersonNameEmailListList().stream().map(personNameEmailDTO -> new PeopleEmailResponseDTO(personNameEmailDTO.getName(), personNameEmailDTO.getEmail())).toList();

    }

}
