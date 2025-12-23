package com.example.demo.Fees.Service;

import com.example.demo.Fees.DTO.FeeStructureRequest;
import com.example.demo.Fees.DTO.FeeStructureResponse;

import java.util.List;

public interface FeeStructureService {

    Long createFeeStructure(FeeStructureRequest request);

    List<FeeStructureResponse> getFeeStructuresByClass(Long classId);
}

