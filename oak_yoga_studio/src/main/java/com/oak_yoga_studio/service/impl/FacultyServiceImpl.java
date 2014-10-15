/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.SectionDAO;
import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.dao.FacultyDAO;
import com.oak_yoga_studio.dao.WaiverDAO;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.domain.Waiver;
import com.oak_yoga_studio.service.IFacultyService;
import com.oak_yoga_studio.service.INotificationService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Weldino
 */
public class FacultyServiceImpl implements IFacultyService {

    private FacultyDAO facultyDAO;
    private SectionDAO sectionDAO;
    private WaiverDAO waiverDAO;
    private CustomerDAO customerDAO;
    private INotificationService notificationService;

    public FacultyServiceImpl(FacultyDAO facultyDAO, SectionDAO sectionDAO, WaiverDAO waiverDAO,
            CustomerDAO customerDAO, INotificationService notificationService) {
        this.facultyDAO = facultyDAO;
        this.sectionDAO = sectionDAO;
        this.waiverDAO = waiverDAO;
        this.customerDAO = customerDAO;
        this.notificationService = notificationService;
    }

    @Override
    public void addFaculty(Faculty faculty) {
        try {
            facultyDAO.addFaculty(faculty);
        } catch (Exception e) {

        }
    }

    @Override
    public List<Faculty> getListOfActiveFaculty() {
        try {
            return facultyDAO.getAllActiveFaculties();
        } catch (Exception e) {
            return new ArrayList();
        }

    }
    
    @Override
    public List<Faculty> getListOfFaculty() {
        try {
            return facultyDAO.getAllFaculties();
        } catch (Exception e) {
            return new ArrayList();
        }

    }

    @Override
    public void updateFaculty(Faculty faculty) {
        try {
            facultyDAO.updateFaculty(faculty);
        } catch (Exception e) {

        }
    }

    @Override
    public Faculty getFacultyById(int Id) {
        try {
            return facultyDAO.getFaculty(Id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void enableOrdisableFaculty(Faculty faculty) {
        try {
            if (faculty.getCredential().isActive()) {
                faculty.getCredential().setActive(false);
            } else {
                faculty.getCredential().setActive(true);
            }
            facultyDAO.updateFaculty(faculty);
        } catch (Exception e) {

        }
    }

    @Override
    public List<Section> getFacultySections(Faculty faculty) {
        try {
            return faculty.getSections();
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    @Override
    public void updateWaiverRequest(Waiver waiver) {
        try {
             waiverDAO.updateWaiver(waiver);
        } catch (Exception e) {
             
        }
    }

    @Override
    public List<Customer> facultyAdvisees(Faculty faculty) {
        try{
            return faculty.getAdvisees();
        }catch(Exception e){
            return new ArrayList();
        }
    }

}
