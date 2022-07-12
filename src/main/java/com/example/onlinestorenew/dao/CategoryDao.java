package com.example.onlinestorenew.dao;

import com.example.onlinestorenew.models.CategoryEntity;
import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryDao {
    public CategoryEntity getByName(String name) {
        List<CategoryEntity> result = (List<CategoryEntity>) HibernateSessionFactoryUtil
                .getSessionFactory().openSession()
                .createQuery("FROM CategoryEntity WHERE name LIKE '%' || :name || '%'")
                .setParameter("name", name).list();
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public CategoryEntity getById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CategoryEntity.class, id);
    }

    public List<CategoryEntity> getAllCategories() {
        List<CategoryEntity> categories = (List<CategoryEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM CategoryEntity ").list();
        return categories;
    }

    public CategoryEntity createCategory(CategoryEntity category) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(category);
            tx1.commit();
            session.close();
            return category;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean deleteCategory(CategoryEntity category) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(category);
            tx1.commit();
            session.close();
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateCategory(CategoryEntity category) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(category);
            tx1.commit();
            session.close();
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
