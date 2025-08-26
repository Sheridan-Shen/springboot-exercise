package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.dao.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oocl.springboot_exercise.dao.Company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private CompanyService companyService;

    private Map<Integer, Employee> employeeDb = new HashMap<>();
    private AtomicInteger employeeIdGenerator = new AtomicInteger(0);

    public Employee addEmployeeToCompany(Integer companyId, Employee employee) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return null;
        }

        Integer newId = employeeIdGenerator.incrementAndGet();
        employee.setId(newId);
        company.getEmployees().add(employee);
        employeeDb.put(newId, employee);

        return employee;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeDb.get(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeDb.values().stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeDb.values());
    }
}
