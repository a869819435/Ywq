package com.xzsd.pc.imageUpload.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.imageUpload.entity.ImageUpload;
import com.neusoft.util.UUIDUtils;
import com.qcloud.cos.COSClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageUploadService {

    @Autowired
    private ImageUpload imageUpload;
    //不能通过new来调用

    private COSClient cosClient;

    public AppResponse uploadImage(MultipartFile file){
        if( cosClient == null ){
            cosClient = imageUpload.tencentStart();
        }
        System.out.println(file.getContentType().substring(6));
        // image/png
        if (file.isEmpty()) {
            return AppResponse.versionError("上传失败，请选择重新选择图片");
        }
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = imageUpload.getBucketName();
        // 指定要上传到 COS 上对象键
        // 对象键（Key）是对象在存储桶中的唯一标识。
        // 例如，在对象的访问域名 `bucket1-1250000000.cos.ap-beijing.myqcloud.com/mydemo.jpg` 中，对象键为 mydemo.jpg,
        // 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
        String key = imageUpload.getQianzhui() + "/"+ imageUpload.getQianzhui()+ "_" + UUIDUtils.getUUID() + "." + file.getContentType().substring(6);
        File localFile = null;
        try {
            //将 MultipartFile 类型 转为 File 类型
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
        }catch (IOException e){
            return AppResponse.bizError(e.getMessage());
        }
        String imagePath = imageUpload.uploadImageUrl(localFile,cosClient,key);
        return AppResponse.success("图片上传成功！",imagePath);
    }
}
