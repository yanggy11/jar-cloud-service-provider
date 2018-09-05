package com.yanggy.cloud.mapper;

import com.yanggy.cloud.dto.ResourcesDto;
import com.yanggy.cloud.entity.Role;
import com.yanggy.cloud.param.RoleParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author derrick.yang
 * @Date 9/4/18 14:43
 */

@Mapper
public interface RoleMapper {
    int deleteRole(List<Long> list);

    List<Role> getRolesByCriteria(RoleParam roleParam);

    List<ResourcesDto> getResourcesByRole(@Param("roleId") Long roleId);
}
