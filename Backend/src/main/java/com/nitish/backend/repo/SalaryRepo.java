package com.nitish.backend.repo;

import com.nitish.backend.entity.EmployeeSalary;
import com.nitish.backend.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepo extends JpaRepository<EmployeeSalary, Integer> {

//    @Query("select s from EmployeeSalary s where s.employeeId=?1 order by s.paymentDate desc limit 2")
//    List<EmployeeSalary> findByEmployeeId(@Param("employeeId") Employees employeeId);

    @Query("select s from EmployeeSalary s where s.employeeId=?1 order by s.paymentDate desc")
   List<EmployeeSalary> findHistoryByEmployeeId(@Param("employeeId")Employees employeeId);

    @Query("select s from EmployeeSalary s where s.employeeId=?1 order by s.paymentDate desc limit 4")
    List<EmployeeSalary> findByEmployee(@Param("employeeId")Employees employeeId);

    @Query("select s from EmployeeSalary s where s.employeeId=?1 and MONTH(s.paymentDate)=?2 order by s.paymentDate desc")
    List<EmployeeSalary> findByMonth(Employees employee, int month);

    // @Query("SELECT s FROM EmployeeSalary s WHERE MONTH(s.paymentDate) = ? AND YEAR(s.paymentDate) = ?;")
}

