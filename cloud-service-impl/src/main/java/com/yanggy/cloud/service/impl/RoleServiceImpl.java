package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.Role;
import com.yanggy.cloud.mapper.RoleMapper;
import com.yanggy.cloud.param.RoleParam;
import com.yanggy.cloud.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/21 10:56
 * @Description:
 */

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Page<?> getAllRolesInPage(RoleParam roleParam) {
        Page page = new Page();
        page.setPageSize(roleParam.getPageSize());
        page.setPage(roleParam.getPage());
        int count = roleMapper.countRoutes();
        page.setTotalRecord(count);
        page.setTotalPage(count % roleParam.getPageSize() == 0 ? count / roleParam.getPageSize() : count / roleParam.getPageSize() + 1);
        page.setData(roleMapper.getAllRolesInpage(roleParam.getPageSize(), (roleParam.getPage() -1) * roleParam.getPageSize()));

        return page;
    }

    @Override
    public ResponseEntity<?> deleteRole(RoleParam roleParam) {
        roleMapper.deleteUserRole(roleParam.getRoleId());
        return new ResponseEntity<>(roleMapper.deleteRole(roleParam.getRoleId()));
    }

    @Override
    public ResponseEntity<?> addRole(Role role) {
        return new ResponseEntity<>(roleMapper.addRole(role));
    }

    @Override
    public ResponseEntity<?> editRole(Role role) {
        return new ResponseEntity<>(roleMapper.editRole(role));
    }

    @Override
    public ResponseEntity<?> getRoleById(RoleParam roleParam) {
        return new ResponseEntity<>(roleMapper.getRoleById(roleParam.getRoleId()));
    }
}
