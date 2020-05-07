package com.xzsd.pc.store.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.store.entity.Store;
import com.xzsd.pc.store.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName StoreController
 * @description 门店管理
 * @Author ywq
 * @Date 2020-04-12
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private StoreService storeService;

    /**
     * 新增门店
     * @param store
     * @return 
     * @Author ywq
     * @Date 2020-04-12
     */
    @PostMapping("addStore")
    public AppResponse addStore(Store store,String nowRole){
        try {
            return storeService.addStore(store,nowRole);
        }catch (Exception e){
            logger.error("门店新增失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店详情
     * @param storeId
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @RequestMapping("getStore")
    public AppResponse getStore(String storeId){
        try{
            return storeService.getStore(storeId);
        }catch (Exception e){
            logger.error("查询门店详情失败",e );
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 门店列表（分页）
     * @param store
     * @param userName
     * @param role
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @PostMapping("listStores")
    public AppResponse listStores(Store store,String userName,String role){
        try{
            return storeService.listStores(store,userName,role);
        }catch (Exception e){
            logger.error("分页门店查询失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改门店
     * @param store
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @PostMapping("updateStore")
    public AppResponse updateStore(Store store,String nowRole){
        try{
            return storeService.updateStore(store,nowRole);
        }catch (Exception e){
            logger.error("修改门店信息失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除门店
     * @param storeId
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    @RequestMapping("deleteStore")
    public AppResponse deleteStore(String storeId,String nowRole){
        try {
            return storeService.deleteStore(storeId,nowRole);
        }catch (Exception e){
            logger.error("删除门店失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
