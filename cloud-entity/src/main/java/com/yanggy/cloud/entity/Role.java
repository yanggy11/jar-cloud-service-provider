package com.yanggy.cloud.entity;

import lombok.Data;

/**
 * @author derrick.yang
 * @Date 9/4/18 14:40
 */

@Data
public class Role extends BaseEntity {
    private String role;
    private String roleName;

    private String remark;
}
