/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.CredentialDAO;
import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.dao.WaiverDAO;
import com.oak_yoga_studio.domain.Address;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Credential;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.User;
import com.oak_yoga_studio.domain.Waiver;
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
    private WaiverDAO waiverDAO;
    private INotificationService notificationService;

    public CustomerServiceImpl(CustomerDAO customerDAO, CredentialDAO credentialDAO, WaiverDAO waiverDAO,
            INotificationService notificationService) {
        this.customerDAO = customerDAO;
        this.credentialDAO = credentialDAO;
        this.waiverDAO = waiverDAO;
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
        try {
            return this.customerDAO.getCustomer(id);
        } catch (Exception e) {
            return null;
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Customer> getAllCustomers() {

        try {
            List<Customer> customers;
            customers = this.customerDAO.getAllCustomers();
            return customers;
        } catch (Exception e) {
            return null;
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void disableCustomer(Customer customer) {
        try {
            if (customer.getCredential().isActive() == true) {
                customer.getCredential().setActive(false);
                customerDAO.updateCustomer(customer);
            } else {
                customer.getCredential().setActive(true);
                customerDAO.updateCustomer(customer);

            }
        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateCustomer(int id, Customer customer) {// needs to be checked

        /*Customer c = customerDAO.getCustomer(id);
         c.setFirstName(customer.getFirstName());
         c.setLastName(customer.getLastName());
         c.setDateOfBirth(customer.getDateOfBirth());
         c.setEmail(customer.getEmail());
         c.setProfilePicture(customer.getProfilePicture());
         c.getCredential().setActive(customer.getCredential().isActive());
         c.setShoppingCart(customer.getShoppingCart());

         c.getCredential().setActive(customer.getCredential().isActive());*/
        customerDAO.updateCustomer(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void notifyCustomer(Customer customer, String message) {
        notificationService.notifyCustomer(customer, message);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean checkUserName(String userName) {
        if (credentialDAO.getCredentialByUserName(userName) != null) {
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User getUserByUsername(String username) {
        Credential cr = credentialDAO.getCredentialByUserName(username);

        User user = null;
        if (cr != null) {
            user = cr.getUser();

        }
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Waiver> getApprovedWaiversByCustomerID(int customerID) {

        return customerDAO.getApprovedWaiversByCustomerID(customerID);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Waiver getWaiverRequest(int id) {
        try {
            return waiverDAO.getWaiverr(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Course> getAllCoursesToWaive(Customer customer) {

        return customerDAO.getAllCoursesToWaive(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Address getCutomerAdress(int customerId) {
        List<Address> custAdresses = customerDAO.getCustomerAddresses(customerId);
        if (!custAdresses.isEmpty()) {
            return custAdresses.get(0);
        }
        return null;
    }

    public List<Waiver> getAllWaiversByCustomer(Customer customer) {
        
        return customerDAO.getAllWaiversByCustomer(customer);
    
    }
}
