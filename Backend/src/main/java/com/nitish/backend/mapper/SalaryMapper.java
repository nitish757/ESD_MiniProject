package com.nitish.backend.mapper;

import com.nitish.backend.dto.SalaryResponse;
import com.nitish.backend.entity.EmployeeSalary;
import org.springframework.stereotype.Service;

@Service
public class SalaryMapper {
    public SalaryResponse toSalaryResponse(EmployeeSalary salary) {
        return new SalaryResponse(
                salary.getPaymentDate(),
                salary.getAmount(),
                salary.getDescription()
        );
//        SalaryResponse sal = new SalaryResponse();
//                sal.setPaymentDate(salary.getPaymentDate());
//                sal.setAmount(salary.getAmount());
//                sal.setDescription(salary.getDescription());
//        return sal;
    }
}
