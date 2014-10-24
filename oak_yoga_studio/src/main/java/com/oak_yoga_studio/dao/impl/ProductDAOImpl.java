/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.ProductDAO;
import com.oak_yoga_studio.domain.Order;
import com.oak_yoga_studio.domain.OrderItem;
import com.oak_yoga_studio.domain.Product;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weldu
 */
public class ProductDAOImpl implements ProductDAO{
    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addProduct(Product product) {
        sf.getCurrentSession().save(product);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateProduct(Product product) {
        sf.getCurrentSession().saveOrUpdate(product);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Product getProduct(int id) {
        Product product = (Product) sf.getCurrentSession().get(Product.class, id);
        return product;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Product> getAllProducts() {
        
        Query query = sf.getCurrentSession().createQuery("from Product" );
        List<Product> products = query.list();

        return products;
    }
@Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Product> getProductByName(String name) {
        List<Product> product;
        Query query=sf.getCurrentSession().createQuery("select p from Product p where p.name=name");
        System.out.println("hereeee"+ query.list());
        //query.setString("theName",name);
        product=query.list();
        return product;
    
    }
    
    
   

}
