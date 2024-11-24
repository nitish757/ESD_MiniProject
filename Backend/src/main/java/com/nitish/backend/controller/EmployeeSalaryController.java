package com.nitish.backend.controller;

import com.nitish.backend.configurations.JwtFilter;
import com.nitish.backend.dto.EmployeeResponse;
import com.nitish.backend.dto.SalaryResponse;
import com.nitish.backend.entity.EmployeeSalary;
import com.nitish.backend.entity.Employees;
import com.nitish.backend.services.EmployeeSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class EmployeeSalaryController {
    @Autowired
    EmployeeSalaryService employeeSalaryService;

    @Autowired
    JwtFilter jwtFilter;

    @GetMapping("/")
    public ResponseEntity<SalaryResponse> getLastSalary(){
        return ResponseEntity.ok(employeeSalaryService.getLastSalary(jwtFilter.getEmailAddress()));
    }

    @GetMapping("/salary/History")
    public ResponseEntity<List<SalaryResponse>> getSalaryHistory(){
        return ResponseEntity.ok(employeeSalaryService.getSalaryHistory(jwtFilter.getEmailAddress()));
    }
}
