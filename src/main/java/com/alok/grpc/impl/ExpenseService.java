package com.alok.grpc.impl;

import com.alok.grpc.repository.ExpenseRepository;
import com.alok.grpc.stub.ExpenseServiceGrpc;
import com.alok.grpc.stub.ExpenseServiceOuterClass;
import com.alok.grpc.stub.ExpenseServiceOuterClass.ExpenseRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alok.grpc.stub.ExpenseServiceOuterClass.ExpenseEntry;

@Service
public class ExpenseService extends ExpenseServiceGrpc.ExpenseServiceImplBase {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public void getAllExpenses(ExpenseRequest request,
                               StreamObserver<ExpenseEntry> responseObserver) {
        System.out.println("ExpenseService: " + request);

        expenseRepository.findAll().forEach(
            expense -> {
                responseObserver.onNext(ExpenseEntry.newBuilder()
                    .setId(expense.getId())
                    .setDate(expense.getStrDate())
                    .setAmount(expense.getAmount())
                    .setCategory(expense.getCategory())
                    .setComment(expense.getComment())
                    .setMonthx(expense.getMonthx())
                    .setYearx(expense.getYearx())
                    .build());
            }
        );
        responseObserver.onCompleted();
    }
}
