package com.dalircode.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("gRPC Server");

        Server server = ServerBuilder.forPort(50013)
                .addService(new GreetServiceImpl())
                .build();

        server.start();
        System.out.println("Server started at port: " + server.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread( () -> server.shutdown()));
        server.awaitTermination();
    }
}
