package com.example.demo.Fees.Controller;

import com.example.demo.Fees.DTO.PaymentRequest;
import com.example.demo.Fees.DTO.PaymentResponse;
import com.example.demo.Fees.Service.PaymentService;
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
