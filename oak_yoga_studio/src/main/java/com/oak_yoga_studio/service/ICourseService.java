/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Section;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface ICourseService {
    
    public void addCourse(Course course);

    public void updateCourse(Course course);

    public Course getCourseById(int Id);

    public void enableOrdisableCourse(Course course);
    
    public List<Course> getListOfActiveCourses();
    
    public List<Course> getListOfCourses();
    
    public List<Section> getCourseSections(Course course);

}
