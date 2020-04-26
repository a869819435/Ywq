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
     * 统计用户编号
     * @param userId
     * @return
     */
    int countUserId(@Param("userId") String userId);

    /**
     * 统计电话数量
     * @param phone
     * @param storeId
     * @return
     */
    int countPhone(@Param("phone") String phone, @Param("storeId") String storeId);

    /**
     * 统计门店长编号数量
     * @param userId
     * @param storeId
     * @return
     */
    int countManagerId(@Param("userId") String userId, @Param("storeId") String storeId);

    /**
     * 统计门店营业执照编号数量
     * @param businessCode
     * @param storeId
     * @return
     */
    int countBusinessCode(@Param("businessCode") String businessCode, @Param("storeId") String storeId);

    /**
     * 校验店长是否存在、电话是否已是别的店所用、用户是否已经是店长、营业执照是否已被使用
     * @param store
     * @return
     */
    int countStoreInfo(Store store);

    /**
     * 新增门店
     * @param store
     * @return
     */
    int addStore(Store store);

    /**
     * 获取门店详情
     * @param storeId
     * @return
     */
    Store getStore(@Param("storeId") String storeId);

    /**
     * 获取所有门店信息
     * @param store
     * @param userName
     * @param role
     * @return
     */
    List<Store> listStoreByPage(@Param("store") Store store,@Param("userName") String userName, @Param("role") String role);

    /**
     * 修改门店信息
     * @param store
     * @return
     */
    int updateStore(Store store);

    /**
     * 获取有订单的门店
     * @param listStoreId
     * @return
     */
    List<String> getHavingOrder(@Param("listStoreId") List<String> listStoreId);

    /**
     * 删除门店
     * @param listStoreId
     * @param updateUser
     * @return
     */
    int deleteStore(@Param("listStoreId") List<String> listStoreId, @Param("updateUser") String updateUser);

}

