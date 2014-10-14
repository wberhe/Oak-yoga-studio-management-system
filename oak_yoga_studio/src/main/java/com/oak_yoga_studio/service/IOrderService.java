/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Address;
import com.oak_yoga_studio.domain.User;

/**
 *
 * @author Somayeh
 */
public interface IOrderService {
    
    public void fillingShippingAddress(Address address);
    
    public void fillingPaymentInfo(User userInfo);
    

}
