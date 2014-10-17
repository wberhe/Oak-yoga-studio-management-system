/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.CourseDAO;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fetiya
 */
public class CourseDAOImpl implements CourseDAO {

    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addCourse(Course course) {

        sf.getCurrentSession().save(course);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateCourse(Course course) {

        sf.getCurrentSession().saveOrUpdate(course);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Course getCourse(int id) {

        Course course = (Course) sf.getCurrentSession().get(Course.class, id);
        return course;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Course> getCoursesWith(String words) {
        List<Course> courses;

        Query query = sf.getCurrentSession().createQuery("from Course c Where c.courseName like words");
        //query.setParameter("name",words);
        courses = query.list();

        return courses;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Course> getAllActiveCourses() {
        List<Course> courses;

        Query query = sf.getCurrentSession().createQuery("from Course c Where c.active=true");
        courses = query.list();

        return courses;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Course> getAllCourses() {

        List<Course> courses;

        Query query = sf.getCurrentSession().createQuery("from Course");
        courses = query.list();

        return courses;
    }
    
    
        @Override
    public List<Course> getWaivers(Customer customerID) {
        
        List<Course> courses;
        
        Query query= sf.getCurrentSession().createQuery("select distinct c from Course c join c.waivers w join w.customer cu where cu.id=customerID");
        courses= query.list();
        
       return courses;
    }
}
