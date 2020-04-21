package com.xzsd.app.clientHome.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.dao.ClientHomeDao;
import com.xzsd.app.clientHome.entity.ClientHomeGoodsInfo;
import com.xzsd.app.clientHome.entity.ClientHomeSlideshowInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ClientHomeService
 * @Deripition app客户首页实现类
 * @Author ywq
 * @Date 2020-04-17
 */
@Service
public class ClientHomeService {

    @Resource
    private ClientHomeDao clientHomeDao;

    /**
     * 查询首页轮播图信息
     * @return
     */
    public AppResponse listRotationCharHome(){
         List<ClientHomeSlideshowInfo> clientHomeSlideshowInfoList = clientHomeDao.listRotationCharHome();
         if(clientHomeSlideshowInfoList == null || clientHomeSlideshowInfoList.size() == 0){
             return AppResponse.notFound("当前没有有效的首页轮播图！");
         }
         return AppResponse.success("查询首页轮播图信息成功！",clientHomeSlideshowInfoList);
    }

    /**
     * 查询热门商品信息
     * @return
     */
    public AppResponse listHotGoods(){
        int showNum = clientHomeDao.getHotGoodsShowNum();
        List<ClientHomeGoodsInfo> clientHomeGoodsInfoList = clientHomeDao.listHotGoods(showNum);
        if(clientHomeGoodsInfoList == null ){
            AppResponse.notFound("未查到热门商品信息！");
        }
        return AppResponse.success("查询热门商品信息成功！",clientHomeGoodsInfoList);
    }
}
