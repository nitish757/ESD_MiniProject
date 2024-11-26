package com.nitish.backend.mapper;

import com.nitish.backend.dto.EmployeeResponse;
import com.nitish.backend.dto.SalaryResponse;
import com.nitish.backend.entity.EmployeeSalary;
import com.nitish.backend.entity.Employees;
import org.springframework.stereotype.Service;

@Service
public class SalaryMapper {
    public SalaryResponse toSalaryResponse(EmployeeSalary salary) {
        return new SalaryResponse(
                salary.getPaymentDate(),
                salary.getAmount(),
                salary.getDescription()
        );
    }
}
