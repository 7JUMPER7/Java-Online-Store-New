package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.ImageDao;
import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.utils.FileUtil;
import com.example.onlinestorenew.utils.Helpers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageService {
    private ImageDao imageDao;
    public ImageService() {
        imageDao = new ImageDao();
    }

    public ImageEntity getImage(int id) {
        return imageDao.getImage(id);
    }

    public List<ImageEntity> getGoodImages(int goodId) {
        return imageDao.getGoodImages(goodId);
    }

    public List<ImageEntity> createImages(MultipartFile[] images, int goodId) {
        if(images.length == 0 || images[0].getOriginalFilename().equals("")) {
            return null;
        }

        List<ImageEntity> imageEntities = new ArrayList<ImageEntity>();

        for(MultipartFile image : images) {
            try {
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setGoodId(goodId);
                imageEntity.setFilename(image.getOriginalFilename());
                imageEntity.setData(image.getBytes());
                imageEntities.add(imageEntity);
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return imageDao.createImages(imageEntities);
    }

    public void deleteImages(ImageEntity image) {
        imageDao.delete(image);
    }
}
