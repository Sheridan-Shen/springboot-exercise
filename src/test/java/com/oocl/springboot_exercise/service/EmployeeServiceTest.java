package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.exception.InvalidEmployeeException;
import com.oocl.springboot_exercise.models.Employee;
import com.oocl.springboot_exercise.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        when(employeeRepository.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

        // 模拟输出的结果
        Employee outputEmployee = employeeService.addEmployee(inputEmployee);

        assertEquals(outputEmployee.getName(),inputEmployee.getName());
        assertEquals(outputEmployee.getAge(),inputEmployee.getAge());
        assertEquals(outputEmployee.getGender(),inputEmployee.getGender());
        assertEquals(outputEmployee.getSalary(),inputEmployee.getSalary());
        assertNotNull(outputEmployee.getStatus());
    }

    @Test
    void should_throw_exception_when_age_16(){
        // 输入
        Employee inputEmployee = new Employee("oocl",16,"MALE",30000, true);

        InvalidEmployeeException exception = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(inputEmployee));
        assertEquals("年龄不在 18 到 65 岁之间的员工不能被创建",exception.getMessage());
    }

    @Test
    void should_throw_exception_when_age_66(){
        // 输入
        Employee inputEmployee = new Employee("oocl",66,"MALE",30000, true);

        InvalidEmployeeException exception = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(inputEmployee));
        assertEquals("年龄不在 18 到 65 岁之间的员工不能被创建",exception.getMessage());
    }

    @Test
    void should_throw_exception_when_age_30_salary_10000(){
        // 输入
        Employee inputEmployee = new Employee("oocl",30,"MALE",10000, true);

        InvalidEmployeeException exception = assertThrows(InvalidEmployeeException.class, () -> employeeService.addEmployee(inputEmployee));
        assertEquals("年龄大于等于 30 岁且薪资低于 20000 的员工不能被创建",exception.getMessage());
    }

    @Test
    void should_delete_employee_by_id_successfully(){
        // 输入
        Employee inputEmployee = new Employee(1,"oocl",30,"MALE",10000, true);
        when(employeeRepository.removeEmployee(1)).thenReturn(inputEmployee);
    }

    @Test
    void should_return_null_when_get_employee_by_nonexistent_id() {
        // 输入
        Integer nonExistentId = 999;

        // 模拟输出
        when(employeeRepository.getEmployee(nonExistentId)).thenReturn(null);

        // 执行
        Employee result = employeeService.getEmployeeById(nonExistentId);

        // 验证
        assertNull(result);

        // 验证repository方法被调用
        verify(employeeRepository, times(1)).getEmployee(nonExistentId);
    }

    @Test
    void should_get_all_employees_successfully() {
        // 准备测试数据
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1, "John Smith", 32, "MALE", 5000.0),
                new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
                new Employee(3, "David Williams", 35, "MALE", 5500.0)
        );

        // 模拟repository返回值
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee employee : mockEmployees) {
            employeeMap.put(employee.getId(), employee);
        }
        when(employeeRepository.getEmployees()).thenReturn(employeeMap);

        // 执行
        List<Employee> result = employeeService.getAllEmployees();

        // 验证
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(mockEmployees));

        // 验证repository方法被调用
        verify(employeeRepository, times(1)).getEmployees();
    }
}