package com.yanggy.cloud.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/17 12:57
 * @Description:
 */

@Data
public class UserParam implements Serializable {
    private String name;
    private String password;
}
