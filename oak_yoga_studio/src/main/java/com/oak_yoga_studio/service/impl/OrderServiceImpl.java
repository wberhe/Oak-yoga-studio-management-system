package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.domain.Address;
import com.oak_yoga_studio.domain.Order;
import com.oak_yoga_studio.domain.User;
import com.oak_yoga_studio.service.IOrderService;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Somayeh
 */
public class OrderServiceImpl implements IOrderService{
    
    
    @Transactional(propagation = Propagation.REQUIRED)

    @Override
    public void fillingShippingAddress(Address address) {
//TODO
    }

    @Override
    public void fillingPaymentInfo(User userInfo) {
//TODO
    }

    @Override
    public List<Order> placeOrder(Order orderList) {
        return null;
//TODO
    }
    
    
     

}
