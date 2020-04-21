package com.xzsd.pc.hotGoods.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.hotGoods.dao.HotGoodsDao;
import com.xzsd.pc.hotGoods.entity.HotGoods;
import com.xzsd.pc.hotGoods.entity.HotGoodsShowNumVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName HotGoodsService
 * @Deprecation 热门商品实现类
 * @Author ywq
 * @Date 2020-04-11
 */
@Service
public class HotGoodsService {

    @Resource
    private HotGoodsDao hotGoodsDao;

    /**
     * 添加热门商品
     * @param hotGoods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addHotGoods(HotGoods hotGoods){
        //查看商品是否为热门、热门序号是否存在
        int countHotGoodsInfo = hotGoodsDao.countHotGoodsInfo(hotGoods);
        if( (countHotGoodsInfo & 1 ) == 1 ){
            return AppResponse.versionError("该商品已经是热门商品");
        }
        //获取热门商品编号
        hotGoods.setHotGoodsId("hg" + StringUtil.getCommonCode(2));
        //获取当前登录人id
        hotGoods.setCreateUser(SecurityUtils.getCurrentUserId());
        int count = hotGoodsDao.addHotGoods(hotGoods);
        if(count == 0){
            return AppResponse.versionError("商品添加热门失败！请重试");
        }
        if( (countHotGoodsInfo & 2 ) == 2 ){
            //调整热门商品的序号
            String temp = String.valueOf(hotGoods.getHotGoodsNum());
            hotGoodsDao.solveAddHotGoodsNum(temp,hotGoods.getHotGoodsId());
        }
        return AppResponse.success("添加热门商品成功");
    }

    /**
     * 获取热门商品详情
     * @param hotGoodsId
     * @return
     */
    public AppResponse getHotGoods(String hotGoodsId){
        HotGoods hotGoods = hotGoodsDao.getHotGoods(hotGoodsId);
        return AppResponse.success("获取热门商品详情成功", hotGoods);
    }

    /**
     * 分页查询热门商品信息
     * @param goodsId
     * @param goodsName
     * @return
     */
    public AppResponse listHotGoods(String goodsId,String goodsName){
        List<HotGoods> hotGoodsList = hotGoodsDao.listHotGoodsByPage(goodsId,goodsName);
        return AppResponse.success("查询热门商品信息成功",getPageInfo(hotGoodsList));
    }

    /**
     * 修改热门商品信息
     * @param hotGoods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoods(HotGoods hotGoods){
        //查看商品是否为热门、热门序号是否存在
        int countHotGoodsInfo = hotGoodsDao.countHotGoodsInfo(hotGoods);
        if( (countHotGoodsInfo & 1 ) == 1 ){
            return AppResponse.versionError("该商品已经是热门商品");
        }
        int oldHotGoodsNum = 0;
        int hotGoodsNum = 0;
        if((countHotGoodsInfo & 2 ) == 2){
            //现获取该热门的序号，然后调整热门商品的序号，空出要修改的序号
            oldHotGoodsNum = hotGoodsDao.getHotGoodsNum(hotGoods.getHotGoodsId());
            hotGoodsNum = hotGoods.getHotGoodsNum();
        }
        //获取当前登录人id
        hotGoods.setUpdateUser(SecurityUtils.getCurrentUserId());
        int count = hotGoodsDao.updateHotGoods(hotGoods);
        if(0 == count){
            return AppResponse.versionError("修改热门商品操作失败!请重试");
        }
        if( (countHotGoodsInfo & 2 ) == 2 ){
            //处理编号乱序
            hotGoodsDao.solveUpdateHotGoodsNum(hotGoodsNum,oldHotGoodsNum,hotGoods.getHotGoodsId());
        }
        return AppResponse.success("修改热门商品信息成功！");
    }

    /**
     * 查询热门商品展示数量
     * @return
     */
    public AppResponse getHotGoodsShowNum(){
        HotGoodsShowNumVO hotGoodsShowNum = hotGoodsDao.getHotGoodsShowNum();
        return AppResponse.success("查询热门商品展示数量成功",hotGoodsShowNum);
    }

    /**
     * 修改热门商品展示数量
     * @param version
     * @return
     */
    @Transactional(rollbackFor = Exception.class )
    public AppResponse updateHotGoodsShowNum(String hotGoodsShowNum,String version){
        //获取当前登录人
        String updateUser = SecurityUtils.getCurrentUserId();
        int count = hotGoodsDao.updateHotGoodsShowNum(hotGoodsShowNum,version,updateUser);
        if(0 == count){
            return AppResponse.versionError("修改热门商品展示数量失败");
        }
        return AppResponse.success("修改热门商品展示数量成功");
    }

    /**
     * 删除热门商品
     * @param hotGoodsId
     * @return
     */
    @Transactional(rollbackFor = Exception.class )
    public AppResponse deleteHotGoods(String hotGoodsId){
        //获取当前登录人
        String updateUser = SecurityUtils.getCurrentUserId();
        List<String> hotGoodsIdList = Arrays.asList(hotGoodsId.split(","));
        int count = hotGoodsDao.deleteHotGoods(hotGoodsIdList,updateUser);
        if(0 == count){
            return AppResponse.versionError("删除热门商品失败");
        }
        return AppResponse.success("删除热门商品成功");
    }
}
