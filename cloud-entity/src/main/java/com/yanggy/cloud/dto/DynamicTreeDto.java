package com.yanggy.cloud.dto;

import com.yanggy.cloud.entity.DynamicTree;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/18 11:02
 * @Description:
 */
@Data
public class DynamicTreeDto implements Serializable {
    private Long id;
    private String name;
    private List<DynamicTreeBaseDto> childrens;
}
