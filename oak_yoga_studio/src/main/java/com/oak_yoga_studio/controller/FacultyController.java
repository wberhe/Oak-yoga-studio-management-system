/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Waiver;
import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.service.IFacultyService;
import com.oak_yoga_studio.service.INotificationService;
import com.oak_yoga_studio.service.ISectionService;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Senai
 */
@Controller
public class FacultyController {

    @Resource
    private INotificationService notificationService;
    @Resource
    private IFacultyService facultyService;
    @Resource
    private ICustomerService customerService;
    @Resource
    private ISectionService sectionService;

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String faculty() {
        return "faculty";
    }

    
    @RequestMapping(value = "/facultyProfile", method = RequestMethod.GET)
    public String getUserDetail(@ModelAttribute("customerUpdate") Faculty facultyUpdate,Model model, HttpSession session) {
        System.out.println("begininnnnnnnnnnnnnnnnnnnnnnnnnn");
        Faculty loggedFaculty = (Faculty) session.getAttribute("loggedUser");
        model.addAttribute("facultyDetail", facultyService.getFacultyById(loggedFaculty.getId()));
        System.out.println("in between ewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
//        Address a = customerService.getCutomerAdress(loggedCustomer.getId());
//        if (a != null) {
//            model.addAttribute("addressDetail", a);
//        } else {
//            model.addAttribute("addressDetail", new Address());
//        }
        return "facultyProfile";
    }

    @RequestMapping(value = "/updateFacultyProfile", method = RequestMethod.POST)
    public String updateUser(@Valid Faculty facultyUpdate, BindingResult result, HttpSession session, RedirectAttributes flashAttr, @RequestParam("file") MultipartFile file) {
        String view = "redirect:/";

        if (!result.hasErrors()) {
            int Id = ((Faculty) session.getAttribute("loggedUser")).getId();
            Faculty faculty = facultyService.getFacultyById(Id);

            faculty.setFirstName(facultyUpdate.getFirstName());
            faculty.setLastName(facultyUpdate.getLastName());
            //System.out.println("Date of Birth" + customerUpdate.getDateOfBirth());
            //customer.setDateOfBirth(customerUpdate.getDateOfBirth());
            faculty.setEmail(facultyUpdate.getEmail());

            try {
                System.out.println("Imageeeeeeeeeee - " + file.getBytes());
                if (file.getBytes().length != 0) {
                    faculty.setProfilePicture(file.getBytes());
                }
            } catch (IOException ex) {

            }

            facultyService.updateFaculty(Id, faculty);
        } else {
            for (FieldError err : result.getFieldErrors()) {
                System.out.println("Error from UpdateProfileController " + err.getField() + ": " + err.getDefaultMessage());
            }
            System.out.println("err");
        }
        return "redirect:/facultyProfile";
    }

    
    @RequestMapping(value = "/viewAdvisees", method = RequestMethod.GET)
    public String viewAdvisees(Model model, HttpSession session) {

        Faculty f = (Faculty) session.getAttribute("loggedUser");
        model.addAttribute("advisees", facultyService.getfacultyAdvisees(f));
        // model.addAttribute("advisees", f.getAdvisees());
        return "AdviseesList";
    }

    @RequestMapping(value = "/adviseeDetail/{id}", method = RequestMethod.GET)
    public String getAdviseeDetail(Model model, @PathVariable int id) {
        model.addAttribute("adviseeDetail", customerService.getCustomerById(id));
        return "adviseeDetail";
    }

    @RequestMapping(value = "/viewWaiverRequests", method = RequestMethod.GET)
    public String viewWaiverRequests(Model model, HttpSession session) {
        Faculty f = (Faculty) session.getAttribute("loggedUser");
        model.addAttribute("waiverRequests", facultyService.getfacultyPendingWaiverRequests(f));
        model.addAttribute("waivers", facultyService.getfacultyDecidedWaivers(f));
        return "facultywaiverRequests";
    }

    @RequestMapping(value = "/decideOnWaiver/{waiver_Id}", method = RequestMethod.POST)
    public String decideOnWaiver(Model model, @PathVariable int waiver_Id, String waiverDecision, HttpSession session) {
        Waiver w = customerService.getWaiverRequest(waiver_Id);
        
        System.out.println(" waiver   "+ w.getId());
        if (waiverDecision.equals("accepted")) {
            w.setStatus(Waiver.Status.ACCEPTED);
            System.out.println("accepted");
        } else {
            w.setStatus(Waiver.Status.REJECTED);
            System.out.println("rejected");
        }
        facultyService.updateWaiverRequest(w);
        System.out.println("done");
        return viewWaiverRequests(model,session);
    }

    @RequestMapping(value = "/viewFacultySections", method = RequestMethod.GET)
    public String viewfacultySections(Model model, HttpSession session) {
        Faculty f = (Faculty) session.getAttribute("loggedUser");
        model.addAttribute("facultySections", facultyService.getFacultySections(f));
        return "facultySections";
    }

    @RequestMapping(value = "/sectionDetail/{id}", method = RequestMethod.GET)
    public String getSectionDetail(Model model, @PathVariable int id) {
        model.addAttribute("sectionDetail", sectionService.getSectionById(id));
        return "sectionDetail";
    }

     @RequestMapping(value = "/facultyProfileImage/{id}", method = RequestMethod.GET)
    public void getProfileImage(Model model, @PathVariable int id, HttpServletResponse response) {
        try {
            Faculty f = facultyService.getFacultyById(id);
            if (f != null) {
                OutputStream out = response.getOutputStream();
                out.write(f.getProfilePicture());
                response.flushBuffer();
            }
        } catch (IOException ex) {
            // Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
