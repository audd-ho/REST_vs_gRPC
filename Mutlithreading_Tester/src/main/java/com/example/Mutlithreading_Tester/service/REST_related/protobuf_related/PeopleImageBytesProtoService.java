package com.example.Mutlithreading_Tester.service.REST_related.protobuf_related;

import com.example.Mutlithreading_Tester.entity.PersonImageBytes;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.gRPC.peopleimage.*;
import com.example.Mutlithreading_Tester.service.dto.request.NationalityPOJO;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Mutlithreading_Tester.service.dto.response.PeopleEmailImageBytesResponseDTO_gRPC;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleImageBytesProtoService {
    final private RestClient restClient_proto;
    final private RestClient restClient_JSON;

    public PeopleImageBytesProtoService(@Qualifier("PeopleImageBytesProtoApiClient") RestClient restClient_proto, @Qualifier("JSONPeopleImageBytesProtoApiClient") RestClient restClient_JSON) {
        this.restClient_proto = restClient_proto;
        this.restClient_JSON = restClient_JSON;
    }


// From PIB repo


    public PersonImageBytes protoPersonImage(PersonRequestDTO personRequestDTO) {
        PersonImageBytesProtobufDTO personImageBytesProtobufDTO = restClient_proto.post()
                .uri("/ProtoPersonImage")
                .body(IdentificationPassportNationalityDTO.newBuilder()
                        .setIdentificationNumber(personRequestDTO.getIdentificationNumber())
                        .setPassportNumber(personRequestDTO.getPassportNumber())
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(personRequestDTO.getNationality().name()))
                        .build())
                .retrieve()
                .body(PersonImageBytesProtobufDTO.class);
        return PersonImageBytes.fromPersonImageBytesProtobufDTO(personImageBytesProtobufDTO);
    }

    public List<PersonImageBytes> protoNationalityImage(Nations nationality) {
        PersonImageBytesProtobufListDTO personImageBytesProtobufListDTO = restClient_proto.post()
                .uri("/ProtoNationalityImage")
                .body(NationalityDTO.newBuilder()
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                        .build())
                .retrieve()
                .body(PersonImageBytesProtobufListDTO.class);
        return personImageBytesProtobufListDTO == null?
                new ArrayList<>()
                :
                personImageBytesProtobufListDTO.getPersonImageBytesProtobufListDTOList()
                .stream().map(PersonImageBytes::fromPersonImageBytesProtobufDTO).toList();
    }
    // convert bytes to string maybe another function or later on?? or keep it to only return bytes here from this service?
    public List<PeopleEmailImageBytesResponseDTO_gRPC> protoPeopleEmailImage(Nations nationality) {
        PersonImageBytesNameEmailListDTO personImageBytesNameEmailListDTO = restClient_proto.post()
                .uri("/ProtoPeopleEmailImage")
                .body(NationalityDTO.newBuilder()
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                        .build())
                .retrieve()
                .body(PersonImageBytesNameEmailListDTO.class);
        return personImageBytesNameEmailListDTO == null?
                new ArrayList<>()
                :
                personImageBytesNameEmailListDTO.getPersonImageBytesNameEmailListList()
                .stream().map(personImageBytesNameEmailDTO -> new PeopleEmailImageBytesResponseDTO_gRPC(personImageBytesNameEmailDTO.getName(), personImageBytesNameEmailDTO.getEmail(), personImageBytesNameEmailDTO.getBiometrics().toByteArray())).toList();
    }

    public PersonImageBytes personImageJSON(PersonRequestDTO personRequestDTO) {
        PersonImageBytesProtobufDTO personImageBytesProtobufDTO = restClient_JSON.post()
                .uri("/PersonImageJSON")
                .body(personRequestDTO)
                .retrieve()
                .body(PersonImageBytesProtobufDTO.class);
        return PersonImageBytes.fromPersonImageBytesProtobufDTO(personImageBytesProtobufDTO);
    }

    public List<PersonImageBytes> nationalityImageJSON(Nations nationality) {
        PersonImageBytesProtobufListDTO personImageBytesProtobufListDTO = restClient_JSON.post()
                .uri("/NationalityImageJSON")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                .body(PersonImageBytesProtobufListDTO.class);
        return personImageBytesProtobufListDTO == null?
                new ArrayList<>()
                :
                personImageBytesProtobufListDTO.getPersonImageBytesProtobufListDTOList()
                .stream().map(PersonImageBytes::fromPersonImageBytesProtobufDTO).toList();
    }
    // convert bytes to string maybe another function or later on?? or keep it to only return bytes here from this service?
    public List<PeopleEmailImageBytesResponseDTO_gRPC> peopleEmailImageJSON(Nations nationality) {
        PersonImageBytesNameEmailListDTO personImageBytesNameEmailListDTO = restClient_JSON.post()
                .uri("/PeopleEmailImageJSON")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                .body(PersonImageBytesNameEmailListDTO.class);
        return personImageBytesNameEmailListDTO == null?
                new ArrayList<>()
                :
                personImageBytesNameEmailListDTO.getPersonImageBytesNameEmailListList()
                .stream().map(personImageBytesNameEmailDTO -> new PeopleEmailImageBytesResponseDTO_gRPC(personImageBytesNameEmailDTO.getName(), personImageBytesNameEmailDTO.getEmail(), personImageBytesNameEmailDTO.getBiometrics().toByteArray())).toList();
    }

// From PI repo


    public PersonImageBytes protoPersonImage_PIR(PersonRequestDTO personRequestDTO) {
        PersonImageBytesProtobufDTO personImageBytesProtobufDTO = restClient_proto.post()
                .uri("/ProtoPersonImage_PIR")
                .body(IdentificationPassportNationalityDTO.newBuilder()
                        .setIdentificationNumber(personRequestDTO.getIdentificationNumber())
                        .setPassportNumber(personRequestDTO.getPassportNumber())
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(personRequestDTO.getNationality().name()))
                        .build())
                .retrieve()
                .body(PersonImageBytesProtobufDTO.class);
        return PersonImageBytes.fromPersonImageBytesProtobufDTO(personImageBytesProtobufDTO);
    }

    public List<PersonImageBytes> protoNationalityImage_PIR(Nations nationality) {
        PersonImageBytesProtobufListDTO personImageBytesProtobufListDTO = restClient_proto.post()
                .uri("/ProtoNationalityImage_PIR")
                .body(NationalityDTO.newBuilder()
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                        .build())
                .retrieve()
                .body(PersonImageBytesProtobufListDTO.class);
        return personImageBytesProtobufListDTO == null?
                new ArrayList<>()
                :
                personImageBytesProtobufListDTO.getPersonImageBytesProtobufListDTOList()
                .stream().map(PersonImageBytes::fromPersonImageBytesProtobufDTO).toList();
    }
    // convert bytes to string maybe another function or later on?? or keep it to only return bytes here from this service?
    public List<PeopleEmailImageBytesResponseDTO_gRPC> protoPeopleEmailImage_PIR(Nations nationality) {
        PersonImageBytesNameEmailListDTO personImageBytesNameEmailListDTO = restClient_proto.post()
                .uri("/ProtoPeopleEmailImage_PIR")
                .body(NationalityDTO.newBuilder()
                        .setNationality(com.example.Mutlithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                        .build())
                .retrieve()
                .body(PersonImageBytesNameEmailListDTO.class);
        return personImageBytesNameEmailListDTO == null?
                new ArrayList<>()
                :
                personImageBytesNameEmailListDTO.getPersonImageBytesNameEmailListList()
                .stream().map(personImageBytesNameEmailDTO -> new PeopleEmailImageBytesResponseDTO_gRPC(personImageBytesNameEmailDTO.getName(), personImageBytesNameEmailDTO.getEmail(), personImageBytesNameEmailDTO.getBiometrics().toByteArray())).toList();
    }

    public PersonImageBytes personImageJSON_PIR(PersonRequestDTO personRequestDTO) {
        PersonImageBytesProtobufDTO personImageBytesProtobufDTO = restClient_JSON.post()
                .uri("/PersonImageJSON_PIR")
                .body(personRequestDTO)
                .retrieve()
                .body(PersonImageBytesProtobufDTO.class);
        return PersonImageBytes.fromPersonImageBytesProtobufDTO(personImageBytesProtobufDTO);
    }

    public List<PersonImageBytes> nationalityImageJSON_PIR(Nations nationality) {
        PersonImageBytesProtobufListDTO personImageBytesProtobufListDTO = restClient_JSON.post()
                .uri("/NationalityImageJSON_PIR")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                .body(PersonImageBytesProtobufListDTO.class);
        return personImageBytesProtobufListDTO == null?
                new ArrayList<>()
                :
                personImageBytesProtobufListDTO.getPersonImageBytesProtobufListDTOList()
                .stream().map(PersonImageBytes::fromPersonImageBytesProtobufDTO).toList();
    }
    // convert bytes to string maybe another function or later on?? or keep it to only return bytes here from this service?
    public List<PeopleEmailImageBytesResponseDTO_gRPC> peopleEmailImageJSON_PIR(Nations nationality) {
        PersonImageBytesNameEmailListDTO personImageBytesNameEmailListDTO = restClient_JSON.post()
                .uri("/PeopleEmailImageJSON_PIR")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                .body(PersonImageBytesNameEmailListDTO.class);
        return personImageBytesNameEmailListDTO == null?
                new ArrayList<>()
                :
                personImageBytesNameEmailListDTO.getPersonImageBytesNameEmailListList()
                .stream().map(personImageBytesNameEmailDTO -> new PeopleEmailImageBytesResponseDTO_gRPC(personImageBytesNameEmailDTO.getName(), personImageBytesNameEmailDTO.getEmail(), personImageBytesNameEmailDTO.getBiometrics().toByteArray())).toList();
    }

}
