/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.CourseDAO;
import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.dao.EnrollmentDAO;
import com.oak_yoga_studio.dao.FacultyDAO;
import com.oak_yoga_studio.dao.SectionDAO;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Enrollment;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.service.IEnrollmentService;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fetiya
 */
public class EnrollmentServiceImpl  implements IEnrollmentService
{

    
    
     private CustomerDAO customerDAO;
     
     private CourseDAO courseDAO;
     
     private SectionDAO sectionDAO;
     
     private FacultyDAO facultyDAO;
     
     private EnrollmentDAO enrollmentDAO;

    public EnrollmentServiceImpl(CustomerDAO customerDAO, CourseDAO courseDAO, SectionDAO sectionDAO, FacultyDAO facultyDAO, EnrollmentDAO enrollmentDAO) {
        this.customerDAO = customerDAO;
        this.courseDAO = courseDAO;
        this.sectionDAO = sectionDAO;
        this.facultyDAO = facultyDAO;
        this.enrollmentDAO = enrollmentDAO;
    }
      
     
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addEnrollment(Enrollment enrollment) {
     
       try
       {
           enrollmentDAO.addEnrollment(enrollment);
       }
       catch(Exception e)
       {
         
       }
    }

  

     @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Enrollment getEnrollmentById(int id) {
       
        
        try
       {
           return enrollmentDAO.getEnrollment(id);
       }
       catch(Exception e)
       {
           return null;
           
       }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Enrollment> getAllEnrollments() {
       
        
         try
       {
           return enrollmentDAO.getAllEnrollments();
       }
       catch(Exception e)
       {
           return null;
           
       }
        
    }

    @Override
    public List<Enrollment> getEnrollmentsByCustomer(Customer customer) {
          
        try
       {
        
           return enrollmentDAO.getEnrollmentsByCustomer(customer);
       }
       catch(Exception e)
       {
           return null;
           
       }  
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Enrollment> getEnrollmentsByCustomerID(int customerID) {
   
       try
       {
        
           return enrollmentDAO.getEnrollmentsByCustomerID(customerID);
       }
       catch(Exception e)
       {
           return null;
       }
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Section> getSections(Course course) {
        
    
    }
 
    
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean checkSeatAvailablity(int sectionID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public List<Course> getAllCourses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public void saveEnrollment(Enrollment enrollment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public void saveSection(Section section) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public Faculty getAvailableAdvisor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public void addWaitingListEnrollment(Enrollment enrollment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public List<Course> getWaivers(Customer customerID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public List<Course> getCoursesTaken(int customerID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public void withdraw(Customer customer, Section section) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public Enrollment getTopWaitingList(int sectionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Transactional(propagation = Propagation.REQUIRED)
     @Override
    public void changeEnrollmentStatus(String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}
