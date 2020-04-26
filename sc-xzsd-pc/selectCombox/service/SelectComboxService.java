package com.xzsd.pc.selectCombox.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.selectCombox.dao.SelectComboxDao;
import com.xzsd.pc.selectCombox.entity.AreaList;
import com.xzsd.pc.selectCombox.entity.AreaInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SelectComboxService
 * @Deprecated 下拉框管理实现类
 * @Author ywq
 * @Date 2020-04-13
 */
@Service
public class SelectComboxService {

    @Resource
    private SelectComboxDao selectComboxDao;

    /**
     * 地区下拉查询实现
     * @param areaId
     * @return
     */
    public AppResponse listArea(String areaId){
        if( areaId == null || "".equals(areaId) ){
            //若收到的id为空则赋值0
            areaId = "0";
        }
        AreaList areaList = new AreaList();
        areaList.setAreaList(selectComboxDao.listArea(areaId));
        return AppResponse.success("区域下拉查询成功！",areaList);
    }
}
