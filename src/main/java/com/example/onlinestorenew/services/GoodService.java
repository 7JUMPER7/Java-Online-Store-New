package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.GoodDao;
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
}
