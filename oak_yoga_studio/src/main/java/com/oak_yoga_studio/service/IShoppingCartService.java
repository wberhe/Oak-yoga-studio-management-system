package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Product;
import com.oak_yoga_studio.domain.ShoppingCartItem;
import java.util.List;

/**
 *
 * @author Somayeh
 */
public interface IShoppingCartService {

    public List<ShoppingCartItem> getShoppingCartItems();
    
    public double calculateTotalPrice(Product prdct);
    
    public void addToShoppingCart(int customerId , int productId);
    
}
