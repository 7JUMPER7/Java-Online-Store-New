package com.example.onlinestorenew.dao;

import com.example.onlinestorenew.models.CategoryEntity;
import com.example.onlinestorenew.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CategoryDao {
    public List<CategoryEntity> getAllCategories() {
        List<CategoryEntity> categories = (List<CategoryEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM CategoryEntity ").list();
        return categories;
    }

}
