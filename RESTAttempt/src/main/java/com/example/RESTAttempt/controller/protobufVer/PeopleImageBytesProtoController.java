package com.example.RESTAttempt.controller.protobufVer;

import com.example.RESTAttempt.controller.dto.request.NationalityPOJO;
import com.example.RESTAttempt.controller.dto.request.PersonRequestDTO;
import com.example.RESTAttempt.controller.dto.response.PeopleEmailImageResponseDTO;
import com.example.RESTAttempt.dto.protobufTypes.*;
import com.example.RESTAttempt.entity.PersonImage;
import com.example.RESTAttempt.entity.PersonImageBytes;
import com.example.RESTAttempt.enums.Nations;
import com.example.RESTAttempt.repository_JPA.PersonImageBytesRepository;
import com.example.RESTAttempt.repository_JPA.PersonImageRepository;
import com.google.protobuf.ByteString;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/PeopleImageBytesProto")
public class PeopleImageBytesProtoController {

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    private final PersonImageRepository personImageRepository;
    private final PersonImageBytesRepository personImageBytesRepository;

    public PeopleImageBytesProtoController(PersonImageRepository personImageRepository, PersonImageBytesRepository personImageBytesRepository) {
        this.personImageRepository = personImageRepository;
        this.personImageBytesRepository = personImageBytesRepository;
    }
    /*
    @PostMapping("/PeopleInfo/Nationality")
    public Optional<List<Person>> POST_PEOPLE_INFO_Nationality(@RequestBody String nationality) {
        System.out.println(nationality);
        return personRepository.findByNationality(Nations.valueOf(nationality));
    }

    @PostMapping("/PeopleInfo/Person")
    public Optional<Person> POST_PEOPLE_INFO_PERSON(@RequestBody PersonRequestDTO personRequestDTO) {
        //System.out.println(personRequestDTO);
        return personRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequestDTO.getIdentificationNumber(), personRequestDTO.getPassportNumber(), personRequestDTO.getNationality());
    }

    @PostMapping("PeopleInfo/PeopleEmail")
    public List<PeopleEmailResponseDTO> POST_PEOPLE_INFO_PEOPLE_EMAIL(@RequestBody Nations nationality) {
        //System.out.println("EMAIL" + nationality);
        Optional<List<Person>> people_list = personRepository.findByNationality(nationality);
        return people_list.orElse(new ArrayList<>()).stream().map((people) -> {
            return new PeopleEmailResponseDTO(people.getName(), people.getEmail());
        }).collect(Collectors.toList());
    }



    @PostMapping("/PeopleInfo/NationalityJSON")
    public Optional<List<Person>> POST_PEOPLE_INFO_Nationality_JSON(@RequestBody NationalityPOJO nationalityJSON) {
        //System.out.println(nationality);
        return personRepository.findByNationality(nationalityJSON.getNationality());
    }
    @PostMapping("PeopleInfo/PeopleEmailJSON")
    public List<PeopleEmailResponseDTO> POST_PEOPLE_INFO_PEOPLE_EMAIL_JSON(@RequestBody NationalityPOJO nationalityJSON) {
        //System.out.println("EMAIL" + nationality);
        Optional<List<Person>> people_list = personRepository.findByNationality(nationalityJSON.getNationality());
        return people_list.orElse(new ArrayList<>()).stream().map((people) -> {
            return new PeopleEmailResponseDTO(people.getName(), people.getEmail());
        }).collect(Collectors.toList());
    }
    */


    /// from PersonImageBytesRepository


    // protobuf total

    @PostMapping(value = "/PeopleInfo/ProtoPersonImage", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageBytesProtobufDTO proto_POST_PEOPLE_INFO_PERSON_IMAGE_PersonImageBytesRepository(@RequestBody IdentificationPassportNationalityDTO identificationPassportNationalityDTO) {
        //System.out.println(personRequestDTO);
        return personImageBytesRepository.findByIdentificationNumberAndPassportNumberAndNationality(identificationPassportNationalityDTO.getIdentificationNumber(), identificationPassportNationalityDTO.getPassportNumber(), Nations.valueOf(identificationPassportNationalityDTO.getNationality().name())).orElse(new PersonImageBytes()).toPersonImageBytesProtobufDTO();
    }
    @PostMapping(value = "/PeopleInfo/ProtoNationalityImage", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageBytesProtobufListDTO proto_POST_PEOPLE_INFO_Nationality_Image_PersonImageBytesRepository(@RequestBody NationalityDTO nationality) {
        List<PersonImageBytesProtobufDTO> ProtobufDTO_people_list_ImageString = personImageBytesRepository.findByNationality(Nations.valueOf(nationality.getNationality().name())).stream().map(PersonImageBytes::toPersonImageBytesProtobufDTO).toList();
        return PersonImageBytesProtobufListDTO.newBuilder().addAllPersonImageBytesProtobufListDTO(ProtobufDTO_people_list_ImageString).build();
    }
    @PostMapping(value = "PeopleInfo/ProtoPeopleEmailImage", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageBytesNameEmailListDTO proto_POST_PEOPLE_INFO_PEOPLE_EMAIL_Image_PersonImageBytesRepository(@RequestBody NationalityDTO nationality) {
        List<PersonImageBytes> people_list = personImageBytesRepository.findByNationality(Nations.valueOf(nationality.getNationality().name()));
        return PersonImageBytesNameEmailListDTO.newBuilder().addAllPersonImageBytesNameEmailList(people_list.stream().map((people) -> {
                    return PersonImageBytesNameEmailDTO.newBuilder()
                            .setName(people.getName())
                            .setEmail(people.getEmail())
                            .setBiometrics(ByteString.copyFrom(people.getBiometrics()))
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }

    // JSON + protobuf

    @PostMapping(value = "/PeopleInfo/PersonImageJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageBytesProtobufDTO proto_POST_PEOPLE_INFO_PERSON_IMAGE_JSON_PersonImageBytesRepository(@RequestBody PersonRequestDTO personRequestDTO) {
        return personImageBytesRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequestDTO.getIdentificationNumber(), personRequestDTO.getPassportNumber(), personRequestDTO.getNationality()).orElse(new PersonImageBytes()).toPersonImageBytesProtobufDTO();
    }
    @PostMapping(value = "/PeopleInfo/NationalityImageJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageBytesProtobufListDTO proto_POST_PEOPLE_INFO_Nationality_Image_JSON_PersonImageBytesRepository(@RequestBody NationalityPOJO nationalityJSON) {
        List<PersonImageBytesProtobufDTO> ProtobufDTO_people_list_ImageString = personImageBytesRepository.findByNationality(nationalityJSON.getNationality()).stream().map(PersonImageBytes::toPersonImageBytesProtobufDTO).toList();
        return PersonImageBytesProtobufListDTO.newBuilder().addAllPersonImageBytesProtobufListDTO(ProtobufDTO_people_list_ImageString).build();
    }
    @PostMapping(value = "PeopleInfo/PeopleEmailImageJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageBytesNameEmailListDTO proto_POST_PEOPLE_INFO_PEOPLE_EMAIL_Image_JSON_PersonImageBytesRepository(@RequestBody NationalityPOJO nationalityJSON) {
        List<PersonImageBytes> people_list = personImageBytesRepository.findByNationality(nationalityJSON.getNationality());
        return PersonImageBytesNameEmailListDTO.newBuilder().addAllPersonImageBytesNameEmailList(people_list.stream().map((people) -> {
                    return PersonImageBytesNameEmailDTO.newBuilder()
                            .setName(people.getName())
                            .setEmail(people.getEmail())
                            .setBiometrics(ByteString.copyFrom(people.getBiometrics()))
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }


    /// from PersonImageRepository

    // protobuf total

    @PostMapping(value = "/PeopleInfo/ProtoPersonImage_PIR", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageBytesProtobufDTO proto_POST_PEOPLE_INFO_PERSON_IMAGE_PersonImageRepository(@RequestBody IdentificationPassportNationalityDTO identificationPassportNationalityDTO) {
        return personImageRepository.findByIdentificationNumberAndPassportNumberAndNationality(identificationPassportNationalityDTO.getIdentificationNumber(), identificationPassportNationalityDTO.getPassportNumber(), Nations.valueOf(identificationPassportNationalityDTO.getNationality().name())).orElse(new PersonImage()).toPersonImageBytes().toPersonImageBytesProtobufDTO();
    }
    @PostMapping(value = "/PeopleInfo/ProtoNationalityImage_PIR", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageBytesProtobufListDTO proto_POST_PEOPLE_INFO_Nationality_Image_PersonImageRepository(@RequestBody NationalityDTO nationality) {
        List<PersonImageBytesProtobufDTO> ProtobufDTO_people_list_ImageString = personImageRepository.findByNationality(Nations.valueOf(nationality.getNationality().name())).stream().map(PersonImage::toPersonImageBytes).map(PersonImageBytes::toPersonImageBytesProtobufDTO).toList();
        return PersonImageBytesProtobufListDTO.newBuilder().addAllPersonImageBytesProtobufListDTO(ProtobufDTO_people_list_ImageString).build();
    }
    @PostMapping(value = "PeopleInfo/ProtoPeopleEmailImage_PIR", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageBytesNameEmailListDTO proto_POST_PEOPLE_INFO_PEOPLE_EMAIL_Image_PersonImageRepository(@RequestBody NationalityDTO nationality) {
        List<PersonImage> people_list = personImageRepository.findByNationality(Nations.valueOf(nationality.getNationality().name()));
        return PersonImageBytesNameEmailListDTO.newBuilder().addAllPersonImageBytesNameEmailList(people_list.stream().map((people) -> {
                    return PersonImageBytesNameEmailDTO.newBuilder()
                            .setName(people.getName())
                            .setEmail(people.getEmail())
                            .setBiometrics(ByteString.copyFrom(Base64.getDecoder().decode(people.getBiometrics())))
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }

    // JSON + protobuf

    @PostMapping(value = "/PeopleInfo/PersonImageJSON_PIR", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageBytesProtobufDTO proto_POST_PEOPLE_INFO_PERSON_IMAGE_JSON_PersonImageRepository(@RequestBody PersonRequestDTO personRequestDTO) {
        return personImageRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequestDTO.getIdentificationNumber(), personRequestDTO.getPassportNumber(), personRequestDTO.getNationality()).orElse(new PersonImage()).toPersonImageBytes().toPersonImageBytesProtobufDTO();
    }
    @PostMapping(value = "/PeopleInfo/NationalityImageJSON_PIR", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageBytesProtobufListDTO proto_POST_PEOPLE_INFO_Nationality_Image_JSON_PersonImageRepository(@RequestBody NationalityPOJO nationalityJSON) {
        List<PersonImageBytesProtobufDTO> ProtobufDTO_people_list_ImageString = personImageRepository.findByNationality(nationalityJSON.getNationality()).stream().map(PersonImage::toPersonImageBytes).map(PersonImageBytes::toPersonImageBytesProtobufDTO).toList();
        return PersonImageBytesProtobufListDTO.newBuilder().addAllPersonImageBytesProtobufListDTO(ProtobufDTO_people_list_ImageString).build();
    }
    @PostMapping(value = "PeopleInfo/PeopleEmailImageJSON_PIR", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageBytesNameEmailListDTO proto_POST_PEOPLE_INFO_PEOPLE_EMAIL_Image_JSON_PersonImageRepository(@RequestBody NationalityPOJO nationalityJSON) {
        List<PersonImage> people_list = personImageRepository.findByNationality(nationalityJSON.getNationality());
        return PersonImageBytesNameEmailListDTO.newBuilder().addAllPersonImageBytesNameEmailList(people_list.stream().map((people) -> {
                    return PersonImageBytesNameEmailDTO.newBuilder()
                            .setName(people.getName())
                            .setEmail(people.getEmail())
                            .setBiometrics(ByteString.copyFrom(Base64.getDecoder().decode(people.getBiometrics())))
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }
}