package com.example.Mutlithreading_Tester.service.REST_related.protobuf_related;

import com.example.Mutlithreading_Tester.entity.Person;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.gRPC.people.*;
import com.example.Mutlithreading_Tester.service.dto.request.NationalityPOJO;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Mutlithreading_Tester.service.dto.response.PeopleEmailResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleProtoService {
    final private RestClient restClient_proto;
    final private RestClient restClient_JSON;

    public PeopleProtoService(@Qualifier("PeopleProtoApiClient") RestClient restClient_proto, @Qualifier("JSONPeopleProtoApiClient") RestClient restClient_JSON) {
        this.restClient_proto = restClient_proto;
        this.restClient_JSON = restClient_JSON;
    }

    public Person protoPerson(PersonRequestDTO personRequestDTO) {
        PersonProtobufDTO personProtobufDTO = restClient_proto.post()
                .uri("/ProtoPerson")
                .body(IdentificationPassportNationalityDTO.newBuilder()
                        .setIdentificationNumber(personRequestDTO.getIdentificationNumber())
                        .setPassportNumber(personRequestDTO.getPassportNumber())
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(personRequestDTO.getNationality().name()))
                        .build())
                .retrieve()
                .body(PersonProtobufDTO.class);
        return Person.fromPersonProtobufDTO(personProtobufDTO); // assuming not null
    }

    public Person personJSON(PersonRequestDTO personRequestDTO) {
        PersonProtobufDTO personProtobufDTO = restClient_JSON.post()
                .uri("/PersonJSON")
                .body(personRequestDTO)
                .retrieve()
                .body(PersonProtobufDTO.class);
        return Person.fromPersonProtobufDTO((personProtobufDTO));
    }

    public List<Person> protoNationality(Nations nationality) {
        PersonProtobufListDTO personProtobufListDTO = restClient_proto.post()
                .uri("/ProtoNationality")
                .body(NationalityDTO.newBuilder()
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                        .build())
                .retrieve()
                .body(PersonProtobufListDTO.class);
        return personProtobufListDTO == null?
                new ArrayList<>()
                :
                personProtobufListDTO.getPersonProtobufListDTOList()
                .stream().map(Person::fromPersonProtobufDTO).toList();
    }

    public List<Person> nationalityJSON(Nations nationality) {
        /*
        ResponseEntity<PersonProtobufListDTO> personProtobufListDTOEntity = restClient_JSON.post()
                .uri("/NationalityJSON")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                //.body(byte[].class);
                .toEntity(PersonProtobufListDTO.class);

        System.out.println("1");
        System.out.println(personProtobufListDTOEntity);
        System.out.println(personProtobufListDTOEntity);
        System.out.println(personProtobufListDTOEntity);
        System.out.println(personProtobufListDTOEntity);
        System.out.println(personProtobufListDTOEntity.ge);
        System.out.println("2");
        System.out.println(personProtobufListDTOEntity.getBody());
        System.out.println(personProtobufListDTOEntity.getBody());
        System.out.println(personProtobufListDTOEntity.getBody());
        System.out.println("personProtobufListDTOEntity length: " + (personProtobufListDTOEntity.getBody() != null ? personProtobufListDTOEntity.getBody().getPersonProtobufListDTOList().size() : "null"));
        System.out.println(personProtobufListDTOEntity.getBody());
        System.out.println("3");


        //System.out.println("Response raw bytes length: " + rawBytes.length);
        //System.out.println("Response raw bytes sample (as string): " + new String(rawBytes, StandardCharsets.UTF_8));
        return new ArrayList<>();
        */

        PersonProtobufListDTO personProtobufListDTO = restClient_JSON.post()
                .uri("/NationalityJSON")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                .body(PersonProtobufListDTO.class);
        return personProtobufListDTO == null?
                new ArrayList<>()
                :
                personProtobufListDTO.getPersonProtobufListDTOList()
                .stream().map(Person::fromPersonProtobufDTO).toList();

    }

    public List<PeopleEmailResponseDTO> protoPeopleEmail(Nations nationality) {
        PersonNameEmailListDTO personNameEmailListDTO = restClient_proto.post()
                .uri("/ProtoPeopleEmail")
                .body(NationalityDTO.newBuilder()
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                        .build())
                .retrieve()
                .body(PersonNameEmailListDTO.class);
        return personNameEmailListDTO == null?
                new ArrayList<>()
                :
                personNameEmailListDTO.getPersonNameEmailListList()
                .stream().map(personNameEmailDTO -> new PeopleEmailResponseDTO(personNameEmailDTO.getName(), personNameEmailDTO.getEmail())).toList();
    }

    public List<PeopleEmailResponseDTO> peopleEmailJSON(Nations nationality) {
        PersonNameEmailListDTO personNameEmailListDTO = restClient_JSON.post()
                .uri("/PeopleEmailJSON")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                .body(PersonNameEmailListDTO.class);
        return personNameEmailListDTO == null?
                new ArrayList<>()
                :
                personNameEmailListDTO.getPersonNameEmailListList()
                .stream().map(personNameEmailDTO -> new PeopleEmailResponseDTO(personNameEmailDTO.getName(), personNameEmailDTO.getEmail())).toList();
    }

}
