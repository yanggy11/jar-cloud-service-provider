package com.yanggy.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author derrick.yang
 * @Date 9/3/18 14:36
 */

@Data
public class RoleDto implements Serializable {
    private Long id;
    private String role;
    private String label;

    private List<RoleDto> children;
    private Long parentId;
}
