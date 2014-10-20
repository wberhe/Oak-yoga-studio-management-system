/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.ShoppingCart;
import com.oak_yoga_studio.domain.ShoppingCartItem;
import java.util.List;

/**
 *
 * @author Weldu
 */
public interface IShoppingCartItemService {
    
    public ShoppingCartItem getCartItem(int id);
    
    public void addCartItem(ShoppingCartItem cartItem);
    
    public void updateCartItem(ShoppingCartItem cartItem);
    
    public void deleteCartItem(ShoppingCartItem cartItem);
    
    public List<ShoppingCartItem> getCartItemsOfCart(ShoppingCart cart);

    public List<ShoppingCartItem> getAllCartItems();
}
