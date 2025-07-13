package com.example.RESTAttempt.controller.protobufVer;

import com.example.RESTAttempt.controller.dto.request.NationalityPOJO;
import com.example.RESTAttempt.controller.dto.request.PersonRequestDTO;
import com.example.RESTAttempt.controller.dto.response.PeopleEmailImageResponseDTO;
import com.example.RESTAttempt.dto.protobufTypes.*;
import com.example.RESTAttempt.entity.PersonImage;
import com.example.RESTAttempt.enums.Nations;
import com.example.RESTAttempt.repository_JPA.PersonImageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/PeopleImageStringProto")
public class PeopleImageStringProtoController {

    private final PersonImageRepository personImageRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    public PeopleImageStringProtoController(PersonImageRepository personImageRepository) {
        this.personImageRepository = personImageRepository;
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

    // protobuf total

    @PostMapping(value = "/PeopleInfo/ProtoPersonImage", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageProtobufDTO proto_POST_PEOPLE_INFO_PERSON_IMAGE(@RequestBody IdentificationPassportNationalityDTO identificationPassportNationalityDTO) {
        //System.out.println(personRequestDTO);
        return personImageRepository.findByIdentificationNumberAndPassportNumberAndNationality(identificationPassportNationalityDTO.getIdentificationNumber(), identificationPassportNationalityDTO.getPassportNumber(), Nations.valueOf(identificationPassportNationalityDTO.getNationality().name())).orElse(new PersonImage()).toPersonImageProtobufDTO();
    }
    @PostMapping(value = "/PeopleInfo/ProtoNationalityImage", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageProtobufListDTO proto_POST_PEOPLE_INFO_Nationality_Image(@RequestBody NationalityDTO nationality) {
        List<PersonImageProtobufDTO> ProtobufDTO_people_list_ImageString = personImageRepository.findByNationality(Nations.valueOf(nationality.getNationality().name())).stream().map(PersonImage::toPersonImageProtobufDTO).toList();
        return PersonImageProtobufListDTO.newBuilder().addAllPersonImageProtobufListDTO(ProtobufDTO_people_list_ImageString).build();
    }
    @PostMapping(value = "PeopleInfo/ProtoPeopleEmailImage", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonImageNameEmailListDTO proto_POST_PEOPLE_INFO_PEOPLE_EMAIL_Image(@RequestBody NationalityDTO nationality) {
        List<PersonImage> people_list = personImageRepository.findByNationality(Nations.valueOf(nationality.getNationality().name()));
        return PersonImageNameEmailListDTO.newBuilder().addAllPersonImageNameEmailList(people_list.stream().map((people) -> {
            return PersonImageNameEmailDTO.newBuilder()
                    .setName(people.getName())
                    .setEmail(people.getEmail())
                    .setBiometrics(people.getBiometrics())
                    .build();
        }).collect(Collectors.toList()))
                .build();
    }

    // JSON + protobuf

    @PostMapping(value = "/PeopleInfo/PersonImageJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageProtobufDTO proto_POST_PEOPLE_INFO_PERSON_IMAGE_JSON(@RequestBody PersonRequestDTO personRequestDTO) {
        return personImageRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequestDTO.getIdentificationNumber(), personRequestDTO.getPassportNumber(), personRequestDTO.getNationality()).orElse(new PersonImage()).toPersonImageProtobufDTO();
    }
    @PostMapping(value = "/PeopleInfo/NationalityImageJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageProtobufListDTO proto_POST_PEOPLE_INFO_Nationality_Image_JSON(@RequestBody NationalityPOJO nationalityJSON) {
        List<PersonImageProtobufDTO> ProtobufDTO_people_list_ImageString = personImageRepository.findByNationality(nationalityJSON.getNationality()).stream().map(PersonImage::toPersonImageProtobufDTO).toList();
        return PersonImageProtobufListDTO.newBuilder().addAllPersonImageProtobufListDTO(ProtobufDTO_people_list_ImageString).build();
    }
    @PostMapping(value = "PeopleInfo/PeopleEmailImageJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonImageNameEmailListDTO proto_POST_PEOPLE_INFO_PEOPLE_EMAIL_Image_JSON(@RequestBody NationalityPOJO nationalityJSON) {
        //System.out.println("EMAIL" + nationality);
        List<PersonImage> people_list = personImageRepository.findByNationality(nationalityJSON.getNationality());
        return PersonImageNameEmailListDTO.newBuilder().addAllPersonImageNameEmailList(people_list.stream().map((people) -> {
                    return PersonImageNameEmailDTO.newBuilder()
                            .setName(people.getName())
                            .setEmail(people.getEmail())
                            .setBiometrics(people.getBiometrics())
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }
}