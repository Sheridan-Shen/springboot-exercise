package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.models.Employee;
import com.oocl.springboot_exercise.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void getAllEmployees() {

    }

    @Test
    void should_create_employee_successfully(){
        // 输入
        Employee inputEmployee = new Employee("oocl",22,"MALE",30000, true);

        // 模拟输出
        Employee mockEmployee = new Employee(1, inputEmployee.getName(),inputEmployee.getAge(),inputEmployee.getGender(),inputEmployee.getSalary(),inputEmployee.getStatus());

        Mockito.when(employeeRepository.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

        // 模拟输出的结果
        Employee outputEmployee = employeeService.addEmployee(inputEmployee);

        assertEquals(outputEmployee.getName(),inputEmployee.getName());
        assertEquals(outputEmployee.getAge(),inputEmployee.getAge());
        assertEquals(outputEmployee.getGender(),inputEmployee.getGender());
        assertEquals(outputEmployee.getSalary(),inputEmployee.getSalary());
        assertNotNull(outputEmployee.getStatus());
    }


}