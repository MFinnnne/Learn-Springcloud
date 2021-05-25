package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/24 23:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    /**
     * 泛型，对应类型的json数据
     */
    private T data;

    /**
     * 自定义两个参数的构造方法
     */
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
