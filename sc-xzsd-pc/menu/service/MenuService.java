package com.xzsd.pc.menu.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.menu.dao.MenuDao;
import com.xzsd.pc.menu.entity.Menu;
import com.xzsd.pc.menu.entity.MenuList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MenuService
 * @Deprecated 菜单实现类
 * @Author ywq
 * @Date 2020-04-14
 */
@Service
public class MenuService {
    /**
     * 默认是菜单
     */
    public static String IS_MENU = "1";
    /**
     * 默认父级编号
     */
    public static String PARENT_MENU = "0";

    @Resource
    private MenuDao menuDao;

    /**
     * 查询菜单名列表接口
     * @return
     */
    public AppResponse listMenu(){
        MenuList menuList = new MenuList();
        menuList.setMenuList(menuDao.listMenu() );
        return AppResponse.success("查询菜单名名列表成功！",menuList);
    }

    /**
     * 根据角色查询首页菜单列表接口
     * @param role
     * @return
     */
    public AppResponse listMenuHome(String role){
        MenuList menuList = new MenuList();
        menuList.setMenuList(menuDao.listMenuHome(role));
        return AppResponse.success("查询首页菜单列表成功！",menuList);
    }

    /**
     * 新增菜单接口
     * @param menu
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addMenu(Menu menu){
        //默认是菜单
        menu.setIsMenu(IS_MENU);
        //默认父级编号为0
        menu.setParentMenu(PARENT_MENU);
        int countMenuName = menuDao.countMenuName(menu);
        if(countMenuName != 0){
            return AppResponse.versionError("该菜单名称已存在！");
        }
        //生成菜单编号
        menu.setMenuId("me" + StringUtil.getCommonCode(2));
        //获取当前登录人信息
        menu.setCreateUser(SecurityUtils.getCurrentUserId());
        int count = menuDao.addMenu(menu);
        if(0 == count){
            return AppResponse.versionError("添加菜单失败！");
        }
        return AppResponse.success("添加菜单成功！");
    }

    /**
     * 查询菜单详情接口
     * @param menuId
     * @return
     */
    public AppResponse getMenu(String menuId){
        Menu menu = menuDao.getMenu(menuId);
        menu.setMenuId(menuId);
        return AppResponse.success("查询菜单详情成功！",menu);
    }

    /**
     * 修改菜单接口
     * @param menu
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateMenu(Menu menu){
        //默认是菜单
        menu.setIsMenu(IS_MENU);
        //默认父级编号为0
        menu.setParentMenu(PARENT_MENU);
        int countMenuName = menuDao.countMenuName(menu);
        if(countMenuName != 0){
            return AppResponse.versionError("该菜单名称已存在！");
        }
        //获取当前登录人信息
        menu.setUpdateUser(SecurityUtils.getCurrentUserId());
        //默认是菜单
        menu.setIsMenu(IS_MENU);
        //默认父级编号为0
        menu.setParentMenu(PARENT_MENU);
        int count = menuDao.updateMenu(menu);
        if(0 == count){
            return AppResponse.versionError("修改菜单失败");
        }
        return AppResponse.success("修改菜单成功");
    }

    /**
     * 删除菜单接口
     * @param menuId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteMenu(String menuId){
        //获取当前登录人信息
        String updateUser = SecurityUtils.getCurrentUserId();
        int count = menuDao.deleteMenu(menuId,updateUser);
        if(0 == count){
            return AppResponse.bizError("删除菜单失败！");
        }
        return AppResponse.success("删除菜单成功！");
    }
}
