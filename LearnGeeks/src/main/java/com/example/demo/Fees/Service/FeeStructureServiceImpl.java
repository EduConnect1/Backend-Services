package com.example.demo.fees.service;

import com.example.demo.fees.dto.FeeStructureRequest;
import com.example.demo.fees.dto.FeeStructureResponse;
import com.example.demo.fees.model.FeeStructure;
import com.example.demo.fees.repository.FeeStructureRepository;

import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.repository.SchoolClassRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeStructureServiceImpl implements FeeStructureService {

    private final FeeStructureRepository feeStructureRepository;
    private final SchoolClassRepository schoolClassRepository;

    @Override
    public Long createFeeStructure(FeeStructureRequest request) {

        SchoolClass schoolClass = schoolClassRepository.findById(request.classId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        FeeStructure feeStructure = FeeStructure.builder()
                .schoolClass(schoolClass)
                .description(request.description())
                .amount(request.amount())
                .year(request.year())
                .build();

        return feeStructureRepository.save(feeStructure).getId();
    }

    @Override
    public List<FeeStructureResponse> getFeeStructuresByClass(Long classId) {

        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        return feeStructureRepository.findBySchoolClass(schoolClass)
                .stream()
                .map(f -> new FeeStructureResponse(
                        f.getId(),
                        schoolClass.getId(),
                        f.getDescription(),
                        f.getAmount(),
                        f.getYear()
                ))
                .toList();
    }
}
