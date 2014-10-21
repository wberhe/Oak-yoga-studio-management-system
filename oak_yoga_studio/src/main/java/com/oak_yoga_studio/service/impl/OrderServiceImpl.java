package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.OrderDAO;
import com.oak_yoga_studio.dao.OrderItemDAO;
import com.oak_yoga_studio.domain.Order;
import com.oak_yoga_studio.domain.OrderItem;
import com.oak_yoga_studio.service.IOrderService;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Somayeh
 */

public class OrderServiceImpl implements IOrderService {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    public OrderServiceImpl() {
    }
    
    
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void setOrderItemDAO(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }
    

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addOrder(Order order) {
        try {
            orderDAO.addOrder(order);
            for(OrderItem o:order.getOrderItems()){
                orderItemDAO.addOrderItem(o);
            }
        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Order getOrderById(int id) {
        try {
            return orderDAO.getOrder(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateOrder(Order order) {
        try {
            orderDAO.updateOrder(order);
        } catch (Exception e) {

        }
    }
   @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders;
        try {
            orders = orderDAO.getAllOrders();
        } catch (Exception e) {
            orders = null;
        }
        return orders;
    }

}
