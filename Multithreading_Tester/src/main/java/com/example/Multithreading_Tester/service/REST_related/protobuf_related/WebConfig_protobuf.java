package com.example.Multithreading_Tester.service.REST_related.protobuf_related;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.codec.protobuf.ProtobufDecoder;
//import org.springframework.http.codec.protobuf.ProtobufEncoder;
//import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@Configuration
public class WebConfig_protobuf {
    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
}
