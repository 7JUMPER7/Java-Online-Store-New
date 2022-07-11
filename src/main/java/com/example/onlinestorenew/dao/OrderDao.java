package com.example.onlinestorenew.dao;

import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.models.OrderEntity;
import com.example.onlinestorenew.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDao {
    public OrderEntity getOrder(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(OrderEntity.class, id);
    }

    public List<OrderEntity> getAllOrders() {
        List<OrderEntity> image = (List<OrderEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM OrderEntity ")
                .list();
        return image;
    }

    public OrderEntity createOrder(OrderEntity order) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(order);
            tx1.commit();
            session.close();
            return order;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
