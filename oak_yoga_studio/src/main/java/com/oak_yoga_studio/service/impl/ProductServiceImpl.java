/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.ProductDAO;
import com.oak_yoga_studio.domain.Product;
import com.oak_yoga_studio.service.IProductService;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Somayeh
 */
public class ProductServiceImpl implements IProductService {
    
    
    private ProductDAO productDAO;

    public ProductServiceImpl() {
    }

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
     
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Product> searchProductByName(String pname) {
        //TODO
        return null;
    }
 @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Product getProductDetailInfo(int id) {
        //TODO
        return null;
    }
 @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addProduct(Product product) {
        try{
           productDAO.addProduct(product);
        }catch(Exception e){
            
        }
    }
 @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateProduct(Product product) {
        try{
           productDAO.updateProduct(product);
        }catch(Exception e){
            
        }
    }
 @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Product getProduct(int id) {
        
        try{
           return productDAO.getProduct(id);
        }catch(Exception e){
            return null;
        }
    }
    
 @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Product> getAllProducts() {
        List<Product> products=productDAO.getAllProducts();
        return products;
    }
    
 @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Product> getProductByName(String name) {
        try{
            return productDAO.getProductByName(name);
        }catch(Exception e){
            return null;
        }
    }
    
  
}
