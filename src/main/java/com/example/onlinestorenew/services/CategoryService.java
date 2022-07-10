package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.CategoryDao;
import com.example.onlinestorenew.models.CategoryEntity;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao;
    public CategoryService() {
        categoryDao = new CategoryDao();
    }

    public CategoryEntity getById(int id) {
        return categoryDao.getById(id);
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public CategoryEntity createCaregory(CategoryEntity category) {
        return categoryDao.createCategory(category);
    }

    public boolean deleteCategory(int id) {
        CategoryEntity category = getById(id);
        return categoryDao.deleteCategory(category);
    }

    public boolean updateCategory(CategoryEntity category) {
        return categoryDao.updateCategory(category);
    }
}
