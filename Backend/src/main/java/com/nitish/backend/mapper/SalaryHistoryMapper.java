package com.nitish.backend.mapper;

import com.nitish.backend.dto.SalaryResponse;
import com.nitish.backend.entity.EmployeeSalary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryHistoryMapper {
    public SalaryResponse toSalaryResponse(EmployeeSalary salary) {
        return new SalaryResponse(
                salary.getPaymentDate(),
                salary.getAmount(),
                salary.getEmployees().getFirstName(),
                salary.getEmployees().getLastName()
        );
    }
}
