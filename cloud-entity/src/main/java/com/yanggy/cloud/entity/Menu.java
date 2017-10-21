package com.yanggy.cloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/21 16:54
 * @Description:
 */

@Data
public class Menu implements Serializable {
    private Long id;
    private String menu;
    private String menuName;
    private String icon;
    private Long parentId;
}
