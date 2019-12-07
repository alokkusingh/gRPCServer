package com.alok.grpc.impl;

import com.alok.grpc.stub.UserGrpc.UserImplBase;
import com.alok.grpc.stub.UserOuterClass.APIResponse;
import com.alok.grpc.stub.UserOuterClass.Empty;
import com.alok.grpc.stub.UserOuterClass.LoginRequest;
import io.grpc.stub.StreamObserver;

public class UserService extends UserImplBase {
    @Override
    public void login(LoginRequest request, StreamObserver<APIResponse> responseObserver) {
        System.out.println("UserService::login()");

        String username = request.getUsername();
        String password = request.getPassword();
        APIResponse.Builder responseBuilder = APIResponse.newBuilder();
        if (username.equals(password)) {
            responseBuilder
                    .setResponseCode(0)
                    .setMessage("Login Success");
        } else {
            responseBuilder
                    .setResponseCode(-1)
                    .setMessage("Login Failed");
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void logout(Empty request, StreamObserver<APIResponse> responseObserver) {
        System.out.println("UserService::logout()");

        APIResponse.Builder responseBuilder = APIResponse.newBuilder();
        responseBuilder
            .setResponseCode(0)
            .setMessage("Logged Out");

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
