/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Enrollment;
import java.util.List;

/**
 *
 * @author Fetiya
 */
public interface EnrollmentDAO {
   
     public void addEnrollment(Enrollment enrollment);
     
     public void updateCourse(Enrollment enrollment);
     
     public Enrollment getEnrollment(int id);
     
     public List<Enrollment> getAllEnrollments();
}
