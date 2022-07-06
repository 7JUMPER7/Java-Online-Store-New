package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.GoodDao;
import com.example.onlinestorenew.models.GoodEntity;

import java.util.List;

public class GoodService {
    private GoodDao goodDao;
    public GoodService() {
        goodDao = new GoodDao();
    }

    public List<GoodEntity> findAllGoods() {
        return goodDao.findAll();
    }
}
