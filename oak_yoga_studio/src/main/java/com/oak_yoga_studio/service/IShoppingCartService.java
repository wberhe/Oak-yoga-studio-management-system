package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.ShoppingCart;
import com.oak_yoga_studio.domain.ShoppingCartItem;
import java.util.List;

/**
 *
 * @author weldu
 */
public interface IShoppingCartService {
    
    public ShoppingCart getCart(int id);
    
    public ShoppingCart getCart(Customer customer);
    
    public void addCart(ShoppingCart cart);
    
    public void updateCart(ShoppingCart cart);
    
    public void clearCart(int id);
    
    
}
