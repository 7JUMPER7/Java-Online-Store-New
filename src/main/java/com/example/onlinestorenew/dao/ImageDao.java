package com.example.onlinestorenew.dao;

import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ImageDao {
    public List<ImageEntity> getGoodImages(int goodId) {
        List<ImageEntity> image = (List<ImageEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM ImageEntity WHERE goodId = :goodId")
                .setParameter("goodId", goodId)
                .list();
        return image;
    }

    public boolean createImages(List<ImageEntity> images) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            for(ImageEntity image : images) {
                session.save(image);
            }
            tx1.commit();
            session.close();
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
