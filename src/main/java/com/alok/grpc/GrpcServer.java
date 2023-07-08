package com.alok.grpc;

import com.alok.grpc.impl.AbcDataService;
import com.alok.grpc.impl.UserService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Staring gRPC Server...");

        Server server = ServerBuilder
                .forPort(9090)
                .addService(new UserService())
                .addService(new AbcDataService())
                .build();

        server.start();
        System.out.println("Staring gRPC Server started...");
        server.awaitTermination();
        System.out.println("Staring gRPC Server terminated...");
    }
}
