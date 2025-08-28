package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDBRepository implements EmployeeRepository{
    @Autowired
    JpaEmployeeRepository jpaEmployeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return jpaEmployeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return jpaEmployeeRepository.getEmployeesById(id);
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        return jpaEmployeeRepository.getEmployeesByGender(gender);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return jpaEmployeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return jpaEmployeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        jpaEmployeeRepository.delete(employee);
    }

    @Override
    public Page<Employee> getByPageSize(Pageable pageable){
        return jpaEmployeeRepository.findAll(pageable);
    }
}
