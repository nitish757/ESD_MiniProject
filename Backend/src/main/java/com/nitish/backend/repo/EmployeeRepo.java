package com.nitish.backend.repo;

import com.nitish.backend.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Integer> {
    Employees findByEmail(String username);
}
