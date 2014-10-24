/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Admin;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Credential;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.service.ICourseService;
import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.service.IFacultyService;
import com.oak_yoga_studio.service.INotificationService;
import com.oak_yoga_studio.service.ISectionService;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Weldu
 */
@Controller
public class AdminController {
    
    @Resource
    private INotificationService notificationService;
    @Resource
    private ICourseService courseService;
    @Resource
    private ISectionService sectionService;
    @Resource
    private IFacultyService facultyServcie;
    @Resource
    private ICustomerService customerService;
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }
    
    
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String addCourse(@ModelAttribute("course") Course course, HttpSession session,Model model) {
           model.addAttribute("ALLCourses", courseService.getListOfCourses());
           return "addCourse";
    }
    
    @RequestMapping(value="/addCourse", method=RequestMethod.POST)
    public String addCourse(@Valid Course course,BindingResult result,HttpSession session){
        String view="redirect:/showPrerequisite";
          /**
           * Binding is the secret,,bind prerequisite with the course
           */
                if(!result.hasErrors()){
                    course.setActive(true);
                    courseService.updateCourse(course);
                }else{
                     view="addCourse";
                }
                return view;
    }
    
    @RequestMapping(value = "/showPrerequisite", method = RequestMethod.GET)
    public String showPrerequisite(Model model, HttpSession session) {
           model.addAttribute("courses", courseService.getListOfCourses());
           return "ViewAllCourses";
    }
    
    
    @RequestMapping(value = "/viewCustomers", method = RequestMethod.GET)
    public String getAllCustomers(Model model,HttpSession session) {
        
       //TODO use session on login
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customersList";
    }
    
    @RequestMapping(value = "/customerDetail/{id}", method = RequestMethod.GET)
    public String getUserDetail(Model model, @PathVariable int id) {
        model.addAttribute("customerDetail", customerService.getCustomerById(id));
        return "customerDetail";
    }
    
    
  /**
   * ADD FACULTY
   * 
   * @param credential
   * @return 
   */  
    
    @RequestMapping(value = "/addFacultyCredential", method = RequestMethod.GET)
    public String addCredential(@ModelAttribute("credential") Credential credential) {
        credential.setRole("Please don't change");
        return "addFacultyCredential";
    }

    @RequestMapping(value = "/addFacultyCredential", method = RequestMethod.POST)
    public String addCredential(@Valid Credential credential, BindingResult result, HttpSession session) {
        String view = "redirect:/addFaculty";
        if (!result.hasErrors()) {
            //Faculty f = (Faculty) session.getAttribute("loggedUser");
            credential.setRole("ROLE_FACULTY");
            credential.setActive(false);
            session.setAttribute("credential", credential);
        } else {
            view = "addFacultyCredential";
        }
        return view;
    }
    
    
    @RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
    public String addFaculty(@ModelAttribute("faculty") Faculty faculty, HttpSession session) {
        faculty.setCredential((Credential) session.getAttribute("credential"));
        
        System.out.println("hello addFaculty GET");
        return "addFaculty";
    }

    @RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
    public String addFaculty(Faculty faculty, BindingResult result, HttpSession session, RedirectAttributes flashAttr, @RequestParam("file") MultipartFile file) {
        String view = "redirect:/viewFaculties";

        if (!result.hasErrors()) {
            try {
                faculty.setProfilePicture(file.getBytes());
            } catch (IOException ex) {
                //Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            faculty.getCredential().setActive(false);
            faculty.setActive(false);
            facultyServcie.addFaculty(faculty);
            session.removeAttribute("credential");
            flashAttr.addFlashAttribute("successful registered", "Faculty signed up succesfully. please  log in to proceed"); //           Customer c=(Customer) session.getAttribute("loggedCustomer");

        } else {
            for (FieldError err : result.getFieldErrors()) {
                System.out.println("Error:" + err.getField() + ":" + err.getDefaultMessage());
            }
            view = "addFaculty";
        }
        return view;
    }
    
    /**
     * VIEW FACULTIES
     */
    
    @RequestMapping(value = "/viewFaculties" , method = RequestMethod.GET)
    public String getAllFaculties(Model model,HttpSession session) {
       //TODO use session on login
        model.addAttribute("faculties", facultyServcie.getListOfFaculty());
        return "viewFaculties";
    }
    
    /**
     * ENABLE OR DISABLE FACULTY
     */
    
     @RequestMapping(value = "/faculties/{id}/{operation}", method = RequestMethod.GET)
    public String EnableDisable(@PathVariable("id") int facultyId, @PathVariable("operation") String operation) {

        Faculty f=facultyServcie.getFacultyById(facultyId);
        if ("enable".equalsIgnoreCase(operation)) {
            System.out.println("enabled");
            f.getCredential().setActive(true);
            //f.getCredential().setUserName(f.getCredential().getUserName());
        } else {
            System.out.println("disabled");
            f.getCredential().setActive(false);
        }
        facultyServcie.updateFaculty(facultyId,f);
        //userService.updateUserInfo(userId, u);
        return "redirect:/viewFaculties";
    }
    
    /**
     * 
     * SECTION MANAGMENT
     */
    /**
     *  I NEED TO CREATE THE sectionList/{id} tomorrow
     * ??TODO..............
     * 
     */
    
    @RequestMapping(value = "/newSection", method = RequestMethod.GET)
    public String showCreatedSection(Model model) {
//        Course course=courseService.getCourseById(id);
        model.addAttribute("section", new Section());
//        model.addAttribute("course", course);
        model.addAttribute("allCourses", courseService.getListOfCourses());
        model.addAttribute("allFaculities", facultyServcie.getListOfFaculty());
        return "createSection";
        
    }
    
    @RequestMapping(value = "/sectionList",method=RequestMethod.GET)
    public String sectinLists(Model model){
        model.addAttribute("sections",sectionService.getListOfSections());
        return "sectionList";
    }
    
    
    
    @RequestMapping(value = "/newSection", method = RequestMethod.POST)
    public String createSection(@Valid Section section,BindingResult result,Model model) {
          
        /**
         * //dump fix, I almost hate my self...damnnn
         * ,,,here the faculty and course are binded with section
         */
        String view = "redirect:/sectionList";
        
        if (result.hasErrors()) {
            view = "createSection";
           
        } else {
            section.setStatus(Section.Status.OPEN);
            section.getProfessor().addSection(section);
            section.getCourse().addSection(section);
            sectionService.updateSection(section);
         }
        return view;
    }
    
    /**
     * DELETING A SECTION
     * @param model
     * @param id
     * @param redattr
     * @return 
     */
    @RequestMapping(value = "/deleteSection", method = RequestMethod.POST)
    public String deleteSection(Model model, String[] ids) {
        
        List<Section> sections = new ArrayList<Section>();
        if (ids != null) {
            for (String id : ids) {
                sections.add(sectionService.getSectionById(Integer.parseInt(id)));
            }
        }
        for(Section s : sections){
            sectionService.deleteSection(s);
            
        }
        
        model.addAttribute("sections",sectionService.getListOfSections());
        return "sectionList";
    }
    
    /**
     * 
     * @param binder 
     */
    
    	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
//		binder.registerCustomEditor(Integer.class, "totalSeat",
//				new PropertyEditorSupport() {
//
//					@Override
//					public void setAsText(String text) {
//						Integer totalSeat= Integer.parseInt(text);
//						setValue(totalSeat);
//					}
//				});
//		
//		binder.registerCustomEditor(Long.class, "id",
//				new PropertyEditorSupport() {
//
//					@Override
//					public void setAsText(String text) {
//						Long id= Long.parseLong(text);
//						setValue(id);
//					}
//				});
//
//		
		
	binder.registerCustomEditor(List.class, "prerequisites",
			new CustomCollectionEditor(List.class) {
				@Override
				protected Object convertElement(Object element) {
					Integer id = null;
					if (element instanceof String
							&& !((String) element).equals("")) {
						// From the JSP 'element' will be a String
						try {
							id = Integer.parseInt((String) element);
						} catch (NumberFormatException e) {
							System.out.println("Element was "
									+ ((String) element));
							e.printStackTrace();
						}
					} else if (element instanceof Integer) {
						// From the database 'element' will be a int
						id = (Integer) element;
					}
					return id != null ? courseService.getCourseById(id): null;
							
				}
			});
            
	binder.registerCustomEditor(Faculty.class, "professor",
			new PropertyEditorSupport() {

				@Override
				public void setAsText(String facultyID) {
					Faculty faculty = facultyServcie.getFacultyById(Integer.parseInt(facultyID));
							
					setValue(faculty);
				}
			});
	
	binder.registerCustomEditor(Course.class, "course",
			new PropertyEditorSupport() {

				@Override
				public void setAsText(String courseID) {
					Course course = courseService.getCourseById(Integer.parseInt(courseID));							
					setValue(course);
				}
			});
	}


    
}
