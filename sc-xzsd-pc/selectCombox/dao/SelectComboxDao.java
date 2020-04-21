package com.xzsd.pc.selectCombox.dao;

import com.xzsd.pc.selectCombox.entity.AreaInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SelectComboxDao
 * @Deprecated 下拉框管理接口
 * @Author ywq
 * @Date 2020-04-13
 */
@Mapper
public interface SelectComboxDao {
    /**
     * 查询地区下拉窗口
     * @param areaId
     * @return
     */
    List<AreaInfo> listArea(@Param("areaId") String areaId);

}
