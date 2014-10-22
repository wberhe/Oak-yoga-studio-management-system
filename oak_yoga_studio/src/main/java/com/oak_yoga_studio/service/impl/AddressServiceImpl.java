/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.AddressDAO;
import com.oak_yoga_studio.domain.Address;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weldino
 */
public class AddressServiceImpl {
    private AddressDAO addDAO;

    public AddressServiceImpl() {
    }

    public AddressServiceImpl(AddressDAO addDAO) {
        this.addDAO = addDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addAddress(Address address) {
        try {
            addDAO.addAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAddress(Address address) {
        try {
            addDAO.updateAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
