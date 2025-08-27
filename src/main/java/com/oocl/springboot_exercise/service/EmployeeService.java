package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.exception.InvalidEmployeeException;
import com.oocl.springboot_exercise.models.Employee;
import com.oocl.springboot_exercise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oocl.springboot_exercise.models.Company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private CompanyService companyService;

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    public Employee addEmployeeToCompany(Integer companyId, Employee employee) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return null;
        }

        if (employee.getAge() < 18 && employee.getAge() > 65){
            throw new InvalidEmployeeException("年龄不在 18 到 65 岁之间的员工不能被创建。");
        }

        if (employee.getAge() >= 30 && employee.getSalary() < 2000){
            throw new InvalidEmployeeException("年龄大于等于 30 岁且薪资低于 20000 的员工不能被创建");
        }

        employee.setCompanyId(companyId);
        employee.setActivate(true);
        employeeRepository.addEmployee(employee);
        company.getEmployees().add(employee);

        return employee;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.getEmployee(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployees().values().stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRepository.getEmployees().values());
    }

    public Employee updateEmployeeInfo(Integer id, Employee employeeDetails) {
        Employee employee = employeeRepository.getEmployee(id);
        if (!employee.isActivate()){
            throw new InvalidEmployeeException("员工已经离职, 不能进行更新");
        }
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
        if (!employeeRepository.getEmployee(id).isActivate()){
            throw new InvalidEmployeeException("员工已经离职, 不能进行更新");
        }
        employeeRepository.putEmployee(id, employeeToSave);
        return employeeToSave;
    }

    public boolean deleteEmployee(Integer id) {
        Employee employee = employeeRepository.removeEmployee(id);
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
        List<Employee> allEmployees = new ArrayList<>(employeeRepository.getEmployees().values());
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, allEmployees.size());

        if (fromIndex > allEmployees.size()) {
            return Collections.emptyList();
        }

        return allEmployees.subList(fromIndex, toIndex);
    }

}
