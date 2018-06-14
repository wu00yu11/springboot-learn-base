package com.learn.example;

import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

   @ExceptionHandler(value = AppException.class)
    public Map businessExpHandler(AppException ex) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", ex.getStatus());
        map.put("msg", ex.getMessage());
        return map;
    }

    @ExceptionHandler(BindException.class)
    public Result handleValidationException(BindException ex){
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrors =  ex.getFieldErrors();
        for (FieldError error : fieldErrors) {
            sb.append(error.getField())
                    .append("=[")
                    .append(error.getRejectedValue()).append("]")
                    .append(error.getDefaultMessage()).append(";");
        }

        Result result = new Result();
        result.setStatus("400");
        result.setMsg(sb.toString());
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "500");
        map.put("msg", ex.getMessage());
        return map;
    }
}
