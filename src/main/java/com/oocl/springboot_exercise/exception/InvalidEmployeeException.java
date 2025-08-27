package com.oocl.springboot_exercise.exception;

public class InvalidEmployeeException extends RuntimeException{
    // 无参构造函数
    public InvalidEmployeeException() {
        super();
    }

    // 带错误消息的构造函数
    public InvalidEmployeeException(String message) {
        super(message);
    }
}
