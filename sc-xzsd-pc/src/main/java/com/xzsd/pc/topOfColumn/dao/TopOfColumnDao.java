package com.xzsd.pc.topOfColumn.dao;

import com.xzsd.pc.topOfColumn.entity.TopOfColumn;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName TopOfColumnDao
 * @Deprecated 顶部栏接口类
 * @Author ywq
 * @Date 2020-04-14
 */
public interface TopOfColumnDao {
    /**
     * 获取顶部栏信息接口
     * @param userId
     * @return
     */
    TopOfColumn getTopOfColumn(@Param("userId") String userId);
}
