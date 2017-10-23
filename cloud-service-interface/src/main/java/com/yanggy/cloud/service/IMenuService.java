package com.yanggy.cloud.service;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.param.MenuParam;

/**
 * Created by yangguiyun on 2017/10/21.
 */
public interface IMenuService {
    ResponseEntity<?> getAllMenus(MenuParam menuParam);
}
