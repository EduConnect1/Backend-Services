package com.example.demo.fees.controller;
import com.example.demo.fees.dto.PaymentRequest;
import com.example.demo.fees.dto.PaymentResponse;
import com.example.demo.fees.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> recordPayment(
            @RequestBody PaymentRequest request) {

        paymentService.recordPayment(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PaymentResponse>> getStudentPayments(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                paymentService.getPaymentsForStudent(studentId)
        );
    }
}
