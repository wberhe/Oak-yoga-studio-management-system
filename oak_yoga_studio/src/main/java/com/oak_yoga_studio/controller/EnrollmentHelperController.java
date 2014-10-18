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

    public boolean checkPrerequisiteQualification(Customer customer, Course course) {

       
        List<Course> prerequisites = course.getPrerequisites();

        
               
        if (prerequisites.isEmpty()) {
             System.out.println("size is " + prerequisites.size());
             return true;
        } else {

          ///verify prerequisiteCompletion
            List<Course> coursesTaken = enrollmentService.getCoursesTaken(customer.getId());
            List<Waiver> waivers = customer.getWaivers();

            /* First prerequisite list will be compared with list of courses taken and 
             if there is any which isn't found in coursesTaken list , it will be added to temp
             so that to be checked agains the wavierList. 
             */
            ArrayList<Integer> temp;
            temp = new ArrayList<Integer>();

            //compare prerequisites and coursesTaken
            for (Course pre : prerequisites) {

                boolean found = false;

                for (Course c : coursesTaken) {
                    if (c == pre) {
                        found = true;
                        break;
                    }

                }

                if (!found) {
                    temp.add(pre.getId());

                }

            }

            //checkprerequisites and CoursesWavied
            if (!temp.isEmpty()) {
                for (Course pre : prerequisites) {

                    boolean found = false;

                    for (int c : temp) {
                        if (c == pre.getId()) {
                            found = true;
                            break;
                        }

                    }

                    if (!found) {
                        return false;

                    }

                }
            }

            return false;

        }

    }
    
 
 


}
