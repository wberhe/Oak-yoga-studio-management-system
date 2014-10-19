/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Credential;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Enrollment;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.service.ICourseService;
import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.service.IEnrollmentService;
import com.oak_yoga_studio.service.ISectionService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Resource
    private EnrollmentHelperController enrollmentHelperController;

    @Resource
    private ICustomerService customerService;

    @RequestMapping(value = "enrollInCourse/{id}", method = RequestMethod.GET)
    public String enrollSec(Model model, @PathVariable int id, HttpSession session, RedirectAttributes redirectattribute) {

      //  model.addAttribute("sectionDetail",sectionService.getSectionById(id));
        Section section = sectionService.getSectionById(id);
        Course course = section.getCourse();

        Customer customer = (Customer) session.getAttribute("loggedUser");

        String message;
        
        System.out.println("Before going to CheckPrerequisitesQualification " );
       // System.out.println( checkPrerequisiteQualification(customer, course) );
        
        if (checkPrerequisiteQualification(customer, course)) {
       
            System.out.println("Prerequisite Qualifeid");

            model.addAttribute("section", section);

            if (enrollmentService.checkSeatAvailablity(section.getId())) {
                enrollmentService.addEnrollment(Enrollment.statusType.ACTIVE, customer, section);

                int seats = section.getAvailableSeat();

                section.setAvailableSeat(--seats);
                sectionService.updateSection(section);

                //check if this is the first time enrollment for the  customer
                if (enrollmentService.getEnrollmentsByCustomer(customer).size() < 2) {

                    Faculty advisor = enrollmentService.getAvailableAdvisor();

                    customer.setAdvisor(advisor);

                    customerService.updateCustomer(customer.getId(), customer);

                    advisor.addAdvisee(customer);

               //display success with advisor info
                    message= "Successfully enrolled to section. " + section.getSectionName() 
                            + "and your advisor is " + advisor.getFirstName() + " " + advisor.getLastName();
                     model.addAttribute("message", message);
                     return "registrationSuccess";
                } else {
                    message= "Congratulations. Now you are successfully enrolled in yoga class";
                   model.addAttribute("message", message);
                     return "registrationSuccess"; 

                }
            } //if there is no available seat for a section ask customer to be inclued in waiting list
            else {
                //redirect message
                message= "There is no seat available for the selected section . Would you like to be added in the waitinglist? ";
                model.addAttribute("section", section);
                model.addAttribute("message", message);
                return "toWaitingList";
            }

        } else {
            System.out.println("Prerequisite not qualified");
            message = "Your transcript shows you didn't complete prerequistes for this section"
                    + "Would you like to request a waiver ?";
            model.addAttribute("message", message);
            model.addAttribute("course", course);
            return "toWaiverRequst";
            

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

    public boolean checkPrerequisiteQualification(Customer customer, Course course) {

        
        return enrollmentHelperController.checkPrerequisiteQualification(customer, course);

    }


    
     @RequestMapping(value = "/waitingListSuccess", method = RequestMethod.GET)
    public String addToWaitingList(@ModelAttribute("section") Section section, HttpSession session) {
     
        return "waitingListSuccess";
    }
    
    @RequestMapping(value="/addToWaitingList/{section}", method=RequestMethod.POST)
    public String addToWaitingList(@Valid Section section,BindingResult result,HttpSession session){
        String view="redirect:/";
        
        System.out.println("Running inside addToWaitingList post");
         Customer customer = (Customer) session.getAttribute("loggedUser");
        
                if(!result.hasErrors()){
                   
                    toWaitingList(customer, section);
                   view="redirect:/waitingListSuccess";
                }else{
                    // view="addCourse";
                }
                return view;
    }

    
    
    
    public void toWaitingList(Customer customer, Section section) {
        enrollmentService.addEnrollment(Enrollment.statusType.WAITINGLIST, customer, section);

    }
}
