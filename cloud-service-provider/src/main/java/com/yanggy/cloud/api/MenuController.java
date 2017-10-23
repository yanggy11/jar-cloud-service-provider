package com.yanggy.cloud.api;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.param.MenuParam;
import com.yanggy.cloud.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/21 16:59
 * @Description:
 */

@RestController
@RequestMapping("/menu/**")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @PostMapping(value = "getAllMenus")
    public ResponseEntity<?> getAllMenusInpage(@RequestBody MenuParam menuParam) {
        return menuService.getAllMenus(menuParam);
    }
}
