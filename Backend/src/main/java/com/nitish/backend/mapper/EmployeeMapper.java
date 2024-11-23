package com.nitish.backend.mapper;

import com.nitish.backend.dto.EmployeeRequest;
import com.nitish.backend.dto.EmployeeResponse;
import com.nitish.backend.entity.Employees;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public Employees toEmployee(EmployeeRequest request) {
        Employees employee = new Employees();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setPassword(request.password());
        employee.setTitle(request.title());
        employee.setPhotographPath(request.photographPath());
        employee.setDepartment(request.department());

        return employee;
    }

    public EmployeeResponse toEmployeeResponse(Employees employee) {
        return new EmployeeResponse(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getTitle(),
                employee.getPhotographPath(),
                employee.getDepartment()
        );
    }
}
