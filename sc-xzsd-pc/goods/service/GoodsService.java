package com.xzsd.pc.goods.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.qcloud.cos.utils.StringUtils;
import com.xzsd.pc.goods.enums.GoodsStateEnums;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyList;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.dao.GoodsDao;
import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * 商品管理实现类
 * @ClassName GoodsService
 * @Description Goods
 * @Author ywq
 * @Date 2020-03-26
 */
@Service
public class GoodsService {

    @Resource
    private GoodsDao goodsDao;

    /**
     * 获取商品分类下拉框
     * @param classifyId
     * @return
     * @Author ywq
     * @Date 2020-03-30
     */
    public AppResponse listGoodsClassify(String classifyId){
        if(classifyId == null || "".equals(classifyId)){
            //当前分类为1级分类,赋值id为0
            classifyId = "0";
        }
        List<GoodsClassify> goodsClassifies = goodsDao.listGoodsClassify(classifyId);
        if(goodsClassifies == null || 0 == goodsClassifies.size()){
            return AppResponse.versionError("查询商品分类下拉框失败！");
        }
        GoodsClassifyList goodsClassifyList = new GoodsClassifyList();
        goodsClassifyList.setGoodsClassifyList(goodsClassifies);
        return AppResponse.success("查询商品分类下拉框成功！",goodsClassifyList);
    }

    /**
     * 添加商品
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoods(Goods goods){
        //查询书号是否存在
        int countIsbn = goodsDao.countIsbn(goods);
        //若书号存在直接在对应商品上增加其库存数量
        if(0 != countIsbn){
            //若果已存在书号,则失败
            return AppResponse.versionError("该书号已存在，此类检查");
        }
        goods.setGoodsId("sp" + StringUtil.getCommonCode(2));
        //获取当前登录人的id
        String createUser = SecurityUtils.getCurrentUserId();
        goods.setCreateUser(createUser);
        int count = goodsDao.addGoods(goods);
        if(0 == count){
            return AppResponse.versionError("新增商品失败");
        }
        return AppResponse.success("新增商品成功！",goods);
    }

    /**
     * 获取商品详情
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-03-27
     */
    public AppResponse getGoods(String goodsId){
        Goods goods = goodsDao.getGoods(goodsId);
        return AppResponse.success("查询商品详情成功！",goods);
    }

    /**
     * 分页查询商品
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    public AppResponse listGoods(Goods goods){
        List<Goods> listGoods = goodsDao.listGoodsByPage(goods);
        return AppResponse.success("分页查询商品列表成功" ,getPageInfo(listGoods));
    }

    /**
     * 更新商品信息
     * @param goods
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoods(Goods goods){
        //校验商品是否存在
        int countIsbn = goodsDao.countIsbn(goods);
        AppResponse appResponse = AppResponse.success("修改商品信息成功！");
        if(0 != countIsbn){
            return AppResponse.versionError("该书号已存在！请重新输入");
        }
        //当前商品状态为在售的且商品库存为0，直接改变状态为售罄
        if (goods.getGoodsStateId().equals(GoodsStateEnums.ON_SALE.getType()) && goods.getGoodsInventory() <= 0){
            goods.setGoodsStateId(GoodsStateEnums.SOLD_OUT.getType());
        }
        //当前商品状态为售罄的且商品库存为大于0，直接改变状态为在售
        if (goods.getGoodsStateId().equals(GoodsStateEnums.SOLD_OUT.getType()) && goods.getGoodsInventory() > 0){
            goods.setGoodsStateId(GoodsStateEnums.ON_SALE.getType());
        }
        //获取当前登录人的id
        String updateUser = SecurityUtils.getCurrentUserId();
        goods.setUpdateUser(updateUser);
        int count = goodsDao.updateGoods(goods);
        if(0 == count){
            appResponse = AppResponse.versionError("修改失败,数据有变化，请刷新！");
        }
        return appResponse;
    }

    /**
     * 更新商品状态
     * @param goods
     * @param goodsInventories
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsState(Goods goods,String goodsInventories){
        //取出商品编号和版本号转成数组
        List<String> listGoodsId = Arrays.asList(goods.getGoodsId().split(","));
        List<String> listVersion = Arrays.asList(goods.getVersion().split(","));
        List<String> inventories = new ArrayList<>();
        if (goodsInventories != null && !"".equals(goodsInventories)){
            //取出要修改的商品的库存,只有当状态为上架的时候才有
            inventories = Arrays.asList(goodsInventories.split(","));
        }
        List<Goods> listUpdate = new ArrayList<>();
        //获取要修改的状态
        String goodsStateId = goods.getGoodsStateId();
        //修改人:获取当前登录人id
        String updateUser = SecurityUtils.getCurrentUserId();
        //将所有修改的信息放入一个list中
        for (int i = 0 ; i < listGoodsId.size() ; i++ ) {
            Goods goods1 = new Goods();
            goods1.setGoodsId(listGoodsId.get(i));
            goods1.setVersion(listVersion.get(i));
            goods1.setGoodsStateId(goodsStateId);
            goods1.setUpdateUser(updateUser);
            //当前要改变的状态为在售的且商品库存为0，直接改变状态为售罄
            if (goodsStateId.equals(GoodsStateEnums.ON_SALE.getType()) && Integer.valueOf(inventories.get(i)) <= 0){
                goods1.setGoodsStateId(GoodsStateEnums.SOLD_OUT.getType());
            }
            listUpdate.add(goods1);
        }
        int count = goodsDao.updateGoodsState(listUpdate);
        if(0 == count){
            return AppResponse.versionError("修改状态失败！");
        }
        return AppResponse.success("修改状态成功！");
    }

    /**
     * 删除商品
     * @param goodsId
     * @return
     * @Author ywq
     * @Date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String goodsId){
        //取出商品编号转成list
        List<String> listGoodsId = Arrays.asList(goodsId.split(","));
        //查看选中的商品是否在订单中
        List<String> goodsInOrder = goodsDao.goodsIdInOrder(listGoodsId);
        if( goodsInOrder != null && goodsInOrder.size() != 0  ){
            String error = StringUtils.join(goodsInOrder);
            System.out.println(error);
            return AppResponse.versionError("书名:" + error + "商品还在订单中，无法删除");
        }
        //获取当前登录人的id
        String updateUser = SecurityUtils.getCurrentUserId();
        //删除商品(其首页轮播图和热门一起删除)
        int count = goodsDao.deleteGoods(listGoodsId,updateUser);
        if(0 == count){
            return AppResponse.versionError("删除商品失败！");
        }
        return AppResponse.success("删除商品成功！");
    }
}
