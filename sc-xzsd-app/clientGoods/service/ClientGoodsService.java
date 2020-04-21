package com.xzsd.app.clientGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientGoods.dao.ClientGoodsDao;
import com.xzsd.app.clientGoods.entity.*;
import com.xzsd.app.clientOrder.entity.ImageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName ClientGoodsService
 * @Deripition app客户客户端商品信息实现类
 * @Author ywq
 * @Date 2020-04-17
 */
@Service
public class ClientGoodsService {

    @Resource
    private ClientGoodsDao clientGoodsDao;
    /**
     * 好评按钮值
     */
    static int GOOD_SCORE = 5;
    /**
     * 中评按钮值
     */
    static int GENERAL_SCORE = 3;
    /**
     * 差评按钮值
     */
    static int BAD_SCORE = 1;

    /**
     * 获取商品详情实现
     * @param goodsId
     * @return
     */
    public AppResponse getGoods(String goodsId){
        ClientGoodsInfo clientGoodsInfo = clientGoodsDao.getGoods(goodsId);
        if(clientGoodsInfo == null){
            return AppResponse.notFound("该商品已下架！");
        }
        return AppResponse.success("查询商品详情成功！",clientGoodsInfo);
    }

    /**
     * 查询商品评价信息
     * @param clientGoodsEvaluates
     * @return
     */
    public AppResponse listGoodsEvaluates(ClientGoodsEvaluates clientGoodsEvaluates){
        //获取除图片外的全部信息
        List<ClientGoodsEvaluatesVO> clientGoodsEvaluatesVOList = clientGoodsDao.listGoodsEvaluatesByPage(clientGoodsEvaluates);
        int sum = clientGoodsEvaluatesVOList.size();
        if(clientGoodsEvaluatesVOList == null || sum == 0 ){
            int score = clientGoodsEvaluates.getEvaluateScore();
            if(score == GOOD_SCORE){
                return AppResponse.notFound("该商品未有人给好评");
            }
            if(score == GENERAL_SCORE){
                return AppResponse.notFound("该商品未有人给中评");
            }
            if(score == BAD_SCORE){
                return AppResponse.notFound("该商品未有人给差评");
            }
            return AppResponse.notFound("该商品未有人评价");
        }
        //获取有效的评价id
        List<String> evaluateIds = new ArrayList<>();
        //处理处图片外的评价信息
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0 ; i < sum ; i++){
            ClientGoodsEvaluatesVO temp = clientGoodsEvaluatesVOList.get(i);
            String key = temp.getEvaluateId();
            map.put(key, i);
            //赋值除评价图片外的评价信息
            temp.setImageList(new ArrayList<>());
            clientGoodsEvaluatesVOList.get(i).setEvaluateId(null);
            evaluateIds.add(key);
        }
        //获取商品的评价图片信息
        List<ClientGoodsEvaluates> imagePaths = clientGoodsDao.listImagePath(evaluateIds,clientGoodsEvaluates.getEvaluateScore());
        imagePaths.forEach(i ->{
            String key = i.getEvaluateId();
            int parent = map.get(key);
            String url = i.getImagePath();
            if(url != null && !"".equals(url)){
                clientGoodsEvaluatesVOList.get(parent).getImageList().add(url);
            }
        });
        return AppResponse.success("查询商品评价成功",clientGoodsEvaluatesVOList);
    }

    /**
     * 查询商品一级分类信息实现
     * @return
     */
    public AppResponse listOneGoodsClassify(){
        List<ClientGoodsClassify> oneClassifyList = clientGoodsDao.listOneGoodsClassify();
        if(oneClassifyList == null){
            return AppResponse.notFound("未找到商品一级分类信息");
        }
        return AppResponse.success("查询一级商品分类成功",oneClassifyList);
    }

    public AppResponse listGetClassGoods(String classifyId){
        //获取全部的二级分类以及商品
        List<ClientGoodsClassify> allTwoClassifyList = clientGoodsDao.listGetClassGoods(classifyId);
        int sum = allTwoClassifyList.size();
        if(sum == 0){
            return AppResponse.versionError("获取商品二级分类失败");
        }
        List<ClientTwoClassify> twoClassifyList = new ArrayList<ClientTwoClassify>();
        //创建映射，获取所有商品对应的二级分类
        Map<String ,Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = 0 ; i < sum ; i++ ){
            ClientGoodsClassify clientGoodsClassify = allTwoClassifyList.get(i);
            //获取分类的id
            String key = clientGoodsClassify.getClassifyId();
            if(map.get(key) == null){
                //若未加入映射，则增加其分类内容，初始化商品列表以及给其规定一个序号
                map.put(key, cnt++ );
                ClientTwoClassify clientTwoClassify = new ClientTwoClassify();
                clientTwoClassify.setClassifyId(key);
                clientTwoClassify.setClassifyName(clientGoodsClassify.getClassifyName());
                clientTwoClassify.setGoodsList(new ArrayList<ClientTwoClassifyGoods>());
                twoClassifyList.add(clientTwoClassify);
            }
        }
        for (int i = 0 ; i < sum ; i++ ){
            ClientGoodsClassify clientGoodsClassify = allTwoClassifyList.get(i);
            //获取商品对应的分类的序号
            int parent = map.get(clientGoodsClassify.getClassifyId());
            //获取当前商品信息
            ClientTwoClassifyGoods clientTwoClassifyGoods = new ClientTwoClassifyGoods();
            clientTwoClassifyGoods.setGoodsId(clientGoodsClassify.getGoodsId());
            clientTwoClassifyGoods.setGoodsName(clientGoodsClassify.getGoodsName());
            clientTwoClassifyGoods.setGoodsImagePath(clientGoodsClassify.getGoodsImagePath());
            clientTwoClassifyGoods.setGoodsPrice(clientGoodsClassify.getGoodsPrice());
            twoClassifyList.get(parent).getGoodsList().add(clientTwoClassifyGoods);
        }
        return AppResponse.success("查询二级分类以及其商品成功！",twoClassifyList);
    }
}
