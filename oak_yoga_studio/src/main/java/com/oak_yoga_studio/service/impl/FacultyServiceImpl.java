/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.SectionDAO;
import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.dao.WaiverDAO;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.domain.Waiver;
import com.oak_yoga_studio.service.IFacultyService;
import com.oak_yoga_studio.service.INotificationService;
import java.util.List;

/**
 *
 * @author Weldino
 */
public class FacultyServiceImpl implements IFacultyService{
    
    private CustomerDAO facultyDAO;
    private SectionDAO sectionDAO;
    private WaiverDAO waiverDAO;
    private CustomerDAO customerDAO;
    private INotificationService notificationService;

    public FacultyServiceImpl(CustomerDAO facultyDAO, SectionDAO sectionDAO, WaiverDAO waiverDAO,
            CustomerDAO customerDAO, INotificationService notificationService) {
        this.facultyDAO = facultyDAO;
        this.sectionDAO = sectionDAO;
        this.waiverDAO = waiverDAO;
        this.customerDAO = customerDAO;
        this.notificationService = notificationService;
    }
    
    

    @Override
    public void addFaculty(Faculty faculty) {
         //TODO
    }

    @Override
    public List<Faculty> getListOfFaculty() {
        //TODO
        return null;
    }

    @Override
    public void updateFaculty(Faculty faculty) {
         //TODO
    }

    @Override
    public Faculty getFacultyById(long Id) {
      //TODO
        return null;  
    }

    @Override
    public void disableFaculty(Faculty faculty) {
         //TODO
    }

    @Override
    public List<Section> getFacultySections(Faculty faculty) {
        //TODO
        return null;
    }

    @Override
    public void updateWaiverRequest(Waiver waiver) {
         //TODO
    }

    @Override
    public Faculty assignAdvisor() {
        //TODO
        return null;
    }

    @Override
    public List<Customer> facultyAdvisees(Faculty faculty) {
        //TODO
        return null;
    }
    
}
