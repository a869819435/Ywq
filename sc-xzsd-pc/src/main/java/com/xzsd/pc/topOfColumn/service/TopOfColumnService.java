package com.xzsd.pc.topOfColumn.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.topOfColumn.dao.TopOfColumnDao;
import com.xzsd.pc.topOfColumn.entity.TopOfColumn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName TopOfColumnService
 * @Deprecated 顶部栏管理实现类
 * @Author ywq
 * @Date 2020-04-14
 */
@Service
public class TopOfColumnService {

    @Resource
    private TopOfColumnDao topOfColumnDao;

    /**
     * 查询顶部栏信息实现
     * @return
     */
    public AppResponse getTopOfColumn(){
        String userId = SecurityUtils.getCurrentUserId();
        TopOfColumn topOfColumn = topOfColumnDao.getTopOfColumn(userId);
        topOfColumn.setUserId(userId);
        return AppResponse.success("查询顶部栏信息成功！",topOfColumn);
    }
}
