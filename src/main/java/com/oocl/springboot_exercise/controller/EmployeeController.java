package com.oocl.springboot_exercise.controller;

import com.oocl.springboot_exercise.controller.dto.EmployeeResponse;
import com.oocl.springboot_exercise.controller.mapper.EmployeeMapper;
import com.oocl.springboot_exercise.models.Employee;
import com.oocl.springboot_exercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody Employee employee) {
        Employee addedEmployee = employeeService.addEmployee(employee);
        EmployeeResponse employeeResponse = employeeMapper.toResponse(addedEmployee);
        if (addedEmployee != null) {
            return ResponseEntity.ok(employeeResponse);
        } else {
            return ResponseEntity.badRequest().build(); // 400 Bad Request，公司不存在
        }
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeResponse> employeeResponses = employeeMapper.toResponse(employees);
        return ResponseEntity.ok(employeeResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeResponse employeeResponse = employeeMapper.toResponse(employee);
        if (employee != null) {
            return ResponseEntity.ok(employeeResponse);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping(params = {"gender"})
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByGender(@RequestParam String gender) {
        List<Employee> employees = employeeService.getEmployeesByGender(gender);
        List<EmployeeResponse> employeeResponses = employeeMapper.toResponse(employees);
        return ResponseEntity.ok(employeeResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeInfo(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployeeInfo(id, employeeDetails);
        EmployeeResponse employeeResponse = employeeMapper.toResponse(updatedEmployee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(employeeResponse);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // 完全替换资源
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        if (employee.getId() != null && !id.equals(employee.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        EmployeeResponse employeeResponse = employeeMapper.toResponse(updatedEmployee);
        return ResponseEntity.ok(employeeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    public ResponseEntity<Page<EmployeeResponse>> getEmployeesByPage(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Employee> employees = employeeService.getEmployeesByPage(pageNumber, pageSize);
        Page<EmployeeResponse> employeeResponses = employeeMapper.toResponse(employees);
        return ResponseEntity.ok(employeeResponses);
    }
}