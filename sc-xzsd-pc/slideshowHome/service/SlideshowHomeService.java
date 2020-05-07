package com.xzsd.pc.slideshowHome.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.slideshowHome.dao.SlideshowHomeDao;
import com.xzsd.pc.slideshowHome.entity.SlideshowHome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName SlideshowHomeService
 * @Deprecation 首页轮播图实现类
 * @Author ywq
 * @Date 2020-04-05
 */
@Service
public class SlideshowHomeService {

    @Resource
    private SlideshowHomeDao slideshowHomeDao;

    /**
     * 添加首页轮播图实现
     * @param slideshowHome
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addSlideshowHome(SlideshowHome slideshowHome){
        //校验轮播图中不可重复信息是否存在重复
        int countSlideshowInfo = slideshowHomeDao.countSlideshowInfo(slideshowHome);
        if(1 == (countSlideshowInfo & 1 )){
            return AppResponse.versionError("该商品已存在轮播图中");
        }
        slideshowHome.setSlideshowId("ssh" + StringUtil.getCommonCode(2));
        //获取修改人的id
        String createUser = SecurityUtils.getCurrentUserId();
        slideshowHome.setCreateUser(createUser);
        int count = slideshowHomeDao.addSlideshowHome(slideshowHome);
        if(0 == count){
            return AppResponse.versionError("添加首页轮播图失败");
        }
        if(2 == (countSlideshowInfo & 2 )){
            //处理排序序号
            slideshowHomeDao.solveSlideshowNum(slideshowHome);
        }
        return AppResponse.success("添加首页轮播图成功",slideshowHome);
    }

    /**
     * 分页查询首页轮播图实现
     * @param slideshowStateId
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    public AppResponse listSlideshowHome(String slideshowStateId){
        List<SlideshowHome> slideshowHomeList = slideshowHomeDao.listSlideshowHomeByPage(slideshowStateId);
        return AppResponse.success("查询首页轮播图列表成功" ,getPageInfo(slideshowHomeList));
    }

    /**
     * 查询商品分页实现
     * @param goodsName
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    public AppResponse listGoods(String goodsName,String goodsId){
        List<Goods> goodsList = slideshowHomeDao.listGoodsByPage(goodsName,goodsId);
        return AppResponse.success("查询商品列表成功" ,getPageInfo(goodsList));
    }

    /**
     * 修改首页轮播图状态实现
     * @param slideshowId
     * @param slideshowStateId
     * @param version
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateSlideshowHomeState(String slideshowId,String slideshowStateId,String version){
        //获取首页轮播图id
        List<String> listId = Arrays.asList(slideshowId.split(","));
        //获取首页轮播图版本
        List<String> listVersion = Arrays.asList(version.split(","));
        List<SlideshowHome> updateSlideList = new ArrayList<>();
        //获取当前登录人id
        String updateUser = SecurityUtils.getCurrentUserId();
        //将全部的更改信息放入一个list里
        for (int i = 0 ; i < listId.size() ; i++ ) {
            SlideshowHome slideshowHome = new SlideshowHome();
            slideshowHome.setSlideshowId(listId.get(i));
            slideshowHome.setVersion(listVersion.get(i));
            slideshowHome.setSlideshowStateId(slideshowStateId);
            slideshowHome.setUpdateUser(updateUser);
            updateSlideList.add(slideshowHome);
        }
        int count = slideshowHomeDao.updateSlideshowHomeState(updateSlideList);
        if(0 == count){
            return AppResponse.versionError("修改状态失败！");
        }
        return AppResponse.success("修改状态成功！");
    }

    /**
     * 删除首页轮播图
     * @param slideshowId
     * @return
     * @Author ywq
     * @Date 2020-04-05
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteSlideshowHome(String slideshowId){
        //获取首页轮播图id
        List<String> slideshowList = Arrays.asList(slideshowId.split(","));
        //获取当前登录人id
        String updateUser = SecurityUtils.getCurrentUserId();
        int count = slideshowHomeDao.deleteSlideshowHome(slideshowList,updateUser);
        if(0 == count){
            return AppResponse.versionError("删除商品失败！");
        }
        return AppResponse.success("删除商品成功！");
    }
}
