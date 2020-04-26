package com.xzsd.pc.menu.entity;

import java.util.List;

/**
 * @ClassName MenuList
 * @Deprecated 菜单实体类
 * @Author ywq
 * @Date 2020-04-14
 */
public class MenuList {
    /**
     * 菜单名称集合
     */
    List<Menu> menuList;

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
