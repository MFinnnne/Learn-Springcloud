package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/25 21:09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    /**
     * ID
     */
    private Long id;

    private String serial;
}
