/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Waiver;
import com.oak_yoga_studio.service.ICourseService;
import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.service.IEnrollmentService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Fetiya
 */
@Controller
public class EnrollmentHelperController {

    @Resource
    private ICourseService courseService;

    @Resource
    private IEnrollmentService enrollmentService;

    @Resource
    private ICustomerService customerService;

    public boolean checkPrerequisiteQualification(Customer customer, Course course) {

        List<Course> prerequisites = course.getPrerequisites();

        if (prerequisites.isEmpty()) {
            System.out.println("size is " + prerequisites.size());
            return true;
        } else {

            ///verify prerequisiteCompletion
            List<Course> coursesTaken = enrollmentService.getCoursesTaken(customer.getId());

            System.out.println("Courses Taken returned  " + coursesTaken.size());

            List<Waiver> waivers = customerService.getApprovedWaiversByCustomerID(customer.getId());

            System.out.println("Waivers returned" + waivers.size());

            System.out.println("..............................................."
                    + "preffeuisits found   ");
            for (Course pre : prerequisites) {
                System.out.println("course Id " + pre.getId());
            }
               
                System.out.println("..............................................."
                    + "courses taken found   ");
                
                for (Course tak : coursesTaken) {
                System.out.println("course Id " + tak.getId());
            }
            /* First prerequisite list will be compared with list of courses taken and 
             if there is any which isn't found in coursesTaken list , it will be added to temp
             so that to be checked agains the wavierList. 
             */
           
            //compare prerequisites and coursesTaken
            
            
            for (Course pre : prerequisites) {

                if(!(isInCoursesTaken(pre, coursesTaken) || isInCoursesWaived(pre, waivers) ))
                        {
                            return false;
                        }
            }

        }
        
        return true;
        
    
    }
        
        private boolean isInCoursesTaken(Course pre, List<Course> coursesTaken )
        {
             for (Course c : coursesTaken) {
                    if (c.getId() == pre.getId()) {
                      return true;
                    
                    }

                }

            return false;
            
        }
        
        private boolean isInCoursesWaived(Course pre, List<Waiver> waivers )
        {
            
             for (Waiver w : waivers) {
                    if (w.getWaiverCourse().getId()== pre.getId()) {
                      return true;
                    
                    }

                }
            return false;
        }
    

}
