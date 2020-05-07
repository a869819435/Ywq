package com.xzsd.pc.store.dao;

import com.xzsd.pc.store.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName StoreDao
 * @Deprecated 门店管理接口
 * @Author ywq
 * @Date 2020-04-12
 */
@Mapper
public interface StoreDao {

    /**
     * 校验店长是否存在、电话是否已是别的店所用、用户是否已经是店长、营业执照是否已被使用
     * @param store
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    int countStoreInfo(Store store);

    /**
     * 校验验证码是否存在
     * @param inviteCode
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    int countInvite(String inviteCode);

    /**
     * 新增门店
     * @param store
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    int addStore(Store store);

    /**
     * 获取门店详情
     * @param storeId
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    Store getStore(@Param("storeId") String storeId);

    /**
     * 获取所有门店信息
     * @param store
     * @param userName
     * @param role
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    List<Store> listStoreByPage(@Param("store") Store store,@Param("userName") String userName, @Param("role") String role);

    /**
     * 修改门店信息
     * @param store
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    int updateStore(Store store);

    /**
     * 获取有订单的门店
     * @param listStoreId
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    List<String> getHavingOrder(@Param("listStoreId") List<String> listStoreId);

    /**
     * 删除门店
     * @param listStoreId
     * @param updateUser
     * @return
     * @Author ywq
     * @Date 2020-04-12
     */
    int deleteStore(@Param("listStoreId") List<String> listStoreId, @Param("updateUser") String updateUser);
}

