/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.User;
import com.oak_yoga_studio.domain.Waiver;
import java.util.List;

/**
 *
 * @author Weldu
 */
public interface ICustomerService {
    
    public void addCustomer(Customer customer);
    
    public Customer getCustomerById(int id);
    
    public List<Customer> getAllCustomers();
    
    public void disableCustomer(Customer customer);
    
    public void updateCustomer(int id,Customer customer);
    
    public User getUserByUsername(String username);
    
    public boolean checkUserName(String userName); 
    
    public void notifyCustomer(Customer customer, String message);
    
    public List<Waiver> getApprovedWaiversByCustomerID ( int customerID);
}
