package com.example.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9091}") int serverPort) {
        log.info("Connecting to billing service gRPC server at {}:{} using PLAINTEXT", serverAddress, serverPort);
        // The billing-service runs without TLS; attempting TLS causes NotSslRecordException on the client.
        // Use plaintext to match the server configuration.
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(serverAddress, serverPort)
                .usePlaintext()
                .build();
        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patinetId, String name, String email) {
        BillingRequest request = BillingRequest.newBuilder().setPatientId(patinetId).setName(name).setEmail(email).build();
        log.info("Creating billing account response {}", blockingStub.createBillingAccount(request));
        return blockingStub.createBillingAccount(request);
    }
}
