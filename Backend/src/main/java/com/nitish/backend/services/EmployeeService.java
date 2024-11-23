package com.nitish.backend.services;

import com.nitish.backend.dto.EmployeeRequest;
import com.nitish.backend.dto.EmployeeResponse;
import com.nitish.backend.dto.LoginRequest;
import com.nitish.backend.entity.Employees;
import com.nitish.backend.helper.JWTHelper;
import com.nitish.backend.mapper.EmployeeMapper;
import com.nitish.backend.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired
    private final EmployeeRepo employeeRepo;
    @Autowired
    private final EmployeeMapper employeeMapper;
    @Autowired
    private final JWTHelper jwtService;

    @Autowired
    AuthenticationManager authManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employees employee = employeeMapper.toEmployee(request);
        employee.setPassword(encoder.encode(employee.getPassword()));
        return employeeMapper.toEmployeeResponse(employeeRepo.save(employee));
    }


    public String login(LoginRequest request) {
        Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(request.email());

        return "Fail";
    }
}
