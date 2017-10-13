package com.yanggy.cloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yangguiyun on 2017/9/26.
 */

@Data
public class UserRole implements Serializable {
    private long id;
    private long userId;
    private long roleId;
}
