package com.dalircode.grpc.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("gRPC Client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50013)
                .usePlaintext()
                .build();

        GreetServiceGrpc.GreetServiceBlockingStub syncClient = GreetServiceGrpc.newBlockingStub(channel);

        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Kamran")
                .setLastName("Dalir")
                .build();

        GreetRequest request = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        GreetResponse response = syncClient.greet(request);

        System.out.println(response.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
