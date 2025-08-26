package com.oocl.springboot_exercise.dao;

public class Company {
    private Integer id;
    private String name;

    // 构造函数
    public Company() {}

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
