package com.oocl.springboot_exercise.integration;

import com.oocl.springboot_exercise.models.Employee;
import com.oocl.springboot_exercise.repository.employee.EmployeeDBRepository;
import com.oocl.springboot_exercise.service.EmployeeService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private EmployeeDBRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

//    @BeforeEach
//    public void setUp(){
//        employeeRepository.addEmployee(new Employee(1,"John Smith", 32, "MALE", 5000.0));
//        employeeRepository.addEmployee(new Employee(2,"Jane Johnson", 28, "FEMALE", 6000.0));
//        employeeRepository.addEmployee(new Employee(3,"David Williams", 35, "MALE", 5500.0));
//        employeeRepository.addEmployee(new Employee(4,"Emily Brown", 23, "FEMALE", 4500.0));
//        employeeRepository.addEmployee(new Employee(5,"Michael Jones", 40, "MALE", 7000.0));
//    }

    @BeforeAll
    public static void setup() {}

    @AfterAll
    public static void cleanup() {}

//    @Test
//    public void should_return_employee_when_get_all_employee_exist() throws Exception{
//        // Given
//        List<Employee> givenEmployees = employeeService.getAllEmployees();
//
//        // When
//        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/employees"));
//        System.out.println(perform.toString());
//
//        // Then
//        perform.andExpect(MockMvcResultMatchers.status().isOk());
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenEmployees.get(0).getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenEmployees.get(0).getName()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].salary").value(givenEmployees.get(0).getSalary()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenEmployees.get(1).getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenEmployees.get(2).getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[3].id").value(givenEmployees.get(3).getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[4].id").value(givenEmployees.get(4).getId()));
//
//    }
//
//    @Test
//    public void should_return_employee_when_get_employee() throws Exception{
//        // Given
//        Employee targetEmployee = employeeRepository.getEmployeeById(7);
//
//        // When
//        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/employees/7"));
//        System.out.println(perform.toString());
//
//        // Then
//        perform.andExpect(MockMvcResultMatchers.status().isOk());
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(targetEmployee.getId()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(targetEmployee.getName()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(targetEmployee.getAge()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(targetEmployee.getGender()));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(targetEmployee.getSalary()));
//    }

}
