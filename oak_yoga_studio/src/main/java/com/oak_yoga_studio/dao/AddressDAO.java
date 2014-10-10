/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Address;
import java.util.List;

/**
 *
 * @author Weldino
 */
public interface AddressDAO {
    
     public void addAddress(Address address);
     
     public void updateAddress(Address address);
     
     public Address getAddress(int id);
     
     public List<Address> getAllAddresses();
     
     public void removeAddress(Address address);
}
