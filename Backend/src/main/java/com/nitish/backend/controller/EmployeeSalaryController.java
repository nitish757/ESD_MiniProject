package com.nitish.backend.controller;

import com.nitish.backend.dto.SalaryResponse;
import com.nitish.backend.services.EmployeeSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/api/v1")
public class EmployeeSalaryController {
    @Autowired
    EmployeeSalaryService employeeSalaryService;

    @GetMapping("/salary/{email}")
    public ResponseEntity<List<SalaryResponse>> getLastSalary(@PathVariable String email){
        return ResponseEntity.ok(employeeSalaryService.getLastSalary(email));
    }

    @GetMapping("/salaryHistory/{email}")
    public ResponseEntity<List<SalaryResponse>> getSalaryHistory(@PathVariable String email){
        return ResponseEntity.ok(employeeSalaryService.getSalaryHistory(email));
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> getSalary(@RequestParam String email, @RequestParam int month){
        byte[] pdfBytes = employeeSalaryService.generatePdf(email, month);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
