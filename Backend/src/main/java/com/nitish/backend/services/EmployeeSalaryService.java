package com.nitish.backend.services;

import com.nitish.backend.dto.SalaryResponse;
import com.nitish.backend.entity.EmployeeSalary;
import com.nitish.backend.mapper.SalaryMapper;
import com.nitish.backend.repo.EmployeeRepo;
import com.nitish.backend.repo.SalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeSalaryService {
    @Autowired
    SalaryRepo salaryRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    SalaryMapper mapper;

    public SalaryResponse getLastSalary(String emailAddress) {

        return mapper.toSalaryResponse(salaryRepo.findByEmployeeId(employeeRepo.findByEmail(emailAddress).getEmployee_id()));
    }

    public List<SalaryResponse> getSalaryHistory(String emailAddress) {
        List<EmployeeSalary> latestSalary = salaryRepo.findHistoryByEmployeeId(employeeRepo.findByEmail(emailAddress).getEmployee_id());
        List<SalaryResponse> salaryList = new ArrayList<>();

        for(EmployeeSalary employeeSalary : latestSalary) {
            salaryList.add(mapper.toSalaryResponse(employeeSalary));
        }
        return salaryList;
    }
}
