package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.dto.MenuDto;
import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.Menu;
import com.yanggy.cloud.mapper.MenuMapper;
import com.yanggy.cloud.param.MenuParam;
import com.yanggy.cloud.service.IMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/21 17:02
 * @Description:
 */

@Service("menuService")
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public ResponseEntity<?> getAllMenus(MenuParam menuParam) {
        List<Menu> menus = menuMapper.getAllMenus(-1l);
        List<MenuDto> menuDtos = menus.stream().map(menu -> {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menu, menuDto);
            menuDto.setMenuId(menu.getId());
            menuDto.setChildrens(menuMapper.getAllMenus(menu.getId()));
            return menuDto;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(menuDtos);
    }
}
