/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Waiver;
import java.util.List;

/**
 *
 * @author weldu
 */
public interface CustomerDAO {
    
     public void addCustomer(Customer customer);
     
     public void updateCustomer(Customer customer);
     
     public Customer getCustomer(int id);
     
     public List<Customer> getAllCustomers();
     
     public List<Waiver> getApprovedWaiversByCustomerID(int customerID);
     
     public List<Course> getAllCoursesToWaive(Customer customer);
     
}
