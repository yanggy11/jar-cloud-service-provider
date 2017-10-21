package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.mapper.MenuMapper;
import com.yanggy.cloud.param.MenuParam;
import com.yanggy.cloud.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Page<?> getAllMenusInpage(MenuParam menuParam) {
        Page page = new Page();
        page.setPageSize(menuParam.getPageSize());
        page.setPage(menuParam.getPage());
        int count = menuMapper.countMenus();
        page.setTotalRecord(count);
        page.setTotalPage(count % menuParam.getPageSize() == 0 ? count / menuParam.getPageSize() : count / menuParam.getPageSize() + 1);
        page.setData(menuMapper.getAllMenusInpage(menuParam.getPageSize(), (menuParam.getPage() -1) * menuParam.getPageSize()));

        return page;
    }
}
