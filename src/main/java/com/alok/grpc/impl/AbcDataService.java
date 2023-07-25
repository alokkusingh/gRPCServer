package com.alok.grpc.impl;

import com.alok.grpc.stub.AbcDataServiceGrpc;
import com.alok.grpc.stub.AbcDataServiceOuterClass;
import com.alok.grpc.stub.AbcDataServiceOuterClass.AbcDataResponse;
import com.alok.grpc.stub.AbcDataServiceOuterClass.AbcDataRequest;
import io.grpc.stub.StreamObserver;
import java.time.LocalDateTime;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

public class AbcDataService extends AbcDataServiceGrpc.AbcDataServiceImplBase {

    @Override
    public void getAbcData(AbcDataRequest request,
                           StreamObserver<AbcDataResponse> responseObserver) {
        System.out.println("AbcDataService::getAbcData() - id: "
                + request.getId() + ", month: " + request.getMonth());

        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            AbcDataResponse response = AbcDataResponse.newBuilder()
                    .setId(request.getId())
                    .setMonth(request.getMonth())
                    .setAbcData(AbcDataServiceOuterClass.AbcDataPayload.newBuilder()
                            .setX(LocalDateTime.now().toString())
                            .setY(String.valueOf(Math.random()))
                            //.setZ(String.valueOf(i))
                            .build())
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }
}
