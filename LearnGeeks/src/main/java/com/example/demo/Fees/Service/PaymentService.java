package com.example.demo.Fees.Service;

import com.example.demo.Fees.DTO.PaymentRequest;
import com.example.demo.Fees.DTO.PaymentResponse;

import java.util.List;

public interface PaymentService {

    void recordPayment(PaymentRequest request);

    List<PaymentResponse> getPaymentsForStudent(Long studentId);
}
