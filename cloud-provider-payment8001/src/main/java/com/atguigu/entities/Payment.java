package com.atguigu.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/24 23:36
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    /**
     * ID
     */
    private Long id;

    private String serial;
}