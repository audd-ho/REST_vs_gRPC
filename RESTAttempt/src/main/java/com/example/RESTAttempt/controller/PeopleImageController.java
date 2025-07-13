package com.example.RESTAttempt.controller;

import com.example.RESTAttempt.controller.dto.request.NationalityPOJO;
import com.example.RESTAttempt.controller.dto.request.PersonRequestDTO;
import com.example.RESTAttempt.controller.dto.response.PeopleEmailImageResponseDTO;
import com.example.RESTAttempt.controller.dto.response.PeopleEmailResponseDTO;
import com.example.RESTAttempt.entity.PersonImage;
import com.example.RESTAttempt.repository_JPA.PersonImageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/PeopleImage")
public class PeopleImageController {

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    private final PersonImageRepository personImageRepository;

    public PeopleImageController(PersonImageRepository personImageRepository) {
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
    @PostMapping("/PeopleInfo/PersonImage")
    public Optional<PersonImage> POST_PEOPLE_INFO_PERSON_IMAGE(@RequestBody PersonRequestDTO personRequestDTO) {
        //System.out.println(personRequestDTO);
        return personImageRepository.findByIdentificationNumberAndPassportNumberAndNationality(personRequestDTO.getIdentificationNumber(), personRequestDTO.getPassportNumber(), personRequestDTO.getNationality());
    }
    @PostMapping("/PeopleInfo/NationalityImageJSON")
    public List<PersonImage> POST_PEOPLE_INFO_Nationality_Image_JSON(@RequestBody NationalityPOJO nationalityJSON) {
        //System.out.println(nationality);
        return personImageRepository.findByNationality(nationalityJSON.getNationality());
    }
    @PostMapping("PeopleInfo/PeopleEmailImageJSON")
    public List<PeopleEmailImageResponseDTO> POST_PEOPLE_INFO_PEOPLE_EMAIL_Image_JSON(@RequestBody NationalityPOJO nationalityJSON) {
        //System.out.println("EMAIL" + nationality);
        List<PersonImage> people_list = personImageRepository.findByNationality(nationalityJSON.getNationality());
        return people_list.stream().map((people) -> {
            return new PeopleEmailImageResponseDTO(people.getName(), people.getEmail(), people.getBiometrics());
        }).collect(Collectors.toList());
    }
}