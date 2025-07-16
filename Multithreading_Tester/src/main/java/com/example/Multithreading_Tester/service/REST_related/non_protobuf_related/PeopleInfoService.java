package com.example.Multithreading_Tester.service.REST_related.non_protobuf_related;

import com.example.Multithreading_Tester.entity.Person;
import com.example.Multithreading_Tester.enums.Nations;
import com.example.Multithreading_Tester.service.dto.request.NationalityPOJO;
import com.example.Multithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Multithreading_Tester.service.dto.response.PeopleEmailResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PeopleInfoService {
    private final RestClient restClient;

    public PeopleInfoService(@Qualifier("peopleInfoApiClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public Person person(PersonRequestDTO personRequestDTO) {
        return restClient.post()
                .uri("/Person")
                .body(personRequestDTO)
                .retrieve()
                .body(Person.class);
    }

    public List<Person> nationality(Nations nationality) {
        ParameterizedTypeReference<List<Person>> parameterizedTypeReference = new ParameterizedTypeReference<List<Person>>() {};
        return restClient.post()
                .uri("/Nationality")
                //.body(nationality) // endpoint seeks string
                .body(nationality.name())
                .retrieve()
                .body(parameterizedTypeReference);
    }

    public List<PeopleEmailResponseDTO> peopleEmail(Nations nationality) {
        ParameterizedTypeReference<List<PeopleEmailResponseDTO>> parameterizedTypeReference = new ParameterizedTypeReference<List<PeopleEmailResponseDTO>>() {};
        return restClient.post()
                .uri("/PeopleEmail")
                //.body(nationality.name()) // the endpoint seeks enum
                .body(nationality)
                .retrieve()
                .body(parameterizedTypeReference);
    }

    public List<PeopleEmailResponseDTO> peopleEmailJSON(Nations nationality) {
        ParameterizedTypeReference<List<PeopleEmailResponseDTO>> parameterizedTypeReference = new ParameterizedTypeReference<List<PeopleEmailResponseDTO>>() {};
        return restClient.post()
                .uri("/PeopleEmailJSON")
                .body(new NationalityPOJO(nationality)) // will the value be an enum or be string when passed through the call?
                .retrieve()
                .body(parameterizedTypeReference);
    }

    public List<Person> nationalityJSON(Nations nationality) {
        ParameterizedTypeReference<List<Person>> parameterizedTypeReference = new ParameterizedTypeReference<List<Person>>() {};
        return restClient.post()
                .uri("/NationalityJSON")
                .body(new NationalityPOJO(nationality)) // do i need to specify string or can pass nationality by itself? enum?? will auto convert and understood and used as string? or??
                .retrieve()
                .body(parameterizedTypeReference);
    }
}
