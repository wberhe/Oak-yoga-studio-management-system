/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Enrollment;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Section;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fetiya
 */
public interface EnrollmentDAO {
   
     public void addEnrollment(Enrollment enrollment);
     
     public void addEnrollment(Enrollment.statusType status, Customer customer, Section section);
     
     public void updateCourse(Enrollment enrollment);
     
     public Enrollment getEnrollment(int id);
     
     public List<Enrollment> getAllEnrollments();
     
     public List<Enrollment> getEnrollmentsByCustomer(Customer cusomer);
     
     public List<Enrollment> getEnrollmentsByCustomerID(int customerId);
     
     public List<Section> getSections(Course course);

     public boolean checkSeatAvailablity(int sectionID);
     
     public List<Course> getAllCourses();
     
     public void saveEnrollment(Enrollment enrollment);
         
     public void addWaitingListEnrollment(Enrollment enrollment);
             
     public List<Course> getCoursesTaken(int customerID);
     
     public void withdraw(Customer customer, Section section);
     
     public Enrollment getTopWaitingList(int sectionId);
     
     public  void changeEnrollmentStatus(String status);
     
     public boolean isFirstTimeEnrollment(Customer customer) ;
     
      public boolean isExistingEnrollment(Customer customer,Section section);
}
