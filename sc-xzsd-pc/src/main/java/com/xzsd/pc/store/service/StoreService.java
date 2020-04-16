package com.xzsd.pc.store.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName StoreService
 * @Deprecated 门店实现类
 * @Author ywq
 * @Date 2020-04-12
 */
@Service
public class StoreService {

    @Resource
    private StoreDao storeDao;

    /**
     * 新增门店
     * @param store
     * @return
     */
    @Transactional(rollbackFor = Exception.class )
    public AppResponse addStore(Store store){
        //校验店长是否存在、电话是否已是别的店所用、用户是否已经是店长、营业执照是否已被使用
        int countStoreInfo = storeDao.countStoreInfo(store);
        String error = "";
        if( (countStoreInfo & 1) == 1){
            error = error + "营业执照编号已被使用！\n";
        }
        if( (countStoreInfo & 2) == 2){
            error = error + "店长编号不存在！\n";
        }
        if( (countStoreInfo & 4) == 4){
            error = error + "该电话已被别的店所用！\n";
        }
        if( (countStoreInfo & 8) == 8){
            error = error + "该店长已拥有一家店！\n";
        }
        if(0 != countStoreInfo){
            return AppResponse.versionError(error);
        }
        //生成门店邀请码
        store.setInviteCode(store.getAreaId() + StringUtil.getRankNumLetter(6));
        //生成门店编号
        store.setStoreId("st" + StringUtil.getCommonCode(2));
        //获取当前登录人
        store.setCreateUser(SecurityUtils.getCurrentUserId());
        //新增用户
        int count = storeDao.addStore(store);
        if(0 == count){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查看门店详情
     * @param storeId
     * @return
     */
    public AppResponse getStore(String storeId){
        Store user = storeDao.getStore(storeId);
        return AppResponse.success("获取门店详情成功",user);
    }

    /**
     * 查询门店列表（分页）
     * @param store
     * @param userName
     * @param role
     * @return
     */
    public AppResponse listStores(Store store,String userName,String role){
        store.setUserId(SecurityUtils.getCurrentUserId());
        List<Store> storeList = storeDao.listStoreByPage(store,userName,role);
        return AppResponse.success("分页查询门店列表成功!",getPageInfo(storeList));
    }

    /**
     * 修改门店信息
     * @param store
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateStore(Store store){
        //校验店长是否存在、电话是否已是别的店所用、用户是否已经是店长、营业执照是否已被使用
        int countStoreInfo = storeDao.countStoreInfo(store);
        String error = "";
        if( (countStoreInfo & 1) == 1){
            error = error + "营业执照编号已被使用！\n";
        }
        if( (countStoreInfo & 2) == 2){
            error = error + "店长编号不存在！\n";
        }
        if( (countStoreInfo & 4) == 4){
            error = error + "该电话已被别的店所用！\n";
        }
        if( (countStoreInfo & 8) == 8){
            error = error + "该店长已拥有一家店！\n";
        }
        if(0 != countStoreInfo){
            return AppResponse.versionError(error);
        }
        store.setUpdateUser(SecurityUtils.getCurrentUserId());
        //修改用户信息
        int count = storeDao.updateStore(store);
        if(0 == count){
            return AppResponse.versionError("修改失败,数据有变化，请刷新！");
        }
        return AppResponse.success("修改门店信息成功！");
    }

    /**
     * 删除门店
     * @param storeId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteStore(String storeId){
        List<String> listStoreId = Arrays.asList(storeId.split(","));
        //获取当前登录人id
        String updateUser = SecurityUtils.getCurrentUserId();
        //删除用户
        int count = storeDao.deleteStore(listStoreId,updateUser);
        if(0 == count){
            return AppResponse.versionError("删除门店失败，请重试！");
        }
        return AppResponse.success("删除门店成功！");
    }

}
