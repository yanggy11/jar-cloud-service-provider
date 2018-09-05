package com.yanggy.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author derrick.yang
 * @Date 9/3/18 14:36
 */

@Data
public class ResourcesDto implements Serializable {
    private Long id;
    private String resource;
    private String resourceName;

    private List<ResourcesDto> children;
    private Long parentId;
    private boolean isEdit = false;
}
