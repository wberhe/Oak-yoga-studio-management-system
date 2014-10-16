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
        
        List<Enrollment> enrollments;
        
        Query query= sf.getCurrentSession().createQuery("from Enrollment");
        enrollments= query.list();
        
       return enrollments;
    
    }

        @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Enrollment> getEnrollmentsByCustomer(Customer customer) {
        
    
        List<Enrollment> enrollments;
        
        Query query= sf.getCurrentSession().createQuery("Seclect distinct e from Enrollment e where e.customer=customer");
        enrollments= query.list();
        
       return enrollments;
    }
    
   
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Enrollment> getEnrollmentsByCustomerID(int customerId) {
       
        List<Enrollment> enrollments;
        
        Query query= sf.getCurrentSession().createQuery("from Enrollment e where e.customer.id=customerId");
        enrollments= query.list();
        
       return enrollments;
    }

    @Override
    public List<Section> getSections(Course course) {
        
    List<Section> sections;
        
        Query query= sf.getCurrentSession().createQuery("from Section s where s.course=course");
        
        sections= query.list();
        
       return sections;
    }

    @Override
    public boolean checkSeatAvailablity(int sectionID) {
        
        
        Query query= sf.getCurrentSession().createQuery("select availableSeat from Section s where s.id=sectionID");
        int availableSeats;
        availableSeats = (Integer)query.uniqueResult();
        
        if (availableSeats >0 && availableSeats < 15)
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
       
        List<Course> courses;
        
        Query query= sf.getCurrentSession().createQuery("from Course c.");
        courses= query.list();
        
       return courses;
        
    }

    @Override
    public void saveEnrollment(Enrollment enrollment) {
        
        sf.getCurrentSession().saveOrUpdate(enrollment);
    }

  



    @Override
    public void addWaitingListEnrollment(Enrollment enrollment) {
             
             enrollment.setStatus(Enrollment.statusType.ACTIVE);
             sf.getCurrentSession().saveOrUpdate(enrollment);
             
    }



    @Override
    public List<Course> getCoursesTaken(int customerID) {
        
     List<Course> courses;
     
        Query query= sf.getCurrentSession().createQuery("select distinct c from Course c "
                + "join c.sections s join s.erollments e join e.customer cus"
                             + "where cus.id=customerID and c.status='COMPLETED'");
        courses= query.list();
       return courses;
    }

    @Override
    public void withdraw(Customer customer, Section section) {
        
        Enrollment enrollment ;
        Query query= sf.getCurrentSession().createQuery("select e from Enrollment e  Join e.customer cu" +
        "   join e.section where e.section=" + section +  " and e.customer=" + customer );
        enrollment= (Enrollment)query.uniqueResult();
        
                   
        sf.getCurrentSession().saveOrUpdate(enrollment);
         
    
    }

    @Override
    public Enrollment getTopWaitingList(int sectionId) {
        
        Enrollment enrollment ;
        Query query= sf.getCurrentSession().createQuery("select top 1 e from Enrollment e join e.section s"
                + "where s.id=sectionId and e.status='WAITINGLIST'" );
        enrollment= (Enrollment)query.uniqueResult();
        
        return enrollment;
    }

    @Override
    public void changeEnrollmentStatus(String status) {
       
        
       // ??add enrollment parama
        
        //should do setEnrollmentStatus(status)
        // and update Enrollment
    
    }
}
