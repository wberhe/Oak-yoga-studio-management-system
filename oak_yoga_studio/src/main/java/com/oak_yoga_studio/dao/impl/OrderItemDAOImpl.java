/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.OrderItemDAO;
import com.oak_yoga_studio.domain.Order;
import com.oak_yoga_studio.domain.OrderItem;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Senai
 */
public class OrderItemDAOImpl implements OrderItemDAO {
    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addOrderItem(OrderItem item) {
        sf.getCurrentSession().save(item);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateOrderItem(OrderItem item) {
        sf.getCurrentSession().saveOrUpdate(item);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderItem getOrderItem(int id) {
        OrderItem order = (OrderItem) sf.getCurrentSession().get(Order.class, id);
        return order;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItem> getAllOrderItems(int orderId) {
        
        Query query = sf.getCurrentSession().createQuery("from Order, OrderItem Where Order_id=" + orderId );
        List<OrderItem> orderItems = query.list();

        return orderItems;
    }

}
