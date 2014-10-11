/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;


import com.oak_yoga_studio.domain.Order;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface OrderDAO {
     public void addOrder(Order order);
     
     public void updateOrder(Order order);
     
     public Order getOrder(int id);
     
     public List<Order> getAllOrders();
}
