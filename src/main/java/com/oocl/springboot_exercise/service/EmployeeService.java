package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.exception.InvalidEmployeeException;
import com.oocl.springboot_exercise.models.Employee;
import com.oocl.springboot_exercise.repository.employee.EmployeeDBRepository;
import com.oocl.springboot_exercise.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private CompanyService companyService;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeDBRepository employeeDBRepository,CompanyService companyService){
        this.employeeRepository = employeeDBRepository;
        this.companyService = companyService;
    }

    public Employee addEmployee(Employee employee){
        if (employee.getAge() < 18 || employee.getAge() > 65){
            throw new InvalidEmployeeException("年龄不在 18 到 65 岁之间的员工不能被创建");
        }

        if (employee.getAge() >= 30 && employee.getSalary() < 20000){
            throw new InvalidEmployeeException("年龄大于等于 30 岁且薪资低于 20000 的员工不能被创建");
        }

        employeeRepository.addEmployee(employee);
        return employee;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployees().stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee updateEmployeeInfo(Integer id, Employee employeeDetails) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (!employee.getStatus()){
            throw new InvalidEmployeeException("员工已经离职, 不能进行更新");
        }
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
        return employeeRepository.updateEmployee(employee);
    }

    public Employee updateEmployee(Integer id, Employee employeeToSave){
        if (!employeeRepository.getEmployeeById(id).getStatus()){
            throw new InvalidEmployeeException("员工已经离职, 不能进行更新");
        }
        return employeeRepository.updateEmployee(employeeToSave);
    }

    public boolean deleteEmployee(Integer id) {
        Employee targetEmployee = employeeRepository.getEmployeeById(id);
        employeeRepository.deleteEmployee(targetEmployee);
//        if (targetEmployee != null) {
//            Company company = companyService.getCompanyById(targetEmployee.getCompanyId());
//            if (company != null) {
//                company.getEmployees().removeIf(e -> e.getId().equals(id));
//            }
//            return true;
//        }
        return true;
    }

    public void softDeleteEmployee(Integer id) {
        Employee targetEmployee = employeeRepository.getEmployeeById(id);
        targetEmployee.setStatus(false);
        employeeRepository.updateEmployee(targetEmployee);
    }


    public Page<Employee> getEmployeesByPage(int pageNumber, int pageSize) {
        // Pageable 是从 0 开始计数的页码
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return employeeRepository.getByPageSize(pageable);
    }

}
