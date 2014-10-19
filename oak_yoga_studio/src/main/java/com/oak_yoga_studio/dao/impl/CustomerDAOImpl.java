/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.User;
import com.oak_yoga_studio.domain.Waiver;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author weldu
 */

public class CustomerDAOImpl implements CustomerDAO {
    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addCustomer(Customer customer) {
        sf.getCurrentSession().save(customer);
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateCustomer(Customer customer) {
        sf.getCurrentSession().saveOrUpdate(customer);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Customer getCustomer(int id) {
        
        Customer customer= (Customer) sf.getCurrentSession().get(Customer.class,id);
        return customer;    
            
     }
    /**
     * 
     * @returns only Customer users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers;        
        Query query= sf.getCurrentSession().createQuery("from Customer");
        customers= query.list();
        
       return customers;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Waiver> getApprovedWaiversByCustomerID ( int customerID)
    {
       
        List<Waiver> waivers ;
             
        Query query= sf.getCurrentSession().createQuery("from Waiver where customer.id=:customerID");
        
        query.setParameter("customerID", customerID);
        waivers= query.list();
        
       return waivers;
    
    }
    
}
