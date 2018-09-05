package com.yanggy.cloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author derrick.yang
 * @Date 9/4/18 14:42
 */

@Data
public class RoleResources implements Serializable{
    private Long id;
    private Long roleId;
    private Long resourcesId;
}
