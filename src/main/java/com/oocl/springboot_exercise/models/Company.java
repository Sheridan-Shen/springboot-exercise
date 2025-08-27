package com.oocl.springboot_exercise.models;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Integer id;
    private String name;
    private List<Employee> employees = new ArrayList<>();

    // 构造函数
    public Company() {}

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(int id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
