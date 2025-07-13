package com.example.RESTAttempt.controller.protobufVer;

import com.example.RESTAttempt.controller.dto.request.NationalityPOJO;
import com.example.RESTAttempt.controller.dto.request.PersonRequestDTO;
import com.example.RESTAttempt.controller.dto.response.PeopleEmailResponseDTO;
import com.example.RESTAttempt.entity.Person;
import com.example.RESTAttempt.enums.MaritalStatus;
import com.example.RESTAttempt.enums.Nations;
import com.example.RESTAttempt.repository_JPA.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.RESTAttempt.dto.protobufTypes.*;


@RestController
@RequestMapping("/PeopleProto")
public class PeopleProtoController {

    @GetMapping(value = "/", produces = "application/x-protobuf")
    public com.example.RESTAttempt.dto.protobufTypes.PersonProtobufDTO GEThello() {
        Person p1 = personRepository.findByNationality(Nations.SG).orElse(new ArrayList<Person>()).get(0);

        return com.example.RESTAttempt.dto.protobufTypes.PersonProtobufDTO.newBuilder()
                .setId(p1.getId())
                .setEmail(p1.getEmail())
                .setNationality(com.example.RESTAttempt.dto.protobufTypes.Nations.valueOf(p1.getNationality().name()))
                .setMaritalStatus(com.example.RESTAttempt.dto.protobufTypes.MaritalStatus.valueOf(p1.getMaritalStatus().name()))
                .setName(p1.getName())
                .build();

    }

    @PostMapping(value = "/", produces = "application/x-protobuf")
    public com.example.RESTAttempt.dto.protobufTypes.PersonProtobufDTO POSThello() {
        Person p1 = personRepository.findByNationality(Nations.SG).orElse(new ArrayList<Person>()).get(0);

        return com.example.RESTAttempt.dto.protobufTypes.PersonProtobufDTO.newBuilder()
                .setId(p1.getId())
                .setEmail(p1.getEmail())
                .setNationality(com.example.RESTAttempt.dto.protobufTypes.Nations.valueOf(p1.getNationality().name()))
                .setMaritalStatus(com.example.RESTAttempt.dto.protobufTypes.MaritalStatus.valueOf(p1.getMaritalStatus().name()))
                .setName(p1.getName())
                .build();

    }

    @PostMapping(value = "/proto", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public com.example.RESTAttempt.dto.protobufTypes.PersonProtobufDTO protoPOSThello(@RequestBody NationalityDTO natDTO) {

        Person p1 = personRepository.findByNationality(Nations.valueOf(natDTO.getNationality().name())).orElse(new ArrayList<Person>()).get(0);

        return com.example.RESTAttempt.dto.protobufTypes.PersonProtobufDTO.newBuilder()
                .setId(p1.getId())
                .setEmail(p1.getEmail())
                .setNationality(com.example.RESTAttempt.dto.protobufTypes.Nations.valueOf(p1.getNationality().name()))
                .setMaritalStatus(com.example.RESTAttempt.dto.protobufTypes.MaritalStatus.valueOf(p1.getMaritalStatus().name()))
                .setName(p1.getName())
                .build();

    }

    private final PersonRepository personRepository;

    public PeopleProtoController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/PeopleInfo")
    public Person PEOPLE_INFO() {
        Person defPerson = new Person("S9012345P", "P1234567N", "DefaultEmail@gmail.com", "DefaultName", 34, Nations.SG, Nations.MY, MaritalStatus.Married, 6);
        return defPerson;
    }

    @GetMapping("/PeopleSInfo")
    public List<Person> PEOPLEs_INFO() {
        Person defLPerson1 = new Person("S1234567G", "K1234567K", "alice@xy.com", "alice", 34, Nations.SG, Nations.TH, MaritalStatus.Married, 6);
        Person defLPerson2 = new Person("U1234567S", "X1234567Z","bob@yx.com", "bob", 37, Nations.US, Nations.CH, MaritalStatus.Single, 0);
        Person defLPerson3 = new Person("M1234567Y", "E1234567E","charlie@yz.com", "charlie", 39, Nations.MY, Nations.SG, MaritalStatus.Divorced, 10);
        Person defLPerson4 = new Person("E1234567S", "N1234567M","Zeno@zy.com", "Zeno", 89, Nations.ES, Nations.ID, MaritalStatus.Married, 34);

        return List.of(defLPerson1, defLPerson2, defLPerson3, defLPerson4);
    }

    @GetMapping("/SGPeopleInfo")
    public Optional<List<Person>> SG_PEOPLE_INFO() {
        //PersonRepository.findBy(a,a
        return personRepository.findByNationality(Nations.SG);
    }

    @GetMapping("/PeopleInfo/{nationality}")
    public Optional<List<Person>> GET_PEOPLE_INFO_Nationality(@PathVariable String nationality) {
        return personRepository.findByNationality(Nations.valueOf(nationality));
    }

    // mixed protobuf changes

    @PostMapping(value = "/PeopleInfo/ProtoPerson", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonProtobufDTO proto_POST_PEOPLE_INFO_PERSON(@RequestBody IdentificationPassportNationalityDTO identificationPassportNationalityDTO) {
        //System.out.println(personRequestDTO);
        Person resPerson = personRepository.findByIdentificationNumberAndPassportNumberAndNationality(identificationPassportNationalityDTO.getIdentificationNumber(), identificationPassportNationalityDTO.getPassportNumber(), Nations.valueOf(identificationPassportNationalityDTO.getNationality().name())).orElse(new Person());
        return resPerson.toPersonProtobufDTO();
    }

    @PostMapping(value = "/PeopleInfo/PersonJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonProtobufDTO proto_POST_PEOPLE_INFO_PERSON_JSON(@RequestBody PersonRequestDTO personRequestDTO) {
        //System.out.println(personRequestDTO);
        Person resPerson = personRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequestDTO.getIdentificationNumber(), personRequestDTO.getPassportNumber(), personRequestDTO.getNationality()).orElse(new Person());
        return resPerson.toPersonProtobufDTO();

    }

    // protobuf change, send AND receive protobuf

    @PostMapping(value = "/PeopleInfo/ProtoNationality", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonProtobufListDTO proto_POST_PEOPLE_INFO_Nationality(@RequestBody NationalityDTO nationality) {
        List<PersonProtobufDTO> people_list = personRepository.findByNationality(Nations.valueOf(nationality.getNationality().name())).orElse(new ArrayList<>()).stream().map(Person::toPersonProtobufDTO).toList();
        return PersonProtobufListDTO.newBuilder().addAllPersonProtobufListDTO(people_list).build();
    }

    @PostMapping(value = "PeopleInfo/ProtoPeopleEmail", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public PersonNameEmailListDTO proto_POST_PEOPLE_INFO_PEOPLE_EMAIL(@RequestBody NationalityDTO nationality) {
        List<PersonNameEmailDTO> people_list = personRepository.findByNationality(Nations.valueOf(nationality.getNationality().name())).orElse(new ArrayList<>()).stream().map(people-> {
            return PersonNameEmailDTO.newBuilder()
                    .setName(people.getName())
                    .setEmail(people.getEmail()).build();
        }).toList();
        return PersonNameEmailListDTO.newBuilder().addAllPersonNameEmailList(people_list).build();
    }

    // receive json and send protobuf

    @PostMapping(value = "/PeopleInfo/NationalityJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonProtobufListDTO proto_POST_PEOPLE_INFO_Nationality_JSON(@RequestBody NationalityPOJO nationalityJSON) {
        List<PersonProtobufDTO> people_list = personRepository.findByNationality(Nations.valueOf(nationalityJSON.getNationality().name())).orElse(new ArrayList<>()).stream().map(Person::toPersonProtobufDTO).toList();
        /*
        System.out.println("STA");
        System.out.println(people_list);
        System.out.println("MID");
        PersonProtobufListDTO check1 = PersonProtobufListDTO.newBuilder().addAllPersonProtobufListDTO(people_list).build();
        System.out.println(check1);
        System.out.println((String) null);
        System.out.println(check1.getPersonProtobufListDTOList());
        System.out.println("END");
        return check1;
        */
        return PersonProtobufListDTO.newBuilder().addAllPersonProtobufListDTO(people_list).build();
    }
    @PostMapping(value = "PeopleInfo/PeopleEmailJSON", consumes = "application/json", produces = "application/x-protobuf")
    public PersonNameEmailListDTO proto_POST_PEOPLE_INFO_PEOPLE_EMAIL_JSON(@RequestBody NationalityPOJO nationalityJSON) {
        Optional<List<Person>> people_list = personRepository.findByNationality(nationalityJSON.getNationality());
        return PersonNameEmailListDTO.newBuilder().addAllPersonNameEmailList(people_list.orElse(new ArrayList<>()).stream().map((people) -> {
            return PersonNameEmailDTO.newBuilder()
                    .setName(people.getName())
                    .setEmail(people.getEmail()).build();
        }).collect(Collectors.toList())).build();
    }
}