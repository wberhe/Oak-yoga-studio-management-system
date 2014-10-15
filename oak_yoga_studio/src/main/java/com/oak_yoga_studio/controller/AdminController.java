/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.service.ICourseService;
import com.oak_yoga_studio.service.IFacultyService;
import com.oak_yoga_studio.service.INotificationService;
import com.oak_yoga_studio.service.IProductService;
import com.oak_yoga_studio.service.ISectionService;
import java.time.Clock;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Weldino
 */
@Controller
public class AdminController {
    
    @Resource
    private INotificationService notificationService;
    @Resource
    private ICourseService courseService;
//    @Resource
//    private ISectionService sectionService;
//    @Resource
//    private IProductService productServcie;
//    @Resource
//    private IFacultyService facultyServcie;
//    @Resource
//    private ICourseService customerService;
    
    
    
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String addCourse(@ModelAttribute("course") Course course, HttpSession session) {
        //customer.setCredential((Credential) session.getAttribute("credential"));
        //System.out.println("hello signup");
        return "addCourse";
    }
    
    @RequestMapping(value="/addCourse", method=RequestMethod.POST)
    public String addCourse(@Valid Course course,BindingResult result,HttpSession session){
        String view="redirect:/index";
        
        System.err.println("testtttttttt out");
                if(!result.hasErrors()){
                    course.setActive(true);
                    courseService.addCourse(course);
                    System.err.println("testtttttttt Inside");
                }else{
                    System.err.println("testtttttttt Inside else");
                     view="addCourse";
                     
                }
                return view;
    }
}
