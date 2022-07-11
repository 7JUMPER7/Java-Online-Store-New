package com.example.onlinestorenew.services;

import com.example.onlinestorenew.dao.OrderDao;
import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.models.OrderEntity;

import java.util.List;

public class OrderService {
    private OrderDao orderDao;
    public OrderService() {
        orderDao = new OrderDao();
    }

    public OrderEntity getOrder(int id) {
        return orderDao.getOrder(id);
    }

    public List<OrderEntity> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public OrderEntity createOrder(OrderEntity order) {
        return orderDao.createOrder(order);
    }
}
