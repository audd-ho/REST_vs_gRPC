package com.example.Multithreading_Tester.service.REST_related.protobuf_related;

import com.example.Multithreading_Tester.entity.PersonImage;
import com.example.Multithreading_Tester.enums.Nations;
import com.example.Multithreading_Tester.gRPC.peopleimage.IdentificationPassportNationalityDTO;
import com.example.Multithreading_Tester.gRPC.peopleimage.NationalityDTO;
import com.example.Multithreading_Tester.gRPC.peopleimage.PersonImageNameEmailListDTO;
import com.example.Multithreading_Tester.gRPC.peopleimage.PersonImageProtobufDTO;
import com.example.Multithreading_Tester.gRPC.peopleimage.PersonImageProtobufListDTO;
import com.example.Multithreading_Tester.service.dto.request.NationalityPOJO;
import com.example.Multithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Multithreading_Tester.service.dto.response.PeopleEmailImageResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleImageStringProtoService {
    final private RestClient restClient_proto;
    final private RestClient restClient_JSON;

    public PeopleImageStringProtoService(@Qualifier("PeopleImageStringProtoApiClient") RestClient restClient_proto, @Qualifier("JSONPeopleImageStringProtoApiClient") RestClient restClient_JSON) {
        this.restClient_proto = restClient_proto;
        this.restClient_JSON = restClient_JSON;
    }

    public PersonImage protoPersonImage(PersonRequestDTO personRequestDTO) {
        PersonImageProtobufDTO personImageProtobufDTO = restClient_proto.post()
                .uri("/ProtoPersonImage")
                .body(IdentificationPassportNationalityDTO.newBuilder()
                        .setIdentificationNumber(personRequestDTO.getIdentificationNumber())
                        .setPassportNumber(personRequestDTO.getPassportNumber())
                        .setNationality(com.example.Multithreading_Tester.gRPC.people.Nations.valueOf(personRequestDTO.getNationality().name()))
                        .build())
                .retrieve()
                .body(PersonImageProtobufDTO.class);
        return PersonImage.fromPersonImageProtobufDTO(personImageProtobufDTO);
    }

    public List<PersonImage> protoNationalityImage(Nations nationality) {
        PersonImageProtobufListDTO personImageProtobufListDTO = restClient_proto.post()
                .uri("/ProtoNationalityImage")
                .body(NationalityDTO.newBuilder()
                        .setNationality(com.example.Multithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                        .build())
                .retrieve()
                .body(PersonImageProtobufListDTO.class);
        return personImageProtobufListDTO == null?
                new ArrayList<>()
                :
                personImageProtobufListDTO.getPersonImageProtobufListDTOList()
                .stream().map(PersonImage::fromPersonImageProtobufDTO).toList();
    }

    public List<PeopleEmailImageResponseDTO> protoPeopleEmailImage(Nations nationality) {
        PersonImageNameEmailListDTO personImageNameEmailListDTO = restClient_proto.post()
                .uri("/ProtoPeopleEmailImage")
                .body(NationalityDTO.newBuilder()
                        .setNationality(com.example.Multithreading_Tester.gRPC.people.Nations.valueOf(nationality.name()))
                        .build())
                .retrieve()
                .body(PersonImageNameEmailListDTO.class);
        return personImageNameEmailListDTO == null?
                new ArrayList<>()
                :
                personImageNameEmailListDTO.getPersonImageNameEmailListList()
                .stream().map(personImageNameEmailDTO -> new PeopleEmailImageResponseDTO(personImageNameEmailDTO.getName(), personImageNameEmailDTO.getEmail(), personImageNameEmailDTO.getBiometrics())).toList();
    }

    public PersonImage personImageJSON(PersonRequestDTO personRequestDTO) {
        PersonImageProtobufDTO personImageProtobufDTO = restClient_JSON.post()
                .uri("/PersonImageJSON")
                .body(personRequestDTO)
                .retrieve()
                .body(PersonImageProtobufDTO.class);
        return PersonImage.fromPersonImageProtobufDTO(personImageProtobufDTO);
    }

    public List<PersonImage> nationalityImageJSON(Nations nationality) {
        PersonImageProtobufListDTO personImageProtobufListDTO = restClient_JSON.post()
                .uri("/NationalityImageJSON")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                .body(PersonImageProtobufListDTO.class);
        return personImageProtobufListDTO == null?
                new ArrayList<>()
                :
                personImageProtobufListDTO.getPersonImageProtobufListDTOList()
                .stream().map(PersonImage::fromPersonImageProtobufDTO).toList();
    }

    public List<PeopleEmailImageResponseDTO> peopleEmailImageJSON(Nations nationality) {
        PersonImageNameEmailListDTO personImageNameEmailListDTO = restClient_JSON.post()
                .uri("/PeopleEmailImageJSON")
                .body(new NationalityPOJO(nationality))
                .retrieve()
                .body(PersonImageNameEmailListDTO.class);
        return personImageNameEmailListDTO == null?
                new ArrayList<>()
                :
                personImageNameEmailListDTO.getPersonImageNameEmailListList()
                .stream().map(personImageNameEmailDTO -> new PeopleEmailImageResponseDTO(personImageNameEmailDTO.getName(), personImageNameEmailDTO.getEmail(), personImageNameEmailDTO.getBiometrics())).toList();
    }

}
