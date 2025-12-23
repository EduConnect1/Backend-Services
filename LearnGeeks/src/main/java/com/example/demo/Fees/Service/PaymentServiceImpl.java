package com.example.demo.Fees.Service;
import com.example.demo.Fees.DTO.PaymentRequest;
import com.example.demo.Fees.DTO.PaymentResponse;
import com.example.demo.Fees.Model.Payment;
import com.example.demo.Fees.Model.StudentFee;
import com.example.demo.Fees.Repository.PaymentRepository;
import com.example.demo.Fees.Repository.StudentFeeRepository;

import com.example.demo.SchoolStructure.Model.Student;
import com.example.demo.SchoolStructure.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final StudentFeeRepository studentFeeRepository;

    @Override
    public void recordPayment(PaymentRequest request) {

        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Payment payment = Payment.builder()
                .student(student)
                .amount(request.amount())
                .paymentDate(
                        request.paymentDate() != null
                                ? request.paymentDate()
                                : LocalDateTime.now()
                )
                .method(request.method())
                .build();

        paymentRepository.save(payment);

        List<StudentFee> fees = studentFeeRepository.findByStudent(student);

        for (StudentFee fee : fees) {
            if (!fee.getFullyPaid()) {
                double newPaid = fee.getAmountPaid() + request.amount();
                fee.setAmountPaid(newPaid);
                fee.setFullyPaid(newPaid >= fee.getAmountDue());
                studentFeeRepository.save(fee);
                break;
            }
        }
    }

    @Override
    public List<PaymentResponse> getPaymentsForStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return paymentRepository.findByStudent(student)
                .stream()
                .map(p -> new PaymentResponse(
                        p.getId(),
                        student.getId(),
                        p.getAmount(),
                        p.getPaymentDate(),
                        p.getMethod()
                ))
                .toList();
    }
}
