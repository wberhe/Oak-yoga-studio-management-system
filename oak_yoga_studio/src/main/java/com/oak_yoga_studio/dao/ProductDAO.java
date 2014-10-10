/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.OrderItem;
import com.oak_yoga_studio.domain.Product;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface ProductDAO {
     public void addProduct(Product product);
     
     public void updateProduct(Product product);
     
     public Product getProduct(int id);
     
     public List<Product> getAllProducts();
}
