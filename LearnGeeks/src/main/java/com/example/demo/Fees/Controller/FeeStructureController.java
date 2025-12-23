package com.example.demo.Fees.Controller;

import com.example.demo.Fees.DTO.FeeStructureRequest;
import com.example.demo.Fees.DTO.FeeStructureResponse;
import com.example.demo.Fees.Service.FeeStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees/structures")
@RequiredArgsConstructor
public class FeeStructureController {

    private final FeeStructureService feeStructureService;

    /**
     * Admin only
     */
    @PostMapping
    public ResponseEntity<Long> createFeeStructure(
            @RequestBody FeeStructureRequest request) {

        return ResponseEntity.ok(
                feeStructureService.createFeeStructure(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<FeeStructureResponse>> getFeeStructures(
            @RequestParam Long classId) {

        return ResponseEntity.ok(
                feeStructureService.getFeeStructuresByClass(classId)
        );
    }
}
