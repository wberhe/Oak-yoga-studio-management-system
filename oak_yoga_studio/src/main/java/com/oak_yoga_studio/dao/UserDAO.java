/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.User;
import java.util.List;

/**
 *
 * @author weldino
 */
public interface UserDAO {
    
     public void addUser(User user);
     
     public void updateUser(User user);
     
     public User getUser(int id);
     
     public List<User> getAllCustomers();
     
     public List<User> getAllFaculties();
     
}
