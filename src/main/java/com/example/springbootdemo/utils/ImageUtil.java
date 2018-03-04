package com.example.springbootdemo.utils;

import org.springframework.web.multipart.MultipartFile;
import  java.io.*;

/**
 * 图片处理工具类
 */
public class ImageUtil {

    private final static  String ROOT = "/Users/mac/Downloads/JavaCode/springbootdemo/images/";

    public static boolean saveImg(MultipartFile file)
    {
        String filename = file.getOriginalFilename();

        File file1 = new File(ROOT+filename);
        if(!file1.getParentFile().exists()){
            file1.getParentFile().mkdirs();
        }
        try {
            file.transferTo(file1);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

