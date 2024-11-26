package com.nitish.backend.controller;

import com.nitish.backend.configurations.JwtFilter;
import com.nitish.backend.dto.EmployeeRequest;
import com.nitish.backend.dto.EmployeeResponse;
import com.nitish.backend.entity.Employees;
import com.nitish.backend.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
//@CrossOrigin
//@RequestMapping("/api/v1")
public class EmployeeController {
    public final EmployeeService employeeService;

    @Autowired
    JwtFilter jwtFilter;

    @PostMapping("/register")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @GetMapping("/dashboard/{email}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable String email) {
//        return "Hello";
        return ResponseEntity.ok(employeeService.employeeDetails(email));
    }
}
