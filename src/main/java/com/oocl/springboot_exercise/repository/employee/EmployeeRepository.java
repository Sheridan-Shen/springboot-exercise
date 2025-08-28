package com.oocl.springboot_exercise.repository.employee;

import com.oocl.springboot_exercise.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getEmployees();

    Employee getEmployeeById(Integer id);

    List<Employee> getEmployeeByGender(String gender);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Page<Employee> getByPageSize(Pageable pageable);
}
