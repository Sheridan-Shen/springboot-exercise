package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> getEmployeesByGender(String gender);

    Employee getEmployeesById(Integer id);

    // 方法1：返回 Page 对象（推荐 - 包含总数信息）
    Page<Employee> findAll(Pageable pageable);

    // 方法2：自定义查询 + 分页
    Page<Employee> findByDepartment(String department, Pageable pageable);

    // 方法3：如果只需要列表，可以用 List
    List<Employee> findAllBy(Pageable pageable);
}
