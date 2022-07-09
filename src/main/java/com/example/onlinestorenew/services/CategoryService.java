package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.CategoryDao;
import com.example.onlinestorenew.models.CategoryEntity;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao;
    public CategoryService() {
        categoryDao = new CategoryDao();
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryDao.getAllCategories();
    }
}
