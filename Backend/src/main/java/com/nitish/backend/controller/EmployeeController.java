package com.nitish.backend.controller;

import com.nitish.backend.dto.EmployeeDetailsResponse;
import com.nitish.backend.dto.EmployeeRequest;
import com.nitish.backend.dto.EmployeeResponse;
import com.nitish.backend.entity.Employees;
import com.nitish.backend.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
//@CrossOrigin
//@RequestMapping("/api/v1")
public class EmployeeController {
    public final EmployeeService employeeService;


    @PostMapping("/register")
    public ResponseEntity<Employees> createEmployee(@RequestBody @Valid Employees employees) {
        return ResponseEntity.ok(employeeService.createEmployee(employees));
    }

    @GetMapping("/dashboard/{email}")
    public ResponseEntity<EmployeeDetailsResponse> getEmployee(@PathVariable String email) {
//        return "Hello";
        return ResponseEntity.ok(employeeService.employeeDetails(email));
    }
}
