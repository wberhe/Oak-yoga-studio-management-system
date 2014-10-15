/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import java.util.List;

/**
 *
 * @author Fetiya
 */
public interface CourseDAO {
    
     public void addCourse(Course course);
     
     public void updateCourse(Course course);
     
     public Course getCourse(int id);
     
     public List<Course> getCoursesWith(String words);
     
     public List<Course> getAllActiveCourses();
     
     public List<Course> getAllCourses();
     
      public List<Course> getWaivers(Customer customerID);
}
