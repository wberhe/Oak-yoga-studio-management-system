/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.dao.FacultyDAO;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author weldu
 */
public class FacultyDAOImpl implements FacultyDAO {

        private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addFaculty(Faculty faculty) {
        sf.getCurrentSession().save(faculty);
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateFaculty(Faculty faculty) {
        sf.getCurrentSession().saveOrUpdate(faculty);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Faculty getFaculty(int id) {
        
        Faculty faculty= (Faculty) sf.getCurrentSession().get(Faculty.class,id);
        return faculty;    
            
     }
    /**
     * 
     * @returns only faculty users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Faculty> getAllActiveFaculties() {
        List<Faculty> faculties;        
        Query query= sf.getCurrentSession().createQuery("from Faculty Where active=true");
        faculties= query.list();
        
       return faculties;
    }
    /**
     * 
     * @returns only faculty users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Faculty> getAllFaculties() {
        List<Faculty> faculties;        
        Query query= sf.getCurrentSession().createQuery("from Faculty");
        faculties= query.list();
        
       return faculties;
    }
    
    
    @Override
    public Faculty getAvailableAdvisor() {
        //decide first on what basis an advisor should be chosen
        Faculty faculty;        
        Query query= sf.getCurrentSession().createQuery("select top 1 f from Faculty  f ");
        
        faculty=(Faculty) query.uniqueResult();
        
        return faculty;
        
    }
    
}
