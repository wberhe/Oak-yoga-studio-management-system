/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;


import com.oak_yoga_studio.dao.ShoppingCartItemDAO;
import com.oak_yoga_studio.domain.ShoppingCart;
import com.oak_yoga_studio.domain.ShoppingCartItem;
import com.oak_yoga_studio.service.IShoppingCartItemService;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weldino
 */
@Transactional(propagation = Propagation.REQUIRED)
public class ShoppingCartItemServiceImpl implements IShoppingCartItemService {

    private ShoppingCartItemDAO cartItemDAO;

    public ShoppingCartItemServiceImpl() {
    }
    
    

    @Transactional(propagation = Propagation.SUPPORTS)
    public void setCartItemDAO(ShoppingCartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    @Override
    public ShoppingCartItem getCartItem(int id) {
        try {
            return cartItemDAO.getShoppingCartItem(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addCartItem(ShoppingCartItem cartItem) {
        try {
            cartItemDAO.addShoppingCartItem(cartItem);
        } catch (Exception e) {
            //
        }
    }

    @Override
    public void updateCartItem(ShoppingCartItem cartItem) {
        try {
            cartItemDAO.updateShoppingCartItem(cartItem);
        } catch (Exception e) {
            //
        }
    }

    @Override
    public void deleteCartItem(ShoppingCartItem cartItem) {
        try {
            cartItemDAO.deleteShoppingCartItem(cartItem);
        } catch (Exception e) {
            //
        }
    }

    @Override
    public List<ShoppingCartItem> getCartItemsOfCart(ShoppingCart cart) {
        try {
            return cartItemDAO.getShoppingCartItemsOfCart(cart);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ShoppingCartItem> getAllCartItems() {
        try {
            return cartItemDAO.getAllShoppingCartItems();
        } catch (Exception e) {
            return null;
        }
    }

}
