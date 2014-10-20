/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Order;
import com.oak_yoga_studio.domain.OrderItem;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface OrderItemDAO {
     public void addOrderItem(OrderItem item);
     
     public void updateOrderItem(OrderItem item);
     
     public OrderItem getOrderItem(int id);
     
     public List<OrderItem> getAllOrderItems();
     
      public List<OrderItem> getAllOrderItemsOfThis(int orderId);
}
