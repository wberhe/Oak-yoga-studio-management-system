/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.OrderDAO;
import com.oak_yoga_studio.domain.Order;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weldu
 */
public class OrderDAOImpl implements OrderDAO {

    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addOrder(Order order) {
        sf.getCurrentSession().save(order);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateOrder(Order order) {
        sf.getCurrentSession().saveOrUpdate(order);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Order getOrder(int id) {
        Order order = (Order) sf.getCurrentSession().get(Order.class, id);
        return order;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Order> getAllOrders() {
        
        Query query = sf.getCurrentSession().createQuery("from Order_table");
        List<Order> orders = query.list();

        return orders;
    }

}
