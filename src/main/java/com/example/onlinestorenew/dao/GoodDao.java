package com.example.onlinestorenew.dao;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GoodDao {
    public List<GoodEntity> findAll() {
        List<GoodEntity> goods = (List<GoodEntity>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM GoodEntity").list();
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
}
