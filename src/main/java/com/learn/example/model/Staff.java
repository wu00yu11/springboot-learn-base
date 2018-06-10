package com.learn.example.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author
 * @describe:
 * @create 2018-03-28 14:39
 **/
@Data
public class Staff implements Serializable{
    private static final long serialVersionUID = 6206325143162618432L;

    private String id;
    @NotEmpty(message="姓名不能为空")
    @Size(min = 2, max = 8, message = "姓名长度必须大于 2 且小于 20 字")
    private String name;
    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄大于 0")
    @Max(value = 300, message = "年龄不大于 300")
    private Integer age;
}
