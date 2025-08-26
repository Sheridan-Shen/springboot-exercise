package com.oocl.springboot_exercise.controller;

public class Todo {
    private int id;
    private String title;
    private String status;

    public Todo() {

    }

    public Todo(int id, String status, String title) {
        this.id = id;
        this.status = status;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
