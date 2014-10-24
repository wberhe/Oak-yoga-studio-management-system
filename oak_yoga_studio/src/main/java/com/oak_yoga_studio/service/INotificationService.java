/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Faculty;
import java.util.List;

/**
 *
 * @author Weldu
 */
public interface INotificationService {

    public void notifyAdvisor(Faculty faculty, String message);

    public void notifyFaculties(List<Faculty> faculties, String message);
    
    public void notifyCustomer(Customer customer, String message) ;

    public void contactAdmin(Faculty faculty, String message);

}
