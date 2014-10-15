/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.domain.Product;
import com.oak_yoga_studio.domain.ShoppingCartItem;
import com.oak_yoga_studio.service.IShoppingCartService;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Somayeh
 */
public class ShoppingCartServiceImpl implements IShoppingCartService{

    public ShoppingCartServiceImpl() {
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    
    @Override
    public List<ShoppingCartItem> getShoppingCartItems() {
        //TODO
        return null;
    }
    
        @Override
        public double calculateTotalPrice(Product prdct) {
                double totalPrice = prdct.getPrice();
                double sum =+ totalPrice;
        return sum;
       
    }

    @Override
    public void addToShoppingCart(int customerId, int productId) {
        //TODO
    }
   

}
