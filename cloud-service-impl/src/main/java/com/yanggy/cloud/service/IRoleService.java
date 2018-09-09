package com.yanggy.cloud.service;

import com.yanggy.cloud.common.utils.ResponseEntityDto;
import com.yanggy.cloud.entity.Role;
import com.yanggy.cloud.param.RoleParam;

/**
 * @author derrick.yang
 * @Date 9/5/18 14:13
 */
public interface IRoleService {
    ResponseEntityDto<?> deleteRole(RoleParam roleParam);

    ResponseEntityDto<?> getRoles(RoleParam roleParam);

    ResponseEntityDto<?> addRole(RoleParam role);

    ResponseEntityDto<?> editRole(RoleParam role);

    ResponseEntityDto<?> getAllRoles();
}
