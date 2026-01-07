package com.example.demo.fees.service;

import com.example.demo.fees.dto.PaymentRequest;
import com.example.demo.fees.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {

    void recordPayment(PaymentRequest request);

    List<PaymentResponse> getPaymentsForStudent(Long studentId);
}
