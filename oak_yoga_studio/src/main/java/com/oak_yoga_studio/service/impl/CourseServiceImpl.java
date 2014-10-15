/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.CourseDAO;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.service.ICourseService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Senai
 */
public class CourseServiceImpl implements ICourseService {

    CourseDAO courseDAO;

    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public void addCourse(Course course) {
        try {
            courseDAO.addCourse(course);
        } catch (Exception e) {

        }
    }

    @Override
    public void updateCourse(Course course) {
        try {
            courseDAO.updateCourse(course);
        } catch (Exception e) {

        }
    }

    @Override
    public Course getCourseById(int Id) {
        try {
            return courseDAO.getCourse(Id);
        } catch (Exception e) {
            return null;
        }
    }

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

    public List<Course> searchCoursesbyName(String courseName) {
        try {
            return courseDAO.getCoursesWith(courseName);
        } catch (Exception e) {
            return new ArrayList();
        }
    }
    
    @Override
    public List<Course> getListOfActiveCourses() {
        try {
            return courseDAO.getAllActiveCourses();
        } catch (Exception e) {
            return new ArrayList();
        }
    }
    
    @Override
    public List<Course> getListOfCourses() {
        try {
            return courseDAO.getAllCourses();
        } catch (Exception e) {
            return new ArrayList();
        }
    }

}
