package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.domain.Address;
import com.oak_yoga_studio.domain.User;
import com.oak_yoga_studio.service.IOrderService;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     

}
