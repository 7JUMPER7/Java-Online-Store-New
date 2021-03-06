package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.GoodDao;
import com.example.onlinestorenew.models.CategoryEntity;
import com.example.onlinestorenew.models.GoodEntity;

import java.util.List;

public class GoodService {
    private GoodDao goodDao;
    public GoodService() {
        goodDao = new GoodDao();
    }

    public GoodEntity findById(int id) {
        return goodDao.findById(id);
    }

    public List<GoodEntity> findAllGoods() {
        return goodDao.findAll();
    }

    public GoodEntity createGood(GoodEntity good) {
        return goodDao.createGood(good);
    }

    public boolean deleteGood(int goodId) {
        GoodEntity good = findById(goodId);
        if(good == null) {
            return false;
        }

        goodDao.deleteGood(good);
        return true;
    }

    public boolean updateGood(GoodEntity good) {
        return goodDao.updateGood(good);
    }

    public List<GoodEntity> searchGoods(String search, String mode) {
        if(mode.equals("category")) {
            CategoryService categoryService = new CategoryService();
            CategoryEntity category = categoryService.getByName(search);
            if(category != null) {
                search = String.valueOf(category.getId());
            } else {
                mode = null;
            }
        }

        return goodDao.searchGoods(search, mode);
    }
}
