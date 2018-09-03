package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.dto.RoleDto;
import com.yanggy.cloud.entity.Role;
import com.yanggy.cloud.mapper.RoleMapper;
import com.yanggy.cloud.param.RoleParam;
import com.yanggy.cloud.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        int count = roleMapper.countRoutes(roleParam);
        page.setTotalRecord(count);
        page.setTotalPage(count % roleParam.getPageSize() == 0 ? count / roleParam.getPageSize() : count / roleParam.getPageSize() + 1);
        page.setData(roleMapper.getAllRolesInpage(roleParam.getPageSize(), (roleParam.getPage() -1) * roleParam.getPageSize()));

        return page;
    }

    @Override
    public ResponseEntity<?> deleteRole(RoleParam roleParam) {
        //批量删除
        roleMapper.deleteRoles(roleParam.getRoleIds());
        return new ResponseEntity<>();
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

    @Override
    public ResponseEntity<?> getRoleTrees() {
        List<Role> roles = roleMapper.getAllRoles();

        List<RoleDto> trees = roles.stream().filter(role -> 0 == role.getParentId()).map((role) -> {
            RoleDto tree = new RoleDto();
            BeanUtils.copyProperties(role, tree);
            tree.setLabel(role.getRoleName());

            List<RoleDto>children = this.menuList(role.getId(), roles);
            tree.setChildren(null == children ? null : children.size() <= 0 ? null : children);

            return tree;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(trees);
    }

    private List<RoleDto>menuList(Long id, List<Role> roles) {
        List<RoleDto> children = new ArrayList<>();

        children = roles.stream().filter(role -> id.equals(role.getParentId())).map(role -> {
            RoleDto tree = new RoleDto();
            BeanUtils.copyProperties(role, tree);
            tree.setLabel(role.getRoleName());
            tree.setChildren(menuList(role.getId(), roles));
            return tree;
        }).collect(Collectors.toList());

        return children;
    }
}
