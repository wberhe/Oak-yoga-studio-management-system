/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.UserDAO;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weldino
 */

public class UserDAOImpl implements UserDAO {
    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addUser(User customer) {
        sf.getCurrentSession().save(customer);
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateUser(User customer) {
        sf.getCurrentSession().saveOrUpdate(customer);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User getUser(int id) {
        
        Customer customer= (Customer) sf.getCurrentSession().get(Customer.class,id);
        return customer;    
            
     }
    /**
     * 
     * @returns only Customer users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<User> getAllCustomers() {
        List<User> customers=new ArrayList<User>();
        
        Query query= sf.getCurrentSession().createQuery("from User u where u.class=Customer");
        customers= query.list();
        
       return customers;
    }
    
    /**
     * 
     * @returns all faculty users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<User> getAllFaculties() {
        List<User> faculties=new ArrayList<User>();
        
        Query query= sf.getCurrentSession().createQuery("from User u where u.class=Faculty and u.status=ACTIVE");
        faculties= query.list();
        
       return faculties;
    }
}
