package com.learn.example;

import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

/*    @ExceptionHandler(value = AppException.class)
    public ModelAndView businessExpHandler(AppException ex) {
        ModelAndView view = new ModelAndView();
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", ex.getStatus());
        map.put("msg", ex.getMessage());
        view.addObject("result",map);
        view.setViewName(ex.getPath());
        return view;
    }*/
    @ExceptionHandler(value = AppException.class)
    public void businessExpHandler(AppException ex,HttpServletResponse response) {

    }

    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "500");
        map.put("msg", ex.getMessage());
        return map;
    }
}
