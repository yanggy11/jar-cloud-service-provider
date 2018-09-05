package com.yanggy.cloud.api;

import com.yanggy.cloud.common.utils.ResponseEntityDto;
import com.yanggy.cloud.param.RoleParam;
import com.yanggy.cloud.service.IRoleService;
import com.yanggy.cloud.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author derrick.yang
 * @Date 9/5/18 13:20
 */

@RestController
@RequestMapping(value = "/role/**")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @DeleteMapping(value = "deleteRole")
    public ResponseEntityDto<?> deleteRole(@RequestBody RoleParam roleParam) {

        return roleService.deleteRole(roleParam);
    }

    @PostMapping(value = "getRolesByCriteria")
    public ResponseEntityDto<?> getRolesByCriteria(@RequestBody RoleParam roleParam) {
        
        return roleService.getRoles(roleParam);
    }
}
