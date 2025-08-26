package com.oocl.springboot_exercise.controller;

import com.oocl.springboot_exercise.dao.Employee;
import com.oocl.springboot_exercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee, @RequestParam Integer companyId) {
        Employee addedEmployee = employeeService.addEmployeeToCompany(companyId, employee);
        if (addedEmployee != null) {
            return ResponseEntity.ok(addedEmployee);
        } else {
            return ResponseEntity.badRequest().build(); // 400 Bad Request，公司不存在
        }
    }
}