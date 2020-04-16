package com.xzsd.pc.goodsClassify.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.JsonUtils;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyTree;
import com.xzsd.pc.utils.RedisUtil;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goodsClassify.dao.GoodsClassifyDao;
import com.xzsd.pc.goodsClassify.entity.GoodsClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商品分类实现类
 * @ClassName GoodsClassifyService
 * @Description how to do goods of classify
 * @Author ywq
 * @Date 2020-03-29
 */

@Service
public class GoodsClassifyService {

    @Resource
    private GoodsClassifyDao goodsClassifyDao;

    @Autowired
    private RedisUtil redisUtil;

    public static String ROOT_ID = "0";

    /**
     * 添加商品分类实现
     * @param goodsClassify
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsClassify(GoodsClassify goodsClassify){
        //获取父级id
        String classifyParent = goodsClassify.getClassifyId();
        if(classifyParent == null || "".equals(classifyParent)){
            //如果没有传父级id则直接赋值为0
            goodsClassify.setClassifyParent("0");
        }else {
            goodsClassify.setClassifyParent(classifyParent);
        }
        //查看该分类是否存在
        int countGoodsClassify = goodsClassifyDao.countGoodsClassify(goodsClassify);
        if(0 != countGoodsClassify){
            return AppResponse.bizError("该分类已存在");
        }
        //获取当前登录人id
        goodsClassify.setCreateUser(SecurityUtils.getCurrentUserId());
        //生成分离编号
        goodsClassify.setClassifyId("gc" + StringUtil.getCommonCode(2));
        int count = goodsClassifyDao.addGoodsClassify(goodsClassify);
        if(0 == count){
            return AppResponse.versionError("添加商品分类失败");
        }
        return AppResponse.success("添加分类成功",goodsClassify);
    }

    /**
     * 获取分类详情
     * @param classifyId
     * @return
     */
    public AppResponse getGoodsClassify(String classifyId){
        //以商品分类编号为键
        String key = classifyId;
        Object ans = redisUtil.get(key);
        //如果缓存中有则直接取
        if(ans != null ){
            GoodsClassify goodsClassify = JsonUtils.fromJson(ans.toString(),GoodsClassify.class);
            goodsClassify.setClassifyId(classifyId);
            return AppResponse.success("查询分类详情成功！",goodsClassify);
        }
        //缓存中没有则取出数据后再放入缓存，时间为5分钟
        GoodsClassify goodsClassify = goodsClassifyDao.getGoodsClassify(classifyId);
        String json = JsonUtils.toJson(goodsClassify);
        redisUtil.set(key,json,300);
        return AppResponse.success("查询分类详情成功！",goodsClassify);
    }


    /**
     * 获取全部商品分类
     * @return
     */
//    public AppResponse listAllGoodsClassify(){
//        List<GoodsClassifyVO> oneClassifyList = goodsClassifyDao.listClassify();
//        return AppResponse.success("查询全部商品分类成功！",oneClassifyList);
//    }
    public AppResponse listAllGoodsClassify(){
        //获取全部的商品分类
        List<GoodsClassify> goodsClassifyList = goodsClassifyDao.listClassify();
        //初始化树根
        GoodsClassifyTree goodsClassifyTree = new GoodsClassifyTree();
        goodsClassifyTree.setLevel( 0 );
        //遍历树且赋值
        initTree(goodsClassifyTree,goodsClassifyList,ROOT_ID);
        return AppResponse.success("查询全部商品分类成功！",goodsClassifyTree);
    }

    /**
     * 遍历树（哎！跪着填接口文档的坑）
     * @param tree
     * @param goodsClassifyList
     * @param id
     */
    private void initTree(GoodsClassifyTree tree , List<GoodsClassify> goodsClassifyList, String id){
        //处理数据，使得好遍历，这里用下标方式遍历也可以
        Iterator<GoodsClassify> goodsClassifyIterator = goodsClassifyList.iterator();
        while(goodsClassifyIterator.hasNext()){
            //获取填入树的数据
            GoodsClassify goodsClassify = goodsClassifyIterator.next();
            if(goodsClassify.getClassifyId().equals(id)){
                //当前节点是当前要找到父节点
                getDate(tree,goodsClassify);
            }else if(goodsClassify.getClassifyParent().equals(id)){
                //当前节点是当前要找的孩子结点
                GoodsClassifyTree child = new GoodsClassifyTree();
                //处理树的等级（填接口文档的坑，其实这个信息不重要）
                child.setLevel(tree.getLevel() + 1);
                getDate(child,goodsClassify);
                if(child.getLevel() == 1){
                    //当孩子等级为1的时候
                    if(tree.getOneClassifyList() == null){
                        List<GoodsClassifyTree> temp = new ArrayList<GoodsClassifyTree>();
                        tree.setOneClassifyList(temp);
                    }
                    tree.getOneClassifyList().add(child);
                }else {
                    //当孩子等级为2的时候
                    if(tree.getTwoClassifyList() == null){
                        List<GoodsClassifyTree> temp = new ArrayList<GoodsClassifyTree>();
                        tree.setTwoClassifyList(temp);
                    }
                    tree.getTwoClassifyList().add(child);
                }
                //递归，继续寻找
                initTree(child,goodsClassifyList,goodsClassify.getClassifyId());
            }
        }
    }

    /**
     * 树节点赋值
     * @param tree
     * @param goodsClassify
     */
    private void getDate(GoodsClassifyTree tree, GoodsClassify goodsClassify){
        tree.setId(goodsClassify.getClassifyId());
        tree.setName(goodsClassify.getClassifyName());
        tree.setParentId(goodsClassify.getClassifyParent());
        tree.setData(goodsClassify);
    }

    /**
     * 修改商品分类信息
     * @param goodsClassify
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsClassify(GoodsClassify goodsClassify){
        //查看该分类是否存在
        int countGoodsClassify = goodsClassifyDao.countGoodsClassify(goodsClassify);
        if(0 != countGoodsClassify){
            return AppResponse.bizError("该分类已存在");
        }
        //获取当前登录人id
        goodsClassify.setUpdateUser(SecurityUtils.getCurrentUserId());
        int count = goodsClassifyDao.updateGoodsClassify(goodsClassify);
        if(0 == count ){
            return AppResponse.versionError("修改分类信息失败");
        }
        return AppResponse.success("修改分类信息成功！",goodsClassify);
    }

    /**
     * 删除商品分类
     * @param classifyId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoodsClassify(String classifyId){
        //查看当前分类是否存在子类
        int countNextClassify = goodsClassifyDao.countNextClassify(classifyId);
        if(countNextClassify != 0){
            //存在则不可以删除
            return AppResponse.versionError("删除分类信息失败，该分类有子分类");
        }
        //获取当前登录人的id
        String updateUser = SecurityUtils.getCurrentUserId();
        //删除分类
        int count = goodsClassifyDao.deleteGoodsClassify(classifyId,updateUser);
        if(0 == count ){
            return AppResponse.versionError("删除分类信息失败");
        }
        return AppResponse.success("删除分类信息成功！");
    }
}
