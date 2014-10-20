/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.OrderItem;
import java.util.List;

/**
 *
 * @author Weldu
 */
public interface IOrderItemService {
    
    public void addOrderItem(OrderItem ol);
    
    public OrderItem getOrderItemById(int id);
    
    public void updateOrderItem(OrderItem orderItem);
    
    public List<OrderItem> getAllOrderItems();
    
    public List<OrderItem> getAllOrderItemsOfOrder(int orderId);
    
}
