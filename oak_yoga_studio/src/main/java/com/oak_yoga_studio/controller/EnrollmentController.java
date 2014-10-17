/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Enrollment;
import com.oak_yoga_studio.domain.Order;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.domain.ShoppingCart;
import com.oak_yoga_studio.domain.Waiver;
import com.oak_yoga_studio.service.ICourseService;
import com.oak_yoga_studio.service.IEnrollmentService;
import com.oak_yoga_studio.service.ISectionService;
import com.oak_yoga_studio.service.impl.SectionServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Fetiya
 */
@Controller
public class EnrollmentController {

    @Resource
    private ICourseService courseService;

    @Resource
    private IEnrollmentService enrollmentService;
    
      @Resource
     private ISectionService sectionService;
      
      @Resource EnrollmentHelperController enrollmentHelperController;
      
  ;
 
      
   @RequestMapping(value = "enrollInCourse/{id}", method = RequestMethod.GET)
    public String getUserDetail(Model model, @PathVariable int id) {
        
        System.out.println("Enorll hwere are you");
      //  model.addAttribute("sectionDetail",sectionService.getSectionById(id));
     
        
       Course course= (sectionService.getSectionById(id)).getCourse();
       
       
       //// Here customer should be changed with the current logged in custerm . 
       ///!!!!!!!!Dont' forget
       
      Customer customer= new Customer();
       if(checkPrerequisiteQualification(customer,course ))
       {
        return "enrollInCourse";
       }
       else
       {
              return "index"; 
        }
    }

    @RequestMapping(value = "/viewCourses", method = RequestMethod.GET)
    public String getCourses(Model model) {
        // System.out.println("Controller"+id);
        model.addAttribute("courses", courseService.getListOfCourses());

        return "ViewAllCourses";
    }

    @RequestMapping(value = "/viewSections/{id}", method = RequestMethod.GET)
    public String getCourseSections(Model model, @PathVariable int id) {

       Course course = courseService.getCourseById(id);
       model.addAttribute("sections", sectionService.getSectionsByCourse(course));
       
        return "ViewSections";
    }
    
    
    public boolean checkPrerequisiteQualification(Customer customer,Course course )
    {
      
      return   enrollmentHelperController.checkPrerequisiteQualification(customer, course);
        
    }
    
    
    public void enroll(Customer customer,Section section)
    {
       if(enrollmentService.checkSeatAvailablity(section.getId()))
       { 
   
           enrollmentService.addEnrollment(Enrollment.statusType.ACTIVE, customer, section);
       }
    }

}
