package com.xzsd.pc.menu.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.menu.entity.Menu;
import com.xzsd.pc.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MenuController
 * @Deprecated 菜单管理
 * @Author ywq
 * @Date 2020-04-14
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private MenuService menuService;

    /**
     * 查询菜单名列表
     * @return
     * @Author ywq
     * @Date 2020-04-14
     */
    @PostMapping("listMenu")
    public AppResponse listMenu(){
        try{
            return menuService.listMenu();
        }catch (Exception e){
            logger.error("查询菜单名列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 根据角色查询首页菜单列表
     * @param role
     * @return
     * @Author ywq
     * @Date 2020-04-14
     */
    @PostMapping("listMenuHome")
    public AppResponse listMenuHome(String role){
        try{
            return menuService.listMenuHome(role);
        }catch (Exception e){
            logger.error("根据角色查询首页菜单列表失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     * @Author ywq
     * @Date 2020-04-14
     */
    @PostMapping("addMenu")
    public AppResponse addMenu(Menu menu){
        try{
            return menuService.addMenu(menu);
        }catch (Exception e){
            logger.error("新增菜单失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询菜单详情
     * @param menuId
     * @return
     * @Author ywq
     * @Date 2020-04-14
     */
    @PostMapping("getMenu")
    public AppResponse getMenu(String menuId){
        try{
            return menuService.getMenu(menuId);
        }catch (Exception e){
            logger.error("查询菜单详情失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     * @Author ywq
     * @Date 2020-04-14
     */
    @PostMapping("updateMenu")
    public AppResponse updateMenu(Menu menu){
        try{
            return menuService.updateMenu(menu);
        }catch (Exception e){
            logger.error("修改菜单失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     * @Author ywq
     * @Date 2020-04-14
     */
    @PostMapping("deleteMenu")
    public AppResponse deleteMenu(String menuId){
        try{
            return menuService.deleteMenu(menuId);
        }catch (Exception e){
            logger.error("删除菜单失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
