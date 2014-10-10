/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.ShoppingCart;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface ShoppingCartDAO {
    public void addShoppingCart(ShoppingCart shoppingCart);
     
     public void updateShoppingCart(ShoppingCart shoppingCart);
     
     public ShoppingCart getShoppingCart(int id);
     
     public List<ShoppingCart> getAllShoppingCarts();
}
