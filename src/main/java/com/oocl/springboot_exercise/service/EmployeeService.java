package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oocl.springboot_exercise.models.Company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private CompanyService companyService;

//    private Map<Integer, Employee> employeeDb = new HashMap<>();
    private final Map<Integer, Employee> employeeDb = new HashMap<>(Map.of(
            1, new Employee(1, "John Smith", 32, "MALE", 5000.0),
            2, new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
            3, new Employee(3, "David Williams", 35, "MALE", 5500.0),
            4, new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
            5, new Employee(5, "Michael Jones", 40, "MALE", 7000.0)));
    private AtomicInteger employeeIdGenerator = new AtomicInteger(6);

    public Employee addEmployeeToCompany(Integer companyId, Employee employee) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return null;
        }

        if (employee.getAge() < 18 && employee.getAge() > 65){
            return null;
        }

        if (employee.getAge() >= 30 && employee.getSalary() < 2000){
            throw new IllegalArgumentException("年龄大于等于 30 岁且薪资低于 20000 的员工不能被创建");
        }

        Integer newId = employeeIdGenerator.incrementAndGet();
        employee.setId(newId);
        employee.setCompanyId(companyId);
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

    public Employee updateEmployeeInfo(Integer id, Employee employeeDetails) {
        Employee employee = employeeDb.get(id);
        if (employee != null) {
            // 更新员工信息
            if (employeeDetails.getName() != null) {
                employee.setName(employeeDetails.getName());
            }
            if (employeeDetails.getAge() > 0) {
                employee.setAge(employeeDetails.getAge());
            }
            if (employeeDetails.getGender() != null) {
                employee.setGender(employeeDetails.getGender());
            }
            if (employeeDetails.getSalary() >= 0) {
                employee.setSalary(employeeDetails.getSalary());
            }
            return employee;
        }
        return null;
    }

    public Employee fullReplaceEmployee(Integer id, Employee employeeToSave){
        employeeDb.put(id, employeeToSave);
        return employeeToSave;
    }

    public boolean deleteEmployee(Integer id) {
        Employee employee = employeeDb.remove(id);
        if (employee != null) {
            Company company = companyService.getCompanyById(employee.getCompanyId());
            if (company != null) {
                company.getEmployees().removeIf(e -> e.getId().equals(id));
            }
            return true;
        }
        return false;
    }

    public List<Employee> getEmployeesByPage(Integer page, Integer size) {
        List<Employee> allEmployees = new ArrayList<>(employeeDb.values());
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, allEmployees.size());

        if (fromIndex > allEmployees.size()) {
            return Collections.emptyList();
        }

        return allEmployees.subList(fromIndex, toIndex);
    }

}
