package com.oocl.springboot_exercise.controller.dto;

import com.oocl.springboot_exercise.models.Company;

public class EmployeeResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Company company;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Integer id, String name, Integer age, String gender, Company company) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.company = company;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
