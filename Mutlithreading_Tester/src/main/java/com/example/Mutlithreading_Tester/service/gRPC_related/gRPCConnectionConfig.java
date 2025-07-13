package com.example.Mutlithreading_Tester.service.gRPC_related;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class gRPCConnectionConfig {

    @Bean
    @Qualifier("gRPCAttemptChannel")
    public ManagedChannel gRPCAttemptChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090)
                //.useTransportSecurity()
                .usePlaintext()
                .maxInboundMessageSize(100 * 1024 * 1024)
                .build();
    }

}
