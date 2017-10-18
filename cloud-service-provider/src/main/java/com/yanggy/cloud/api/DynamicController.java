package com.yanggy.cloud.api;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.service.IDynamicTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/18 12:54
 * @Description:
 */

@RestController
public class DynamicController {

    @Autowired
    private IDynamicTreeService dynamicTreeService;
    @PostMapping(value="/trees")
    public ResponseEntity<?> getTrees() {
        return dynamicTreeService.getAllTrees();
    }
}
