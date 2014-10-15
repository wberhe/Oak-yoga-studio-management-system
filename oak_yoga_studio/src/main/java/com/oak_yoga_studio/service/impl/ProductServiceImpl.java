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

    @Override
    public Product getProductDetailInfo(int id) {
        //TODO
        return null;
    }
    

}
