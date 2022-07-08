package com.example.onlinestorenew.dao;

import com.example.onlinestorenew.models.UserEntity;
import com.example.onlinestorenew.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {
    public UserEntity findByEmail(String email) {
        UserEntity user = (UserEntity)HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM UserEntity WHERE email = :email")
                .setParameter("email", email)
                .uniqueResult();
        return user;
    }

    public boolean createUser(UserEntity user) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            session.close();
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
