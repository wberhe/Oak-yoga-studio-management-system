/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.CredentialDAO;
import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.domain.Credential;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.User;
import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.service.INotificationService;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weldu
 */
public class CustomerServiceImpl implements ICustomerService {

    private CustomerDAO customerDAO;
    private CredentialDAO credentialDAO;
    private INotificationService notificationService;

    public CustomerServiceImpl(CustomerDAO customerDAO, CredentialDAO credentialDAO,
            INotificationService notificationService) {
        this.customerDAO = customerDAO;
        this.credentialDAO = credentialDAO;
        this.notificationService = notificationService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addCustomer(Customer customer) {
        try {
            customerDAO.addCustomer(customer);
        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Customer getCustomerById(int id) {
        try{
            return this.customerDAO.getCustomer(id);
        }catch(Exception e){
            return null;
        }
        
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Customer> getAllCustomers() {
        
        try{
            List<Customer> customers;
            customers=this.customerDAO.getAllCustomers();
            return customers;
        }catch(Exception e){
            return null;
        }
        
    }

    @Override
    public void disableCustomer(Customer customer) {
        
       //TODO
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateCustomer(int id,Customer customer) {
        
        Customer c=customerDAO.getCustomer(id);
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setDateOfBirth(customer.getDateOfBirth());
        c.setEmail(customer.getEmail());
        c.setProfilePicture(customer.getProfilePicture());
        c.getCredential().setBlocked(customer.getCredential().isBlocked());
    }
    
    public boolean checkUserName(String userName) {
        if(credentialDAO.getCredentialByUserName(userName)!=null){
            return true;
        }
        return false;
    }

    
    @Override
    public User getUserByUsername(String username){
        Credential cr = credentialDAO.getCredentialByUserName(username);
        
        User user = null;
        if(cr!=null) {
            user = cr.getUser();
           
        }
        return user;
    }
    
}
