/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.CourseDAO;
import com.oak_yoga_studio.dao.SectionDAO;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.domain.Waiver;
import com.oak_yoga_studio.service.ICourseService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Senai
 */
public class CourseServiceImpl implements ICourseService {

    private CourseDAO courseDAO;
    private SectionDAO sectionDAO;

    public CourseServiceImpl(CourseDAO courseDAO, SectionDAO sectionDAO) {
        this.courseDAO = courseDAO;
        this.sectionDAO = sectionDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addCourse(Course course) {
        try {
            courseDAO.addCourse(course);
        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateCourse(Course course) {
        try {
            courseDAO.updateCourse(course);
        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Course getCourseById(int Id) {
        try {
            return courseDAO.getCourse(Id);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void enableOrdisableCourse(Course course) {
        try {
            if (course.isActive()) {
                course.setActive(false);
            } else {
                course.setActive(true);
            }
            courseDAO.updateCourse(course);
        } catch (Exception e) {

        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Course> searchCoursesbyName(String courseName) {
        try {
            return courseDAO.getCoursesWith(courseName);
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Course> getListOfActiveCourses() {
        try {
            return courseDAO.getAllActiveCourses();
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Course> getListOfCourses() {
        try {
            return courseDAO.getAllCourses();
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Section> getCourseSections(Course course) {
        try {
            return sectionDAO.getCourseSections(course);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void requestWaiver(Course course, Customer customer, String reason) {
        
        Waiver waiver= new Waiver();
        waiver.setCustomer(customer);
        waiver.setRequestReason(reason);
        waiver.setStatus("PENDING");
        
        courseDAO.addWaiver(waiver);
        
    }
}
