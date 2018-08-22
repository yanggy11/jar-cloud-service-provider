package com.yanggy.cloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yangguiyun on 2017/9/26.
 */

@Data
public class Role extends BaseEntity {
    private static final long serialVersionUID = -6995148327145747896L;
    private Long parentId;
    private String role;
    private String roleName;
}
