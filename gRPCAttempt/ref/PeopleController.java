package com.example.RESTAttempt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.RESTAttempt.controller.dto.request.PersonRequestDTO;
import com.example.RESTAttempt.controller.dto.response.PeopleEmailResponseDTO;
import com.example.RESTAttempt.entity.Person;
import com.example.RESTAttempt.enums.MaritalStatus;
import com.example.RESTAttempt.enums.Nations;
import com.example.RESTAttempt.repository_JPA.PersonRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/People")
public class PeopleController {

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    private final PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
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

    @PostMapping("/PeopleInfo/Nationality")
    public Optional<List<Person>> POST_PEOPLE_INFO_Nationality(@RequestBody String nationality) {
        System.out.println(nationality);
        return personRepository.findByNationality(Nations.valueOf(nationality));
    }

    @PostMapping("/PeopleInfo/Person")
    public Optional<Person> POST_PEOPLE_INFO_PERSON(@RequestBody PersonRequestDTO personRequestDTO) {
        System.out.println(personRequestDTO);
        return personRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequestDTO.getIdentificationNumber(), personRequestDTO.getPassportNumber(), personRequestDTO.getNationality());
    }

    @PostMapping("PeopleInfo/PeopleEmail")
    public List<PeopleEmailResponseDTO> POST_PEOPLE_INFO_PEOPLE_EMAIL(@RequestBody Nations nationality) {
        System.out.println("EMAIL" + nationality);
        Optional<List<Person>> people_list = personRepository.findByNationality(nationality);
        /*
        if (people_list == null) {
            return new ArrayList<>();
        }
        */
        return people_list.orElse(new ArrayList<>()).stream().map((people) -> {
            return new PeopleEmailResponseDTO(people.getName(), people.getEmail());
        }).collect(Collectors.toList());
    }
}