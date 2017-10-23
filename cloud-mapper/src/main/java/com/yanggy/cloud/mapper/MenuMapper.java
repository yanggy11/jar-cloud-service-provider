package com.yanggy.cloud.mapper;

import com.yanggy.cloud.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangguiyun on 2017/10/21.
 */

@Mapper
public interface MenuMapper {

    List<Menu> getAllMenus(@Param("parentId") Long parentId);

    int countMenus();
}
