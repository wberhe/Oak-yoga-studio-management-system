/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.UserDAO;
import com.oak_yoga_studio.domain.User;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author Weldino
 */
public class FacultyDAOImpl  implements UserDAO{
    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void addUser(User user) {
        
    }
    @Override
    public void updateUser(User user) {
        
    }

    @Override
    public User getUser(int id) {
        //TODO
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        //TODO
        return null;
    }
    
}
