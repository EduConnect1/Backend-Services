package com.example.demo.fees.service;

import com.example.demo.fees.dto.FeeStructureRequest;
import com.example.demo.fees.dto.FeeStructureResponse;

import java.util.List;

public interface FeeStructureService {

    Long createFeeStructure(FeeStructureRequest request);

    List<FeeStructureResponse> getFeeStructuresByClass(Long classId);
}

