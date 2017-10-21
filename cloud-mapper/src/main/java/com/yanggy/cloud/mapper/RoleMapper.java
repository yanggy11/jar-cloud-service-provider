package com.yanggy.cloud.mapper;

import com.yanggy.cloud.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangguiyun on 2017/10/21.
 */

@Mapper
public interface RoleMapper {
    List<String> getAllRolesInpage(@Param("size") int size, @Param("offset") int offset);

    int countRoutes();

    int deleteRole(@Param("roleId") Long roleId);

    int addRole(Role role);

    int editRole(Role role);

    int deleteUserRole(@Param("roleId") Long roleId);

    Role getRoleById(@Param("roleId") Long roleId);
}
