package com.yanggy.cloud.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/21 17:01
 * @Description:
 */

@Data
public class MenuParam implements Serializable {
    private Long menuId;
    private int page;
    private int pageSize = 15;
}
