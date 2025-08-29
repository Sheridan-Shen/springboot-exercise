package com.oocl.springboot_exercise.controller.mapper;

import com.oocl.springboot_exercise.controller.dto.EmployeeRequest;
import com.oocl.springboot_exercise.controller.dto.EmployeeResponse;
import com.oocl.springboot_exercise.models.Company;
import com.oocl.springboot_exercise.models.Employee;
import com.oocl.springboot_exercise.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    @Autowired
    private CompanyService companyService;

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        Company company = new Company(employee.getCompany().getId(), employee.getCompany().getName());
        employeeResponse.setCompany(company);

        return employeeResponse;
    }

    public List<EmployeeResponse> toResponse(List<Employee> employees) {
        return employees.stream().map(this::toResponse).toList();
    }

    public Page<EmployeeResponse> toResponse(Page<Employee> employees) {
        List<Employee> employeeList = employees.getContent();
        List<EmployeeResponse> employeeResponses = toResponse(employeeList);

        return new PageImpl<>(
                employeeResponses,        // 转换后的 DTO 列表
                employees.getPageable(),  // 分页信息（当前页、页大小、排序等）
                employees.getTotalElements() // 总记录数（用于计算总页数）
        );
    }

    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        Company company = companyService.getCompanyById(employeeRequest.getCompanyId());
        employee.setCompany(company);
        return employee;
    }
}
