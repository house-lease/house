package cn.bdqn.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 自定义异常类
 */
public class MyException extends Exception {

    private String message;

    //带参构造函数
    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
