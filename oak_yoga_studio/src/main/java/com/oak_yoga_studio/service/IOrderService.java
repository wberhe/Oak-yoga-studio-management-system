/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Address;
import com.oak_yoga_studio.domain.Order;
import com.oak_yoga_studio.domain.User;
import java.util.List;

/**
 *
 * @author Somayeh
 */
public interface IOrderService {    
      
    public void addOrder(Order order);
    
    public Order getOrderById(int id);
    
    public void updateOrder(Order order);
    
    public List<Order> getAllOrders();
    
  }
