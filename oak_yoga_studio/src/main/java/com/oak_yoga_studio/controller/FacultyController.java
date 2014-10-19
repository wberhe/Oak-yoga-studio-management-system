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
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        System.out.println("Faculty retrieved from Session");
        model.addAttribute("waiverRequests", facultyService.getfacultyWaiverRequests(f));
        return "facultywaiverRequests";
    }

    @RequestMapping(value = "/decideOnWaiver/{waiver_Id}", method = RequestMethod.POST)
    public String decideOnWaiver(Model model, @PathVariable int waiver_Id, String waiverDecision, HttpSession session) {
       System.out.println(" Atiye   "+ waiver_Id+" decision -"+waiverDecision);
        Waiver w = customerService.getWaiverRequest(waiver_Id);
        
        System.out.println(" waiver   "+ w.getId());
        if (waiverDecision.equals("accepted")) {
            w.setStatus(Waiver.Status.ACCEPTED);
        } else {
            w.setStatus(Waiver.Status.REJECTED);
        }
        facultyService.updateWaiverRequest(w);
        
        return "facultywaiverRequests";
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

}
