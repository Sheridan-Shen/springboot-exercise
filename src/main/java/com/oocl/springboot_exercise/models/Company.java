package com.oocl.springboot_exercise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
//    private List<Employee> employees = new ArrayList<>();

    // 构造函数
    public Company() {}

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(int id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
//        this.employees = employees;
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

//    public List<Employee> getEmployees() {
//        return employees;
//    }

//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
}
