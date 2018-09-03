package com.yanggy.cloud.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/21 10:54
 * @Description:
 */

@Data
public class RoleParam implements Serializable {
    private Long roleId;
    private String roleName;
    private int page = 1;
    private int pageSize = 15;
    private List<Long> roleIds;
    private Long parentId;
}
