package com.yanggy.cloud.api;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.Role;
import com.yanggy.cloud.param.RoleParam;
import com.yanggy.cloud.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/21 10:52
 * @Description:
 */

@RestController
@RequestMapping("/roles/**")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("getAllRolesInPage")
    public Page<?> getAllRolesInPage(@RequestBody RoleParam roleParam) {
        return roleService.getAllRolesInPage(roleParam);
    }
    @PostMapping("deleteRole")
    public ResponseEntity<?> deleteRole(@RequestBody RoleParam roleParam) {
        return roleService.deleteRole(roleParam);
    }
    @PostMapping("addRole")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }
    @PostMapping("editRole")
    public ResponseEntity<?> editRole(@RequestBody Role role) {
        return roleService.editRole(role);
    }
    @PostMapping("getRoleById")
    public ResponseEntity<?> getRoleById(@RequestBody RoleParam roleParam) {
        return roleService.getRoleById(roleParam);
    }

    @PostMapping(value="getRoleTrees")
    public ResponseEntity<?> getRoleTrees() {
        return roleService.getRoleTrees();
    }
}
