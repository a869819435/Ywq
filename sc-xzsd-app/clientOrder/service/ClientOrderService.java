package com.xzsd.app.clientOrder.service;

import com.google.gson.GsonBuilder;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientGoods.entity.ClientGoodsInfo;
import com.xzsd.app.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.clientOrder.entity.*;
import com.xzsd.app.clientOrder.enums.OrderStateEnums;
import com.xzsd.app.clientShopCart.dao.ClientShopCartDao;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName ClientOrderService
 * @Deripition app客户端订单信息实现类
 * @Author ywq
 * @Date 2020-04-19
 */
@Service
public class ClientOrderService {

    static String ORDER_EVALUATED = "5";

    @Resource
    private ClientOrderDao clientOrderDao;

    @Resource
    private ClientShopCartDao clientShopCartDao;

    /**
     * 添加订单
     * @param clientOrderGoods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addOrder(ClientOrderGoods clientOrderGoods,String shopCartId){
        //判断客户是否绑定门店
        String storeId = clientOrderGoods.getStoreId();
        if( storeId == null || "".equals(storeId) ){
            return AppResponse.versionError("您还没有绑定好门店邀请码！");
        }
        //获取当前登录人id
        String nowLogin = SecurityUtils.getCurrentUserId();
        //获取选中的商品编号
        List<String> goodsIds = Arrays.asList(clientOrderGoods.getGoodsId().split(","));
        //获取对应编号的商品数量
        List<String> clientGoodsNums = Arrays.asList(clientOrderGoods.getClientGoodsNum().split(","));
        //获取选中的商品库存
        List<ClientGoodsInfo> goodsInventorys = clientOrderDao.getGoodsInventory(goodsIds);
        //记录所有商品购买后的剩余库存
        List<ClientGoodsInfo> updateGoods = new ArrayList<>();
        //存储库存不足的商品的排位
        List<Integer> failureGoods = new ArrayList<>();
        int sum = goodsIds.size();
        Map<String,Integer> map = new HashMap<>();
        for ( int i = 0 ; i < sum ; i++ ){
            map.put(goodsIds.get(i),i);
        }
        boolean flag = true;
        //检测购买数量是否大于库存,同时记录购买后的商品库存
        for (int i = 0 ; i < goodsInventorys.size() ;i++ ){
            int parent = map.get(goodsInventorys.get(i).getGoodsId());
            int ans = goodsInventorys.get(i).getGoodsInventory() - Integer.valueOf(clientGoodsNums.get(parent));
            if( ans < 0 ){
                failureGoods.add( i + 1 );
                flag = false;
            }
            ClientGoodsInfo clientGoodsInfo = new ClientGoodsInfo();
            clientGoodsInfo.setGoodsId(goodsIds.get(i));
            clientGoodsInfo.setGoodsInventory(ans);
            clientGoodsInfo.setUpdateUser(nowLogin);
            updateGoods.add(clientGoodsInfo);
        }
        //输出商品不够数量的排位
        String errorInfo = String.join(",",failureGoods.toString());
        if( flag == false ){
            return AppResponse.versionError("您选中的第" + errorInfo + "个商品库存不足");
        }
        //更新商品库存
        int countUpdateGoods = clientOrderDao.updateGoodsInventory(updateGoods);
        if (countUpdateGoods == 0) {
            return AppResponse.versionError("商品库存更新失败！");
        }
        //查看是否从购物车页面跳转来的,是的话,删除选中的购物车
        if(shopCartId != null && !"".equals(shopCartId) && !"0".equals(shopCartId)){
            List<String> shopCartIds = Arrays.asList(shopCartId.split(","));
            int count = clientShopCartDao.deleteShoppingCart(shopCartIds,nowLogin);
            if( count == 0 ){
                return AppResponse.versionError("操作失败，请重试！");
            }
        }
        //获取商品价格
        List<String> goodsPrices = Arrays.asList(clientOrderGoods.getGoodsPrice().split(","));
        //获取订单总价
        BigDecimal orderAllCost = new BigDecimal("0");
        //获取订单商品总数
        int orderAllGoodsCount = 0;
        //获取订单信息
        ClientOrder clientOrder = new ClientOrder();
        //生成订单id
        clientOrder.setOrderId("dd" + StringUtil.getCommonCode(2));
        //获取当前登录人，更新人为自己
        clientOrder.setCreateUser(nowLogin);
        //下单人为自己
        clientOrder.setUserId(clientOrder.getCreateUser());
        //组合商品信息，且统计总价和总数
        List<ClientOrderDeepen> clientOrderDeepenList= new ArrayList<>();
        for (int i = 0 ; i < goodsIds.size() ; i++ ){
            //获取订单详情的相关信息
            ClientOrderDeepen tmp = new ClientOrderDeepen();
            tmp.setCreateUser(nowLogin);
            tmp.setOrderId(clientOrder.getOrderId());
            tmp.setOrderGoodsId("ddd" + StringUtil.getCommonCode(2));
            tmp.setGoodsId(goodsIds.get(i));
            tmp.setClientGoodsNum(clientGoodsNums.get(i));
            clientOrderDeepenList.add(tmp);
            //计算订单的一些信息
            BigDecimal price = new BigDecimal(goodsPrices.get(i));
            BigDecimal num = new BigDecimal(clientGoodsNums.get(i));
            orderAllCost = orderAllCost.add(price.multiply(num ) );
            orderAllGoodsCount += Integer.valueOf(clientGoodsNums.get(i));
        }
        //订单总花费
        clientOrder.setOrderAllCost(orderAllCost.toString());
        //订单商品总数
        clientOrder.setOrderAllGoodsCount(orderAllGoodsCount);
        clientOrder.setStoreId(clientOrderGoods.getStoreId());
        int count = clientOrderDao.addOrder(clientOrderDeepenList,clientOrder);
        if(count == 0 ){
            return AppResponse.versionError("订单生成失败");
        }
        return AppResponse.success("订单生成完成");
    }

    /**
     * 查询订单列表
     * @param orderStateId
     * @return
     */
    public AppResponse listOrder(String orderStateId){
        //获取当前登录人id
        String userId = SecurityUtils.getCurrentUserId();
        List<ClientOrderVO> order = clientOrderDao.listOrderByPage(userId,orderStateId);
        if (order == null || order.size() == 0) {
            return AppResponse.notFound("您还没有下过订单！");
        }
        //获取对应的订单编号
        List<String> orderIds = new ArrayList<>();
        //映射订单id
        Map<String,Integer> map = new HashMap<>();
        int sum = order.size();
        for (int i = 0 ; i < sum ; i++ ){
            String key = order.get(i).getOrderId();
            //获取订单的id对应的value，初始化订单商品列表
            map.put(key,i );
            order.get(i).setGoodsList(new ArrayList<>());
            orderIds.add(order.get(i).getOrderId());
        }
        List<ClientOrderGoodsVO> clientOrderGoodsVOList = clientOrderDao.listOrderGoodsVO(orderIds,orderStateId);
        //赋值订单列表里的商品信息
        clientOrderGoodsVOList.forEach(temp -> {
            String key = temp.getOrderId();
            int parent = map.get(key);
            temp.setOrderId(null);
            order.get(parent).getGoodsList().add(temp);
        });
        return AppResponse.success("查询订单列表成功",getPageInfo(order));
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param orderStateId
     * @param version
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(String orderId,String orderStateId,String version){
        //获取当前登录人id为修改人
        String updateUser = SecurityUtils.getCurrentUserId();
        //关联更新
        if (OrderStateEnums.FINISHED_NO.getType().equals(orderStateId) || OrderStateEnums.DELETED.getType().equals(orderStateId)){
            List<OrderGoods> orderGoodsList = clientOrderDao.getOrderGoods(orderId);
            for (OrderGoods i : orderGoodsList){
                i.setUpdateUser(updateUser);
            }
            int countGoods = 0;
            //当更改的状态为已完成的时候,销售量增加
            if ( OrderStateEnums.FINISHED_NO.getType().equals(orderStateId) ){
                countGoods = clientOrderDao.updateGoodsSales(orderGoodsList);
            }
            //当更改的状态为取消订单时,库存增加
            if ( OrderStateEnums.DELETED.getType().equals(orderStateId) ){
                countGoods = clientOrderDao.addGoodsInventory(orderGoodsList);
            }
            if (countGoods == 0){
                return AppResponse.versionError("操作失败，请重试");
            }
        }
        int count = clientOrderDao.updateOrderState(orderId,orderStateId,updateUser,version);
        if (count == 0){
            return AppResponse.versionError("操作失败请重试");
        }
        return AppResponse.success("操作成功！");
    }

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    public AppResponse listOrderDeepen(String orderId){
        List<ClientOrderDeepenVO> clientOrderDeepenList = clientOrderDao.listOrderDeepen(orderId);
        if(clientOrderDeepenList == null || clientOrderDeepenList.size() == 0){
            return AppResponse.notFound("获取订单详情失败");
        }
        //初始化订单详情商品信息列表,且赋值详情信息
        ClientOrderDeepenVO orderDeepen = getOrderDeepenInfo(clientOrderDeepenList);
        return AppResponse.success("获取订单详情成功！",orderDeepen);
    }

    private ClientOrderDeepenVO getOrderDeepenInfo(List<ClientOrderDeepenVO> clientOrderDeepenList){
        //初始化订单详情商品信息列表,且赋值详情信息
        ClientOrderDeepenVO clientOrderDeepenVO = new ClientOrderDeepenVO();
        ClientOrderDeepenVO clientOrderDeepenGet = clientOrderDeepenList.get(0);
        clientOrderDeepenVO.setStoreName(clientOrderDeepenGet.getStoreName());
        clientOrderDeepenVO.setAddress(clientOrderDeepenGet.getAddress());
        clientOrderDeepenVO.setGoodsList(new ArrayList<>());
        clientOrderDeepenVO.setOrderAllCost(clientOrderDeepenGet.getOrderAllCost());
        clientOrderDeepenVO.setOrderAllGoodsCount(clientOrderDeepenGet.getOrderAllGoodsCount());
        clientOrderDeepenVO.setOrderId(clientOrderDeepenGet.getOrderId());
        clientOrderDeepenVO.setCrateTime(clientOrderDeepenGet.getCrateTime());
        clientOrderDeepenVO.setOrderStateId(clientOrderDeepenGet.getOrderStateId());
        //获取订单详情里的商品信息列表
        clientOrderDeepenList.forEach(i -> {
            ClientOrderGoods clientOrderGoods = new ClientOrderGoods();
            clientOrderGoods.setGoodsName(i.getGoodsName());
            clientOrderGoods.setGoodsPrice(i.getGoodsPrice());
            clientOrderGoods.setGoodsImagePath(i.getGoodsImagePath());
            clientOrderGoods.setCartGoodsCount(i.getCartGoodsCount());
            clientOrderGoods.setGoodsId(i.getGoodsId());
            clientOrderDeepenVO.getGoodsList().add(clientOrderGoods);
        });
        return clientOrderDeepenVO;
    }

    /**
     * 查询订单评价商品信息列表
     * @param orderId
     * @return
     */
    public AppResponse listGoodsForEvaluate(String orderId){
        //获取订单商品的图片
        List<ClientOrderGoods> clientOrderGoods = clientOrderDao.listGoodsForEvaluate(orderId);
        if(clientOrderGoods == null){
            return AppResponse.notFound("获取订单商品信息失败");
        }
        ClientOrderData goodsList = new ClientOrderData();
        goodsList.setGoodsList(clientOrderGoods);
        return AppResponse.success("获取订单商品信息成功！",goodsList);
    }

    /**
     * 新增订单商品评价
     * @param evaluateOrder
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsEvaluate(JSONObject evaluateOrder){
        //将json数据转list
        EvaluateOrder ans = new GsonBuilder().create().fromJson(evaluateOrder.toJSONString(),EvaluateOrder.class);
        List<String> goodsIds = new ArrayList<>();
        String userId = SecurityUtils.getCurrentUserId();
        //添加编号值
        for (EvaluateInfo i : ans.getEvaluateInfoList()){
            i.setEvaluateId("pj" + StringUtil.getCommonCode(2));
            i.setUserId(userId);
            goodsIds.add(i.getGoodsId());
            if(i.getImageList() != null){
                for ( ImageInfo j : i.getImageList() ){
                    j.setEvaluateId(i.getEvaluateId());
                    j.setCreateUser(userId);
                    j.setImageId("pjt" + StringUtil.getCommonCode(2));
                }
            }
        }
        //添加商品评价接口,同时修改商品的总评分
        int count = clientOrderDao.addGoodsEvaluate(ans.getEvaluateInfoList());
        if ( count == 0 ){
            return AppResponse.versionError("新增商品评价失败！");
        }
        //修改订单状态和商品评价评分
        int countUpdateEvaluate = clientOrderDao.updateEvaluateState(ans.getOrderId(),ORDER_EVALUATED,userId,goodsIds);
        if (countUpdateEvaluate == 0){
            return AppResponse.versionError("订单状态修改失败或者商品评分更新失败！");
        }
        return AppResponse.success("新增商品评价成功！");
    }
}
