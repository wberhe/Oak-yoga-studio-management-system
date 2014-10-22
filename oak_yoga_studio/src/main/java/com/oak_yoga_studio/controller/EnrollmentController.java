/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Course;
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
import org.springframework.aop.Advisor;
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

       //CheckPrerequisitesQualification 
      
        
        if (checkPrerequisiteQualification(customer, course)) {

            System.out.println("Prerequisite Qualifeid");

            model.addAttribute("section", section);

            // check if there is available seat
            if (isSeatAvailable(section)) {
           /////////  if (enrollmentService.checkSeatAvailablity(section.getId())) {

                //check if this is the first time enrollment for the  customer and assign advisor
                Faculty advisor = handleFirstTimeEnrollment(customer);

                if (!enrollmentService.isExistingEnrollment(customer, section)) {

                    enrollmentService.addEnrollment(Enrollment.statusType.ACTIVE, customer, section);

                  //  int seats = section.getAvailableSeat();
                 //   section.setAvailableSeat(--seats);
                    //    sectionService.updateSection(section);
                    if (advisor != null) {

                        //display success with advisor info
                        message = "Successfully enrolled to section. " + section.getSectionName()
                                + "and your advisor is " + advisor.getFirstName() + " " + advisor.getLastName();
                        model.addAttribute("message", message);
                        return "enrollResult";
                    } else {
                        message = "Congratulations. Now you are successfully enrolled in yoga class";
                        model.addAttribute("message", message);
                        return "enrollResult";

                    }

                } else {
                    message = "Sorry. You can not be registered for section now because your record"
                            + " shows that you already have a current active registration for this section";
                    model.addAttribute("message", message);
                    return "enrollResult";
                }
            } //if there is no available seat for a section ask customer to be inclued in waiting list
            else {
                //redirect message
                message = "There is no seat available for the selected section . Would you like to be added in the waitinglist? ";
                model.addAttribute("section", section);
                model.addAttribute("message", message);
                return "toWaitingList";
            }

        } else {

      //Prerequisite not qualified
            message = "Your transcript shows you didn't complete prerequistes for this section"
                    + " Would you like to request a waiver ?";
            model.addAttribute("message", message);
            model.addAttribute("course", course);
           //return "toWaiverRequst";

         //   return "enrollResult";
            return "toWaiverRequest";

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
        model.addAttribute("course",course);

        return "ViewSections";
    }

    public boolean checkPrerequisiteQualification(Customer customer, Course course) {

        return enrollmentHelperController.checkPrerequisiteQualification(customer, course);

    }

    @RequestMapping(value = "/waitingListResult", method = RequestMethod.GET)
    public String addToWaitingList(@ModelAttribute("section") Section section, HttpSession session) {

        return "waitingListResult";
    }

    @RequestMapping(value = "/addToWaitingList/{section}", method = RequestMethod.POST)
    public String addToWaitingList(@Valid Section section, BindingResult result, HttpSession session) {
        String view = "redirect:/";

        System.out.println("Running inside addToWaitingList post");
        Customer customer = (Customer) session.getAttribute("loggedUser");

        if (!result.hasErrors()) {

            toWaitingList(customer, section);
            view = "redirect:/waitingListResult";
        } else {
            // view="addCourse";
        }
        return view;
    }

    @RequestMapping(value = "/waitingListResult/{id}", method = RequestMethod.POST)
    public String waitingListResult(Model model, @PathVariable int id, HttpSession session,
            RedirectAttributes re) {
        String view = "redirect:/";

        System.out.println("Running inside addToWaitingList post");
        Customer customer = (Customer) session.getAttribute("loggedUser");
        Section section = sectionService.getSectionById(id);

        if (!enrollmentService.isExistingEnrollment(customer, section)) {
            toWaitingList(customer, section);

            re.addFlashAttribute("section", section);
            re.addFlashAttribute("message", "You have been successfully added to the waiting list for");

        } else {
            re.addFlashAttribute("section", section);
            re.addFlashAttribute("message", "Sorry you couldn't be enrolled for this section. Your record "
                    + "shows you already have an active or in progress enrollment for this section ");

        }

        view = "redirect:/waitingListResult";

        return view;
    }

    @RequestMapping(value = "/requestWithdraw", method = RequestMethod.GET)
    public String requestWithdraw(Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("loggedUser");

        if (!enrollmentService.getEnrollmentsByCustomer(customer).isEmpty()) {

            model.addAttribute("sectionsTaken", enrollmentService.getEnrollmentsByCustomer(customer));
            model.addAttribute("msg", "Current courses to withdraw");
        } else {
            model.addAttribute("msg", "Your record shows there are no current courses that you are enrolled in.");
        }

        return "withdrawRequest";
    }

    @RequestMapping(value = "/enrolled", method = RequestMethod.GET)
    public String getEnrolledSections(Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("loggedUser");

        if (!enrollmentService.getEnrollmentsByCustomer(customer).isEmpty()) {

            model.addAttribute("sectionsTaken", enrollmentService.getEnrollmentsByCustomer(customer));
            model.addAttribute("msg", " Course Enrollment History");
        } else {
            model.addAttribute("msg", "You haven't been enrolled in any section");
        }

        return "courseHistory";
    }

    @RequestMapping(value = "/withdraw/{id}", method = RequestMethod.POST)
    public String withdraw(@PathVariable int id, Model model,
            HttpSession session, RedirectAttributes re) {

        Customer customer = (Customer) session.getAttribute("loggedUser");

        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        // Section section =  enrollment.getSection();
        Section section = enrollment.getSection();//sectionService.getSectionById(id);

        if (section.getStatus() == Section.Status.OPEN || section.getStatus() == Section.Status.INPROGRESS) {

            enrollmentService.withdraw(customer, section);

            //System check
            //check and add customer in top of waitingList to enrolled
            enrollTopWaitingList(section);

            re.addFlashAttribute("msg", "You are now withdrawn from section  ");
            re.addFlashAttribute("section", section);

        } else {
            re.addFlashAttribute("msg", "You can not withdraw from this section because "
                    + "its status is " + section.getStatus());
            re.addFlashAttribute("section", section);
        }

        return "redirect:/withdrawResult";
    }

    @RequestMapping("/withdrawResult")
    public String withdrawResult() {
        return "withdrawResult";
    }

    private void toWaitingList(Customer customer, Section section) {

        //add to waiting list if the customer is already registerd for this section currently.
        enrollmentService.addEnrollment(Enrollment.statusType.WAITINGLIST, customer, section);

    }

    public void enrollTopWaitingList(Section section) {

        Enrollment enrollment = enrollmentService.getTopWaitingList(section.getId());

        if (enrollment != null) {

            Faculty advisor = handleFirstTimeEnrollment(enrollment.getCustomer());

            enrollment.setStatus(Enrollment.statusType.ACTIVE);
            enrollmentService.saveEnrollment(enrollment);

        }

    }

    public Faculty handleFirstTimeEnrollment(Customer customer) {

        if (enrollmentService.isFirstTimeEnrollment(customer)) {

            Faculty advisor = enrollmentService.getAvailableAdvisor();

            customer.setAdvisor(advisor);

            customerService.updateCustomer(customer.getId(), customer);

            advisor.addAdvisee(customer);
            return advisor;
        }
        return null;
    }

    public boolean isSeatAvailable(Section section) {
        long enrolledTotal = enrollmentService.getEnrollmentsCountBySection(section);

        long seats = section.getCapacity() - enrolledTotal;

        if (seats > 0) {
            return true;
        } else {
            return false;
        }
    }

}
