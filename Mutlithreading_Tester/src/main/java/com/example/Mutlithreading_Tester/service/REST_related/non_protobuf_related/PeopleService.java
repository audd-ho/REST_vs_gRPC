package com.example.Mutlithreading_Tester.service.REST_related.non_protobuf_related;

import com.example.Mutlithreading_Tester.entity.Person;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.service.dto.request.NationalityPOJO;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Mutlithreading_Tester.service.dto.response.PeopleEmailResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PeopleService {
    private final RestClient restClient;

    public PeopleService(@Qualifier("peopleApiClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public Person person(PersonRequestDTO personRequestDTO) {
        return restClient.post()
                .uri("/PeopleInfo/Person")
                .body(personRequestDTO)
                .retrieve()
                .body(Person.class);
    }

    public List<Person> nationality(Nations nationality) {
        ParameterizedTypeReference<List<Person>> parameterizedTypeReference = new ParameterizedTypeReference<List<Person>>() {};
        return restClient.post()
                .uri("/PeopleInfo/Nationality")
                .body(nationality.name()) // do i need to specify string or can pass nationality by itself? enum?? will auto convert and understood and used as string? or??
                .retrieve()
                .body(parameterizedTypeReference);
    }

    public List<PeopleEmailResponseDTO> peopleEmail(Nations nationality) {
        ParameterizedTypeReference<List<PeopleEmailResponseDTO>> parameterizedTypeReference = new ParameterizedTypeReference<List<PeopleEmailResponseDTO>>() {};
        return restClient.post()
                .uri("/PeopleInfo/PeopleEmail")
                .body(nationality) // do i need to specify string or can pass nationality by itself? enum?? will auto convert and understood and used as string? or??
                .retrieve()
                .body(parameterizedTypeReference);
    }

    public List<PeopleEmailResponseDTO> peopleEmailJSON(Nations nationality) {
        ParameterizedTypeReference<List<PeopleEmailResponseDTO>> parameterizedTypeReference = new ParameterizedTypeReference<List<PeopleEmailResponseDTO>>() {};
        return restClient.post()
                .uri("/PeopleInfo/PeopleEmailJSON")
                .body(new NationalityPOJO(nationality)) // will the value be an enum or be string when passed through the call?
                .retrieve()
                .body(parameterizedTypeReference);
    }

    public List<Person> nationalityJSON(Nations nationality) {
        ParameterizedTypeReference<List<Person>> parameterizedTypeReference = new ParameterizedTypeReference<List<Person>>() {};
        /*
        NationalityPOJO natpojo = new NationalityPOJO(nationality);
        System.out.println(natpojo);
        System.out.println(natpojo);

        System.out.println(natpojo);
        System.out.println(natpojo);
        System.out.println(natpojo);
        System.out.println(natpojo);

        System.out.println(1);
        System.out.println(natpojo.getNationality());
        System.out.println(natpojo.getNationality().name());
        System.out.println(2);
        */
        return restClient.post()
                .uri("/PeopleInfo/NationalityJSON")
                .body(new NationalityPOJO(nationality)) // do i need to specify string or can pass nationality by itself? enum?? will auto convert and understood and used as string? or??
                .retrieve()
                .body(parameterizedTypeReference);
    }
}
