package com.alok.grpc;

import com.alok.grpc.impl.AbcDataService;
import com.alok.grpc.impl.ExpenseService;
import com.alok.grpc.impl.UserService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcServer implements ApplicationRunner {

    @Autowired
    private ExpenseService expenseService;
    public static void main(String[] args) {
        SpringApplication.run(GrpcServer.class, args);
    }
    @Override
    public void run(ApplicationArguments args) throws IOException, InterruptedException {

        System.out.println("Staring gRPC Server...");

        Server server = ServerBuilder
                .forPort(9090)
                .addService(new UserService())
                .addService(new AbcDataService())
                .addService(expenseService)
                .build();

        server.start();
        System.out.println("Staring gRPC Server started...");
        server.awaitTermination();
        System.out.println("Staring gRPC Server terminated...");
    }
}
