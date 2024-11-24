package com.nitish.backend.repo;

import com.nitish.backend.dto.SalaryResponse;
import com.nitish.backend.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepo extends JpaRepository<EmployeeSalary, Integer> {

    @Query("select s,e from EmployeeSalary s join Employees e on e.employee_id=s.employee_id where s.employee_id=?1 order by s.paymentDate desc limit 1")
    EmployeeSalary findByEmployeeId(@Param("employeeId") int employeeId);

    @Query("select s from EmployeeSalary s where s.employee_id=?1 order by s.paymentDate desc")
   List<EmployeeSalary> findHistoryByEmployeeId(int employeeId);
}

