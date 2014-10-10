/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.ShoppingCartItem;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface ShoppingCartItemDAO {
    public void addShoppingCartItem(ShoppingCartItem item);
     
     public void updateShoppingCartItem(ShoppingCartItem item);
     
     public ShoppingCartItem getShoppingCartItem(int id);
     
     public List<ShoppingCartItem> getAllShoppingCartItems(int id);
}
