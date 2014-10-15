/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.EnrollmentDAO;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Enrollment;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Section;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fetiya
 */
public class EnrollmentDAOImpl implements EnrollmentDAO{

    
    SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addEnrollment(Enrollment enrollment) {
   
     sf.getCurrentSession().save(enrollment);
     
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateCourse(Enrollment enrollment) {
      
        sf.getCurrentSession().saveOrUpdate(enrollment);
    }

    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Enrollment getEnrollment(int id) {
       
        Enrollment enrollment=(Enrollment)sf.getCurrentSession().get(Enrollment.class, id);
        
        return enrollment;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Enrollment> getAllEnrollments() {
        
        List<Enrollment> enrollments=new ArrayList<Enrollment>();
        
        Query query= sf.getCurrentSession().createQuery("from Enrollment");
        enrollments= query.list();
        
       return enrollments;
    
    }

        @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Enrollment> getEnrollmentsByCustomer(Customer customer) {
        
    
        List<Enrollment> enrollments=new ArrayList<Enrollment>();
        
        Query query= sf.getCurrentSession().createQuery("from Enrollment where customer_Id=" + customer.getId());
        enrollments= query.list();
        
       return enrollments;
    }
    
   
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Enrollment> getEnrollmentsByCustomerID(int customerId) {
       
        List<Enrollment> enrollments=new ArrayList<Enrollment>();
        
        Query query= sf.getCurrentSession().createQuery("from Enrollment where customer_Id=" + customerId);
        enrollments= query.list();
        
       return enrollments;
    }

    @Override
    public List<Section> getSections(Course course) {
        
    List<Section> sections=new ArrayList<Section>();
        
        Query query= sf.getCurrentSession().createQuery("select distinct s from Section s join  s.course =course ");
        
        sections= query.list();
        
       return sections;
    }

    @Override
    public boolean checkSeatAvailablity(int sectionID) {
        
        
        Query query= sf.getCurrentSession().createQuery("select availableSeat from Section where id=" + sectionID);
        int availableSeats=0;
        availableSeats = (Integer)query.uniqueResult();
        
        if (availableSeats >0)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }

    @Override
    public List<Course> getAllCourses() {
       
        List<Course> courses=new ArrayList<Course>();
        
        Query query= sf.getCurrentSession().createQuery("from Course");
        courses= query.list();
        
       return courses;
        
    }

    @Override
    public void saveEnrollment(Enrollment enrollment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveSection(Section section) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Faculty getAvailableAdvisor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addWaitingListEnrollment(Enrollment enrollment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> getWaivers(Customer customerID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> getCoursesTaken(int customerID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void withdraw(Customer customer, Section section) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enrollment getTopWaitingList(int sectionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeEnrollmentStatus(String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
