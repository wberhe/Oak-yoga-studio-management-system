/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.ShoppingCartItemDAO;
import com.oak_yoga_studio.domain.ShoppingCart;
import com.oak_yoga_studio.domain.ShoppingCartItem;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Senai
 */
public class ShoppingCartItemDAOImpl implements ShoppingCartItemDAO {

    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addShoppingCartItem(ShoppingCartItem item) {
        sf.getCurrentSession().save(item);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateShoppingCartItem(ShoppingCartItem item) {
        sf.getCurrentSession().saveOrUpdate(item);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public ShoppingCartItem getShoppingCartItem(int id) {
        ShoppingCartItem item = (ShoppingCartItem) sf.getCurrentSession().get(ShoppingCartItem.class, id);
        return item;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<ShoppingCartItem> getAllShoppingCartItems() {

        Query query = sf.getCurrentSession().createQuery("from ShoppingCartItem");
        List<ShoppingCartItem> shoppingCartItems = query.list();

        return shoppingCartItems;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void deleteShoppingCartItem(ShoppingCartItem item) {
        try {
            sf.getCurrentSession().delete(item);
        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<ShoppingCartItem> getShoppingCartItemsOfCart(ShoppingCart cart) {
        List<ShoppingCartItem> items;
        Query q = sf.getCurrentSession().createQuery("Select item from ShoppingCartItem item where item.shoppingCart=cart");
        items = q.list();
        return items;
    }

}
