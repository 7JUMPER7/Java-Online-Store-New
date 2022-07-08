package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.ImageDao;
import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.utils.FileUtil;
import com.example.onlinestorenew.utils.Helpers;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImageService {
    private ImageDao imageDao;
    public ImageService() {
        imageDao = new ImageDao();
    }

    public List<ImageEntity> getGoodImages(int goodId) {
        return imageDao.getGoodImages(goodId);
    }

    public boolean createImages(MultipartFile[] images, int goodId, String firstName) {
        if(images.length == 0) {
            return true;
        }

        List<ImageEntity> imageEntities = new ArrayList<ImageEntity>();

        for(int i = 0; i < images.length; i++) {
            String newName = "temp.jpg";
            if(i == 0) {
                newName = firstName;
            } else {
                newName = Helpers.generateUUIDName(images[i].getOriginalFilename());
            }

            FileUtil.uploadFile(images[i], newName);
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setGoodId(goodId);
            imageEntity.setPath(newName);
            imageEntities.add(imageEntity);
        }

        return imageDao.createImages(imageEntities);
    }
}
