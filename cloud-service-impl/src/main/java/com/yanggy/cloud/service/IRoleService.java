package com.yanggy.cloud.service;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.Role;
import com.yanggy.cloud.param.RoleParam;

/**
 * Created by yangguiyun on 2017/10/21.
 */
public interface IRoleService {
    Page<?> getAllRolesInPage(RoleParam roleParam);

    ResponseEntity<?> deleteRole(RoleParam roleParam);

    ResponseEntity<?> addRole(Role role);

    ResponseEntity<?> editRole(Role role);

    ResponseEntity<?> getRoleById(RoleParam roleParam);
}
