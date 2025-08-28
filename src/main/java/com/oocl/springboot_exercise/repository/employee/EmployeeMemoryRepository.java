//package com.oocl.springboot_exercise.repository;
//
//import com.oocl.springboot_exercise.models.Employee;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Repository
//public class EmployeeMemoryRepository implements EmployeeRepository {
//    private final Map<Integer, Employee> employeeDb = new HashMap<>(Map.of(
//            1, new Employee(1, "John Smith", 32, "MALE", 5000.0),
//            2, new Employee(2, "Jane Johnson", 28, "FEMALE", 6000.0),
//            3, new Employee(3, "David Williams", 35, "MALE", 5500.0),
//            4, new Employee(4, "Emily Brown", 23, "FEMALE", 4500.0),
//            5, new Employee(5, "Michael Jones", 40, "MALE", 7000.0)));
//
//    private AtomicInteger employeeIdGenerator = new AtomicInteger(6);
//
//    @Override
//    public List<Employee> getEmployees(){
//        return new ArrayList<>(employeeDb.values());
//    }
//
//    @Override
//    public Employee getEmployeeById(Integer id){
//        return employeeDb.get(id);
//    }
//
//    @Override
//    public List<Employee> getEmployeeByGender(String gender) {
//        return List.of();
//    }
//
//    @Override
//    public Employee addEmployee(Employee employee){
//        Integer newId = employeeIdGenerator.incrementAndGet();
//        employee.setId(newId);
//        employeeDb.put(newId, employee);
//        return employee;
//    }
//
//    @Override
//    public void updateEmployee(Integer id, Employee employee){
//        employeeDb.put(id, employee);
//    }
//
//    @Override
//    public Employee removeEmployee(Integer id){
//        return employeeDb.remove(id);
//    }
//}
