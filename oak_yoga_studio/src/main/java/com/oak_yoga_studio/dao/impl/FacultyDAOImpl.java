/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.UserDAO;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author weldu
 */
public class FacultyDAOImpl implements UserDAO {

        private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addUser(User faculty) {
        sf.getCurrentSession().save(faculty);
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateUser(User faculty) {
        sf.getCurrentSession().saveOrUpdate(faculty);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User getUser(int id) {
        
        Faculty faculty= (Faculty) sf.getCurrentSession().get(Faculty.class,id);
        return faculty;    
            
     }
    /**
     * 
     * @returns only faculty users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<User> getAllUsers() {
        List<User> faculties;        
        Query query= sf.getCurrentSession().createQuery("from Faculty");
        faculties= query.list();
        
       return faculties;
    }
    
}
