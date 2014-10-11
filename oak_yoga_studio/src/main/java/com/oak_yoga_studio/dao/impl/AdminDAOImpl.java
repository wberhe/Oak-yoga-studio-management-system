/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.UserDAO;
import com.oak_yoga_studio.domain.Admin;
import com.oak_yoga_studio.domain.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weldino
 */
public class AdminDAOImpl implements UserDAO {
    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addUser(User admin) {
        sf.getCurrentSession().save(admin);
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateUser(User admin) {
        sf.getCurrentSession().saveOrUpdate(admin);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User getUser(int id) {
        
        Admin admin= (Admin) sf.getCurrentSession().get(Admin.class,id);
        return admin;    
            
     }
    /**
     * 
     * @returns only Admins users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<User> getAllUsers() {
        List<User> admins;        
        Query query= sf.getCurrentSession().createQuery("from Admin");
        admins= query.list();
        
       return admins;
    }
}
