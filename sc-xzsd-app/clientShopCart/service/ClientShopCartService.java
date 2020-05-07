package com.xzsd.app.clientShopCart.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientShopCart.dao.ClientShopCartDao;
import com.xzsd.app.clientShopCart.entity.ClientShopCart;
import com.xzsd.app.clientShopCart.entity.ClientShopCartVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName ClientShopCartService
 * @Deripition app客户端购物车实现类
 * @Author ywq
 * @Date 2020-04-18
 */
@Service
public class ClientShopCartService {

    @Resource
    private ClientShopCartDao clientShopCartDao;

    static int MIN_CART_GOODS_COUNT = 1;

    /**
     * 添加购物车实现
     * @param clientShopCart
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addShoppingCart(ClientShopCart clientShopCart){
        String nowLogin = SecurityUtils.getCurrentUserId();
        //查看该商品是否已在购物车中
        ClientShopCart cartGoodsCount = clientShopCartDao.isGoodsInShopCart(clientShopCart.getGoodsId(),nowLogin);
        int count = 0;
        //若在购物车直接新增商品个数,不在则新增购物车
        if (cartGoodsCount == null || cartGoodsCount.getCartGoodsCount() == 0){
            clientShopCart.setShopCartId("gwc" + StringUtil.getCommonCode(2));
            clientShopCart.setCreateUser(nowLogin);
            if(clientShopCart.getCartGoodsCount() < MIN_CART_GOODS_COUNT ){
                clientShopCart.setCartGoodsCount( MIN_CART_GOODS_COUNT );
            }
            count = clientShopCartDao.addShoppingCart(clientShopCart);
        }else {
            clientShopCart.setUpdateUser(nowLogin);
            clientShopCart.setShopCartId(cartGoodsCount.getShopCartId());
            clientShopCart.setCartGoodsCount(clientShopCart.getCartGoodsCount() + cartGoodsCount.getCartGoodsCount());
            count = clientShopCartDao.updateShoppingCart(clientShopCart);
        }
        if(count == 0){
            return AppResponse.versionError("添加购物车失败");
        }
        return AppResponse.success("添加购物车成功！");
    }

    /**
     * 分页查询购物车列表
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    public AppResponse listShoppingCarts(){
        String userId = SecurityUtils.getCurrentUserId();
        List<ClientShopCartVO> clientShopCartList = clientShopCartDao.listShoppingCartsByPage(userId);
        if(clientShopCartList == null){
            return AppResponse.notFound("您的购物车还空空的快去逛逛吧。");
        }
        return AppResponse.success("查询购物车列表成功！",getPageInfo(clientShopCartList));
    }

    /**
     * 更新购物车实现
     * @param clientShopCart
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateShoppingCart(ClientShopCart clientShopCart){
        clientShopCart.setUpdateUser(SecurityUtils.getCurrentUserId());
        int count = clientShopCartDao.updateShoppingCart(clientShopCart);
        if(count == 0){
            return AppResponse.versionError("修改购物车数量失败请重试");
        }
        return AppResponse.success("修改购物车数量成功");
    }

    /**
     * 删除购物车商品实现
     * @param shopCartId
     * @return
     * @Author ywq
     * @Date 2020-04-18
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteShoppingCart(String shopCartId){
        List<String> shopCartIdList = Arrays.asList(shopCartId.split(","));
        String updateUser = SecurityUtils.getCurrentUserId();
        int count = clientShopCartDao.deleteShoppingCart(shopCartIdList,updateUser);
        if(count == 0){
            return AppResponse.versionError("删除失败！");
        }
        return AppResponse.success("删除成功！");
    }
}
