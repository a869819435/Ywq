package com.xzsd.pc.imageUpload.controller;


import com.neusoft.core.restful.AppResponse;

import com.xzsd.pc.imageUpload.service.ImageUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * 商品管理
 * @ClassName TencentController
 * @Description Tencent
 * @Author ywq
 * @Date 2020-03-26
 */
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/imageUpload")
public class ImageUploadController {
    // 问题：在一个工具类中，通过@Value来映射配置文件的值，得到的总是null
    // 原因：不能用new工具类的方式，应该是用容器注册（@Autowried）的方式使用此工具类，就能得到配置文件里的值
    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @Resource
    ImageUploadService imageUploadService;

    /**
     * markdown图片上传到腾讯云
     * @param imageFile
     * @return
     */
    @PostMapping("uploadImage")
    public AppResponse uploadImage(@RequestParam("imageFile") MultipartFile imageFile){
        try{
            return imageUploadService.uploadImage(imageFile);
        }catch (Exception e){
            logger.error("图片上传失败！",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}

