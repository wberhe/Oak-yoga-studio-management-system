/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.service.IFacultyService;
import com.oak_yoga_studio.service.INotificationService;
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
    
    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public String faculty(){
        return "faculty";
    }
    
    @RequestMapping(value = "/viewAdvisees", method = RequestMethod.GET)
    public String viewAdvisees(Model model,@PathVariable int id,HttpSession session){
        System.out.println("I am here");
        model.addAttribute("advisees", facultyService.getFacultyById(id));
                           
        return "AdviseesList"; 
    }
    
//    public String addCourse(@ModelAttribute("course") Course course, HttpSession session) {
//        //customer.setCredential((Credential) session.getAttribute("credential"));
//        //System.out.println("hello signup");
//        return "addCourse";
//    }
}