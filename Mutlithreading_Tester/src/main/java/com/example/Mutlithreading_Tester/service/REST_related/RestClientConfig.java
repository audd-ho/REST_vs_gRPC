package com.example.Mutlithreading_Tester.service.REST_related;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
//import org.springframework.web.reactive.function.client.ExchangeStrategies;

@Configuration
public class RestClientConfig {
    @Bean(name = "peopleApiClient")
    public RestClient peopleApiClient(RestClient.Builder builder) {
        return  builder
                .baseUrl("http://localhost:8080/People")
                .build();
    }

    @Bean(name = "peopleImageApiClient")
    public RestClient peopleImageApiClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080/PeopleImage")
                .build();
    }
//
    @Bean(name = "peopleInfoApiClient")
    public RestClient peopleInfoApiClient(RestClient.Builder builder) {
        return  builder
                .baseUrl("http://localhost:8080/People/PeopleInfo")
                .build();
    }
//
    @Bean(name = "PeopleProtoApiClient")
    public RestClient peopleProtoApiClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080/PeopleProto/PeopleInfo")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROTOBUF_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_PROTOBUF_VALUE)
                .build();
    }

    @Bean(name = "JSONPeopleProtoApiClient")
    public RestClient JSONpeopleProtoApiClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080/PeopleProto/PeopleInfo")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_PROTOBUF_VALUE)
                .build();
    }

    @Bean(name = "PeopleImageStringProtoApiClient")
    public RestClient PeopleImageStringProtoApiClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080/PeopleImageStringProto/PeopleInfo")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROTOBUF_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_PROTOBUF_VALUE)
                .build();
    }

    @Bean(name = "JSONPeopleImageStringProtoApiClient")
    public RestClient JSONpeopleImageStringProtoApiClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080/PeopleImageStringProto/PeopleInfo")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_PROTOBUF_VALUE)
                .build();
    }

    @Bean(name = "PeopleImageBytesProtoApiClient")
    public RestClient PeopleImageBytesProtoApiClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080/PeopleImageBytesProto/PeopleInfo")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROTOBUF_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_PROTOBUF_VALUE)
                .build();
    }

    @Bean(name = "JSONPeopleImageBytesProtoApiClient")
    public RestClient JSONPeopleImageBytesProtoApiClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080/PeopleImageBytesProto/PeopleInfo")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_PROTOBUF_VALUE)
                .build();
    }
}
