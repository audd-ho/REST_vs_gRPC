package com.example.Multithreading_Tester.service.REST_related.non_protobuf_related;

import com.example.Multithreading_Tester.entity.Person;
import com.example.Multithreading_Tester.entity.PersonImage;
import com.example.Multithreading_Tester.enums.Nations;
import com.example.Multithreading_Tester.service.dto.request.NationalityPOJO;
import com.example.Multithreading_Tester.service.dto.request.PersonRequestDTO;
import com.example.Multithreading_Tester.service.dto.response.PeopleEmailImageResponseDTO;
import com.example.Multithreading_Tester.service.dto.response.PeopleEmailResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PeopleImageService {
    private final RestClient restClient;

    public PeopleImageService(@Qualifier("peopleImageApiClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public PersonImage personImage(PersonRequestDTO personRequestDTO) {
        return restClient.post()
                .uri("/PeopleInfo/PersonImage")
                .body(personRequestDTO)
                .retrieve()
                .body(PersonImage.class);
                //.body(Optional<PersonImage>.class)
                //.orElse(new PersonImage());
    }

    public List<PersonImage> nationalityImageJSON(Nations nationality) {
        ParameterizedTypeReference<List<PersonImage>> parameterizedTypeReference = new ParameterizedTypeReference<List<PersonImage>>() {};
        return restClient.post()
                .uri("/PeopleInfo/NationalityImageJSON")
                .body(new NationalityPOJO(nationality)) // do i need to specify string or can pass nationality by itself? enum?? will auto convert and understood and used as string? or??
                .retrieve()
                .body(parameterizedTypeReference);
    }

    public List<PeopleEmailImageResponseDTO> peopleEmailImageJSON(Nations nationality) {
        ParameterizedTypeReference<List<PeopleEmailImageResponseDTO>> parameterizedTypeReference = new ParameterizedTypeReference<List<PeopleEmailImageResponseDTO>>() {};
        return restClient.post()
                .uri("/PeopleInfo/PeopleEmailImageJSON")
                .body(new NationalityPOJO(nationality)) // will the value be an enum or be string when passed through the call?
                .retrieve()
                .body(parameterizedTypeReference);
    }

}
