/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.OrderItemDAO;
import com.oak_yoga_studio.domain.OrderItem;
import com.oak_yoga_studio.service.IOrderItemService;
import java.util.List;

/**
 *
 * @author Weldino
 */
public class OrderItemServiceImpl implements IOrderItemService {
    
    private OrderItemDAO orderItemDAO;
    

    public OrderItemServiceImpl() {
    }
    

    public void setOrderItemDAO(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try{
            orderItemDAO.addOrderItem(orderItem);
        }catch(Exception e){
            
        }
    }

    @Override
    public OrderItem getOrderItemById(int id) {
         try{
            return orderItemDAO.getOrderItem(id);
        }catch(Exception e){
          return null;  
        }
    }

   

    @Override
    public void updateOrderItem(OrderItem orderItem) {
         try{
            orderItemDAO.updateOrderItem(orderItem);
        }catch(Exception e){
            
        }
    }
    
    @Override
    public List<OrderItem> getAllOrderItems() {
         try{
            return orderItemDAO.getAllOrderItems();
        }catch(Exception e){
           return null; 
        }
    }

    @Override
    public List<OrderItem> getAllOrderItemsOfOrder(int orderId) {
        List<OrderItem> orderItems;
        try{
            orderItems=orderItemDAO.getAllOrderItemsOfThis(orderId);
        }catch(Exception e){
            orderItems=null;
        }
       return orderItems;
    }
    
    
}
