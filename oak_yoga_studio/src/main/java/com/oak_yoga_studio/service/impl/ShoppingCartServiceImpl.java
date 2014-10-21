/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.ShoppingCartDAO;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Product;
import com.oak_yoga_studio.domain.ShoppingCart;
import com.oak_yoga_studio.domain.ShoppingCartItem;
import com.oak_yoga_studio.service.IShoppingCartService;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author weldu
 */
@Transactional(propagation = Propagation.REQUIRED)
public class ShoppingCartServiceImpl implements IShoppingCartService {

    private ShoppingCartDAO cartDAO;

    public ShoppingCartServiceImpl() {
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void setCartDAO(ShoppingCartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public ShoppingCart getCart(int id) {
        try {
            return cartDAO.getShoppingCart(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ShoppingCart getCart(Customer customer) {
        try {
            return cartDAO.getShoppingCart(customer);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addCart(ShoppingCart cart) {
        try {
            cartDAO.addShoppingCart(cart);
        } catch (Exception e) {

        }
    }

    @Override
    public void updateCart(ShoppingCart cart) {
        try {
            cartDAO.updateShoppingCart(cart);
        } catch (Exception e) {
            //
        }
    }

    
    @Override
    public void clearCart(int id) {
        try {
            cartDAO.clearShoppingCart(id);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    @Override
    public void addShoppingCartItem(ShoppingCart cart, ShoppingCartItem item) {
        try{
            cart.addShoppingCartItem(item);
            cartDAO.updateShoppingCart(cart);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteShoppingCartItem(ShoppingCart cart, ShoppingCartItem item) {
        try{
            cart.removeShoppingCartItem(item);
            cartDAO.updateShoppingCart(cart);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
