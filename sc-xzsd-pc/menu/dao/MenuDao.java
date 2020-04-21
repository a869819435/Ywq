package com.xzsd.pc.menu.dao;

import com.xzsd.pc.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName MenuDao
 * @Deprecated 菜单接口类
 * @Author ywq
 * @Date 2020-04-14
 */
@Mapper
public interface MenuDao {
    /**
     * 查询菜单名列表接口
     * @return
     */
    List<Menu> listMenu();

    /**
     * 根据角色查询首页菜单列表接口
     * @param role
     * @return
     */
    List<Menu> listMenuHome(@Param("role") String role);

    /**
     * 统计菜单名称数量
     * @param menu
     * @return
     */
    int countMenuName(Menu menu);

    /**
     * 新增菜单接口
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    /**
     * 查询菜单详情接口
     * @param menuId
     * @return
     */
    Menu getMenu(@Param("menuId") String menuId);

    /**
     * 修改菜单接口
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 删除菜单接口
     * @param menuId
     * @param updateUser
     * @return
     */
    int deleteMenu(@Param("menuId") String menuId, @Param("updateUser") String updateUser);
}
