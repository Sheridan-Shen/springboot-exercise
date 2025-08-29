package com.oocl.springboot_exercise.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequest {
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 65, message = "Age should not be greater than 65")
    private Integer age;

    private String gender;
    private Double salary;
    private Integer companyId;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String name, Integer age, String gender, Double salary, Boolean status, Integer companyId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
