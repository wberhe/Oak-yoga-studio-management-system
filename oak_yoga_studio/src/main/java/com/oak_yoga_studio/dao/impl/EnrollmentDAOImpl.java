/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.EnrollmentDAO;
import com.oak_yoga_studio.domain.Enrollment;
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
    
}
