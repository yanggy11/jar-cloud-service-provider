package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.common.enums.ErrorCode;
import com.yanggy.cloud.common.utils.ResponseEntityBuilder;
import com.yanggy.cloud.common.utils.ResponseEntityDto;
import com.yanggy.cloud.dto.RoleDto;
import com.yanggy.cloud.entity.Resources;
import com.yanggy.cloud.entity.Role;
import com.yanggy.cloud.mapper.ResourcesMapper;
import com.yanggy.cloud.mapper.RoleMapper;
import com.yanggy.cloud.param.RoleParam;
import com.yanggy.cloud.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author derrick.yang
 * @Date 9/5/18 14:13
 */

@Service
public class RoleServiceImpl implements IRoleService {

    private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public ResponseEntityDto<?> deleteRole(RoleParam roleParam) {
        logger.info("删除角色方法开始");
        ResponseEntityDto<?> res = null;

        try {
            roleMapper.deleteRole(roleParam.getRoleIds());

            res = ResponseEntityBuilder.buildNormalResponseEntity();
        } catch (Exception e) {
            logger.info("删除角色发生未知异常", e);

            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        logger.info("删除角色方法结束");

        return res;
    }

    @Override
    public ResponseEntityDto<?> getRoles(RoleParam roleParam) {
        logger.info("查询角色方法开始");
        ResponseEntityDto<?> res = null;

        try {
            List<Role> roles = roleMapper.getRolesByCriteria(roleParam);

            List<RoleDto> roleDtos = roles.stream().parallel().map(role -> {
                RoleDto roleDto = new RoleDto();

                BeanUtils.copyProperties(role, roleDto);

                //查询该角色下的资源
                List<Resources> resources = resourcesMapper.getResourcesByRole(role.getId());
                roleDto.setResources(resources);

                return roleDto;
            }).collect(Collectors.toList());

            res = ResponseEntityBuilder.buildNormalResponseEntity(roleDtos);
        } catch (Exception e) {
            logger.info("查询角色发生未知异常", e);

            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        logger.info("查询角色方法结束");

        return res;
    }

    @Override
    public ResponseEntityDto<?> addRole(Role role) {
        ResponseEntityDto<?> res = null;

        try {
            roleMapper.addRole(role);

            res = ResponseEntityBuilder.buildNormalResponseEntity();
        } catch (Exception e) {
            logger.info("新增角色发生错误", e.getMessage());
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }

    @Override
    public ResponseEntityDto<?> editRole(Role role) {
        ResponseEntityDto<?> res = null;

        try {
            roleMapper.editRole(role);
            res = ResponseEntityBuilder.buildNormalResponseEntity();
        } catch (Exception e) {

            e.printStackTrace();
            logger.info("编辑角色发生错误", e.getMessage());
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }
}
