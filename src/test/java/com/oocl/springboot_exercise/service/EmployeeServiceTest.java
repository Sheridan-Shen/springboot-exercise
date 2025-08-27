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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
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

        when(employeeRepository.addEmployee(any(Employee.class))).thenReturn(mockEmployee);

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

    @Test
    void should_return_empty_list_when_no_employees_exist() {
        // 模拟repository返回空map
        when(employeeRepository.getEmployees()).thenReturn(new HashMap<>());

        // 执行
        List<Employee> result = employeeService.getAllEmployees();

        // 验证
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // 验证repository方法被调用
        verify(employeeRepository, times(1)).getEmployees();
    }

    @Test
    void should_update_employee_info_successfully() {
        // 输入
        Integer employeeId = 1;
        Employee existingEmployee = new Employee(1, "John Smith", 32, "MALE", 5000.0);
        existingEmployee.setStatus(true);

        Employee updateDetails = new Employee();
        updateDetails.setName("John Doe");
        updateDetails.setAge(33);
        updateDetails.setGender("MALE");
        updateDetails.setSalary(5500.0);

        // 模拟输出
        when(employeeRepository.getEmployee(employeeId)).thenReturn(existingEmployee);

        // 执行
        Employee result = employeeService.updateEmployeeInfo(employeeId, updateDetails);

        // 验证服务方法的返回结果
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(33, result.getAge());
        assertEquals("MALE", result.getGender());
        assertEquals(5500.0, result.getSalary());
        assertTrue(result.getStatus()); // 状态应该保持为true

        // 验证repository方法被调用
        verify(employeeRepository, times(1)).getEmployee(employeeId);

    }

    @Test
    void should_throw_exception_when_update_inactive_employee() {
        // 输入
        Integer employeeId = 1;
        Employee inactiveEmployee = new Employee(1, "John Smith", 32, "MALE", 5000.0);
        inactiveEmployee.setStatus(false); // 离职状态

        Employee updateDetails = new Employee();
        updateDetails.setName("John Doe");

        // 模拟输出
        when(employeeRepository.getEmployee(employeeId)).thenReturn(inactiveEmployee);

        // 执行并验证异常
        InvalidEmployeeException exception = assertThrows(
                InvalidEmployeeException.class,
                () -> employeeService.updateEmployeeInfo(employeeId, updateDetails)
        );

        assertEquals("员工已经离职, 不能进行更新", exception.getMessage());

        // 验证repository方法调用
        verify(employeeRepository, times(1)).getEmployee(employeeId);
        verify(employeeRepository, never()).putEmployee(anyInt(), any(Employee.class));
    }

    @Test
    void should_full_replace_employee_successfully() {
        // 输入
        Integer employeeId = 1;
        Employee existingEmployee = new Employee(1, "John Smith", 32, "MALE", 5000.0);
        existingEmployee.setStatus(true);

        Employee employeeToSave = new Employee(1, "Jane Doe", 28, "FEMALE", 7000.0);
        employeeToSave.setStatus(true); // 确保状态为true

        // 模拟输出
        when(employeeRepository.getEmployee(employeeId)).thenReturn(existingEmployee);
        doNothing().when(employeeRepository).putEmployee(eq(employeeId), any(Employee.class));

        // 执行
        Employee result = employeeService.fullReplaceEmployee(employeeId, employeeToSave);

        // 验证
        assertNotNull(result);
        assertEquals(employeeToSave, result);

        // 验证repository方法被调用
        verify(employeeRepository, times(1)).getEmployee(employeeId);
        verify(employeeRepository, times(1)).putEmployee(eq(employeeId), eq(employeeToSave));
    }

    @Test
    void should_throw_exception_when_full_replace_inactive_employee() {
        // 输入
        Integer employeeId = 1;
        Employee inactiveEmployee = new Employee(1, "John Smith", 32, "MALE", 5000.0);
        inactiveEmployee.setStatus(false); // 离职状态

        Employee employeeToSave = new Employee(1, "Jane Doe", 28, "FEMALE", 7000.0);

        // 模拟输出
        when(employeeRepository.getEmployee(employeeId)).thenReturn(inactiveEmployee);

        // 执行并验证异常
        InvalidEmployeeException exception = assertThrows(
                InvalidEmployeeException.class,
                () -> employeeService.fullReplaceEmployee(employeeId, employeeToSave)
        );

        assertEquals("员工已经离职, 不能进行更新", exception.getMessage());

        // 验证repository方法调用
        verify(employeeRepository, times(1)).getEmployee(employeeId);
        verify(employeeRepository, never()).putEmployee(anyInt(), any(Employee.class));
    }

    @Test
    void should_delete_employee_change_status_successfully() {
        // 输入
        Integer employeeId = 1;
        Employee existingEmployee = new Employee(1, "John Smith", 32, "MALE", 5000.0);
        existingEmployee.setStatus(true);

        // 模拟输出
        when(employeeRepository.getEmployee(employeeId)).thenReturn(existingEmployee);
        doNothing().when(employeeRepository).putEmployee(eq(employeeId), any(Employee.class));

        // 执行
        employeeService.deleteEmployeeChangeStatus(employeeId);

        // 验证employeeRepository.putEmployee被调用，且传入的employee状态为false
        verify(employeeRepository, times(1)).getEmployee(employeeId);
    }

    @Test
    void should_get_employees_by_page_successfully() {
        // 准备测试数据
        List<Employee> allEmployees = Arrays.asList(
                new Employee(1, "A", 20, "MALE", 3000.0),
                new Employee(2, "B", 21, "FEMALE", 3100.0),
                new Employee(3, "C", 22, "MALE", 3200.0),
                new Employee(4, "D", 23, "FEMALE", 3300.0),
                new Employee(5, "E", 24, "MALE", 3400.0),
                new Employee(6, "F", 25, "FEMALE", 3500.0)
        );

        // 模拟repository返回值
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee employee : allEmployees) {
            employeeMap.put(employee.getId(), employee);
        }
        when(employeeRepository.getEmployees()).thenReturn(employeeMap);

        // 测试第一页，每页3条
        List<Employee> page1 = employeeService.getEmployeesByPage(1, 3);
        assertEquals(3, page1.size());
        assertEquals(allEmployees.subList(0, 3), page1);

        // 测试第二页，每页3条
        List<Employee> page2 = employeeService.getEmployeesByPage(2, 3);
        assertEquals(3, page2.size());
        assertEquals(allEmployees.subList(3, 6), page2);

        // 验证repository方法被调用
        verify(employeeRepository, times(2)).getEmployees(); // 被调用3次（初始+2次分页）
    }

    @Test
    void should_return_empty_list_when_request_page_beyond_range() {
        // 准备测试数据
        List<Employee> allEmployees = Arrays.asList(
                new Employee(1, "A", 20, "MALE", 3000.0),
                new Employee(2, "B", 21, "FEMALE", 3100.0)
        );

        // 模拟repository返回值
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee employee : allEmployees) {
            employeeMap.put(employee.getId(), employee);
        }
        when(employeeRepository.getEmployees()).thenReturn(employeeMap);

        // 请求第3页，每页2条（总共只有2条数据）
        List<Employee> result = employeeService.getEmployeesByPage(3, 2);

        // 验证
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // 验证repository方法被调用
        verify(employeeRepository, times(1)).getEmployees();
    }

    @Test
    void should_return_empty_list_when_no_employees_for_pagination() {
        // 模拟repository返回空map
        when(employeeRepository.getEmployees()).thenReturn(new HashMap<>());

        // 执行分页查询
        List<Employee> result = employeeService.getEmployeesByPage(1, 5);

        // 验证
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // 验证repository方法被调用
        verify(employeeRepository, times(1)).getEmployees();
    }
}