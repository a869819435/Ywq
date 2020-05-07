package com.xzsd.pc.goodsClassify.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.JsonUtils;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyList;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyTree;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVO;
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

    public static String ROOT_ID = "0";

    /**
     * 添加商品分类实现
     * @param goodsClassify
     * @return
     * @Author ywq
     * @Date 2020-03-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsClassify(GoodsClassify goodsClassify){
        //查看该分类是否存在
        int countGoodsClassify = goodsClassifyDao.countGoodsClassify(goodsClassify);
        if(0 != countGoodsClassify){
            return AppResponse.versionError("该分类已存在");
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
     * @Author ywq
     * @Date 2020-03-29
     */
    public AppResponse getGoodsClassify(String classifyId){
        GoodsClassify goodsClassify = goodsClassifyDao.getGoodsClassify(classifyId);
        goodsClassify.setClassifyId(classifyId);
        return AppResponse.success("查询分类详情成功！",goodsClassify);
    }


    /**
     * 获取全部商品分类
     * @return
     * @Author ywq
     * @Date 2020-03-29
     */
    public AppResponse listAllGoodsClassify(){
        //获取全部的商品分类
        List<GoodsClassify> goodsClassifyList = goodsClassifyDao.listClassify();
        //初始化树根
        GoodsClassifyTree goodsClassifyTree = new GoodsClassifyTree();
        //遍历树且赋值
        initTree(goodsClassifyTree,goodsClassifyList,ROOT_ID);
        GoodsClassifyList oneClassifyList = new GoodsClassifyList();
        oneClassifyList.setOneClassifyList(goodsClassifyTree.getTwoClassifyList());
        return AppResponse.success("查询全部商品分类成功！", oneClassifyList);
    }

    /**
     * 遍历树
     * @param tree
     * @param goodsClassifyList
     * @param id
     * @Author ywq
     * @Date 2020-03-29
     */
    private void initTree(GoodsClassifyTree tree , List<GoodsClassify> goodsClassifyList, String id){
        //处理数据，使得好遍历，这里用下标方式遍历也可以
        Iterator<GoodsClassify> goodsClassifyIterator = goodsClassifyList.iterator();
        while(goodsClassifyIterator.hasNext()){
            //获取填入树的数据
            GoodsClassify goodsClassify = goodsClassifyIterator.next();
            if(goodsClassify.getClassifyId() != null && goodsClassify.getClassifyId().equals(id)){
                //当前节点是当前要找到父节点
                getDate(tree,goodsClassify);
            }else if( goodsClassify.getClassifyParent() != null && goodsClassify.getClassifyParent().equals(id)){
                //当前节点是当前要找的孩子结点
                GoodsClassifyTree child = new GoodsClassifyTree();
                //处理树的等级（填接口文档的坑，其实这个信息不重要）
                getDate(child,goodsClassify);
                if(tree.getTwoClassifyList() == null){
                    List<GoodsClassifyTree> temp = new ArrayList<GoodsClassifyTree>();
                    tree.setTwoClassifyList(temp);
                }
                tree.getTwoClassifyList().add(child);
                //递归，继续寻找
                initTree(child,goodsClassifyList,goodsClassify.getClassifyId());
            }
        }
    }

    /**
     * 树节点赋值
     * @param tree
     * @param goodsClassify
     * @Author ywq
     * @Date 2020-03-29
     */
    private void getDate(GoodsClassifyTree tree, GoodsClassify goodsClassify){
        tree.setClassifyId(goodsClassify.getClassifyId());
        tree.setClassifyName(goodsClassify.getClassifyName());
        tree.setClassifyParent(goodsClassify.getClassifyParent());
        tree.setClassifyComment(goodsClassify.getClassifyComment());
        tree.setVersion(goodsClassify.getVersion());
    }

    /**
     * 修改商品分类信息
     * @param goodsClassify
     * @return
     * @Author ywq
     * @Date 2020-03-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsClassify(GoodsClassify goodsClassify){
        //查看该分类是否存在
        int countGoodsClassify = goodsClassifyDao.countGoodsClassify(goodsClassify);
        if(0 != countGoodsClassify){
            return AppResponse.versionError("该分类已存在");
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
     * @Author ywq
     * @Date 2020-03-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoodsClassify(String classifyId,String classifyParent){
        //查看当前分类是否存在子类
        int countNextInfo = goodsClassifyDao.countNextInfo(classifyId,classifyParent);
        String error = "";
        if( (countNextInfo & 1) == 1 ){
            //存在子分类则不可以删除
            error += "该分类下有其他类\n";
        }
        if( (countNextInfo & 2) == 2 ){
            //存在商品则不可以删除
            error += "该分类下有商品";
        }
        if (error != null && !"".equals(error)){
            return AppResponse.versionError(error);
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
