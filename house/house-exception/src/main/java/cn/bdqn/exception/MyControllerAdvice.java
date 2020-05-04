package cn.bdqn.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



/**
 * 自定义异常处理
 */
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(value = {MyException.class})
    public ModelAndView myExceptionMessage(Exception e){
        MyException myException = null;
        if (e instanceof MyException){
            myException = (MyException)e;
        }else {
            myException = new MyException("网络连接失败!");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("error",myException.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
