package com.example.onlinestorenew.dao;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.services.CategoryService;
import com.example.onlinestorenew.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.Join;

import java.util.ArrayList;
import java.util.List;

public class GoodDao {
    public GoodEntity findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(GoodEntity.class, id);
    }

    public List<GoodEntity> findAll() {
        List<GoodEntity> goods = (List<GoodEntity>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM GoodEntity ").list();
        return goods;
    }

    public GoodEntity createGood(GoodEntity good) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(good);
            tx1.commit();
            session.close();
            return good;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean deleteGood(GoodEntity good) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(good);
            tx1.commit();
            session.close();
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateGood(GoodEntity good) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(good);
            tx1.commit();
            session.close();
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<GoodEntity> searchGoods(String search, String mode) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        try {
            switch (mode) {
                case "name": {
                    return (List<GoodEntity>) session.createQuery("FROM GoodEntity WHERE name LIKE '%' || :name || '%'")
                            .setParameter("name", search)
                            .list();
                }
                case "price": {
                    return (List<GoodEntity>) session.createQuery("FROM GoodEntity WHERE price < :price")
                            .setParameter("price", Double.parseDouble(search))
                            .list();
                }
                case "category": {
                    return (List<GoodEntity>) session.createQuery("FROM GoodEntity WHERE categoryId = :category_id")
                            .setParameter("category_id", Integer.parseInt(search))
                            .list();
                }
                default: {
                    return (List<GoodEntity>) session.createQuery("FROM GoodEntity ")
                            .list();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<GoodEntity>();
        }
    }
}
