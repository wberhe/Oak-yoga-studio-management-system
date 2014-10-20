/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.ShoppingCartDAO;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.ShoppingCart;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author weldu
 */
public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addShoppingCart(ShoppingCart shoppingCart) {
        sf.getCurrentSession().save(shoppingCart);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        sf.getCurrentSession().saveOrUpdate(shoppingCart);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public ShoppingCart getShoppingCart(int id) {
        ShoppingCart shoppingCart = (ShoppingCart) sf.getCurrentSession().get(ShoppingCart.class, id);
        return shoppingCart;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<ShoppingCart> getAllShoppingCarts() {

        Query query = sf.getCurrentSession().createQuery("from Order");
        List<ShoppingCart> shoppingCarts = query.list();

        return shoppingCarts;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public ShoppingCart getShoppingCart(Customer customer) {
        Query q = sf.getCurrentSession().createQuery("from ShoppingCart cart where cart.customer=customer");
        List<ShoppingCart> carts = q.list();
        return carts.get(0);
    }

    @Override
    public void clearShoppingCart(int id) {
        ShoppingCart cart = getShoppingCart(id);
        if (cart != null) {
            cart.getShoppingCartItems().clear();
            updateShoppingCart(cart);
        }

    }

}
