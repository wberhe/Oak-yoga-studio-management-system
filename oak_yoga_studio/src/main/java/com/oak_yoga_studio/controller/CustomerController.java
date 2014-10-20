/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.controller;

import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Credential;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.ShoppingCart;
import com.oak_yoga_studio.domain.Waiver;
import com.oak_yoga_studio.service.ICourseService;
import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.service.IEnrollmentService;
import com.oak_yoga_studio.service.INotificationService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Weldino
 */
@Controller
public class CustomerController {

    @Resource
    private ICustomerService customerService;
    @Resource
    private INotificationService notificationService;

    @Resource
    private ICourseService courseService;

    @Resource
    private IEnrollmentService enrollmentService;

    @RequestMapping("/")
    public String redirectRoot() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("users", customerService.getAllCustomers());
        return "customerList";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public String addCustomer(@ModelAttribute("customer") Customer customer, HttpSession session) {
        customer.setCredential((Credential) session.getAttribute("credential"));
        System.out.println("hello signup");
        return "signup";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String add(@Valid Customer customer, BindingResult result, HttpSession session, RedirectAttributes flashAttr, @RequestParam("file") MultipartFile file) {
        String view = "redirect:/";
        //System.out.println("customerController Add");

        if (!result.hasErrors()) {
            try {
                customer.setProfilePicture(file.getBytes());
            } catch (IOException ex) {
                //Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            ShoppingCart cart= new ShoppingCart();
//            customer.setShoppingCart(cart);
            customerService.addCustomer(customer);
            session.removeAttribute("credential");
            flashAttr.addFlashAttribute("successfulSignup", "Customer signed up succesfully. please  log in to proceed");
            //           Customer c=(Customer) session.getAttribute("loggedCustomer");
//            if(c!=null && c.getUserCredential().isAdmin()){
//                view="redirect:/settings";

//            }
        } else {
            for (FieldError err : result.getFieldErrors()) {
                System.out.println("Error:" + err.getField() + ":" + err.getDefaultMessage());
            }
            view = "addCustomer";
        }
        return view;
    }

    @RequestMapping(value = "/addCredential", method = RequestMethod.GET)
    public String addCredential(@ModelAttribute("credential") Credential credential) {
        credential.setRole("Please don't change");
        return "addCredential";
    }

    @RequestMapping(value = "/addCredential", method = RequestMethod.POST)
    public String addCredential(@Valid Credential credential, BindingResult result, HttpSession session) {
        String view = "redirect:/addCustomer";
        //dumb fix
        boolean used = customerService.checkUserName(credential.getUserName());
        if (used) {
            FieldError f = new FieldError("credential", "userName", credential.getUserName(), false, null, null, "Username : " + credential.getUserName() + " already in use");
            result.addError(f);
        }
        if (!result.hasErrors()) {
            Customer c = (Customer) session.getAttribute("loggedUser");
            credential.setRole("ROLE_CUSTOMER");
            credential.setActive(true);
            session.setAttribute("credential", credential);

        } else {
            view = "addCredential";
        }
        return view;
    }

    @RequestMapping(value = "/editProfile/{id}", method = RequestMethod.GET)
    public String getUserDetail(Model model, @PathVariable int id) {
        model.addAttribute("customerDetail", customerService.getCustomerById(id));
        System.out.println("Hi this is udner editProfile   ");
        return "EditProfile";
    }

    @RequestMapping(value = "/updateProfile/{id}", method = RequestMethod.POST)
    public String updateUser(@Valid Customer customer, BindingResult result, @PathVariable int id, HttpSession session) {
        //System.out.println("Update");
        if (!result.hasErrors()) {

            session.setAttribute("customer", customer);
            System.out.println("Customer firstName " + customer.getFirstName());
            Credential c = (customerService.getCustomerById(id)).getCredential();
            System.out.println(" uswe name " + c.getUserName());
            customer.setCredential(c);

            System.out.println("Customer ID " + customer.getId());
            System.out.println("Customer LastName :" + customer.getLastName());
            System.out.println("Customer email" + customer.getEmail());
            System.out.println("Username" + customer.getCredential().getUserName());
            customerService.updateCustomer(id, customer);
            return "redirect:/index";
        } else {
            for (FieldError err : result.getFieldErrors()) {
                System.out.println("Error from UpdateProfileController " + err.getField() + ": " + err.getDefaultMessage());
            }
            System.out.println("err");
            return "index";
        }
    }

    @RequestMapping(value = "/requestWaiver", method = RequestMethod.GET)
    public String requestWaiver(Model model, HttpSession session) {

    //    model.addAttribute("courses", courseService.getListOfCourses());
        Customer customer = (Customer) session.getAttribute("loggedUser");

        if (!customerService.getAllCoursesToWaive(customer).isEmpty()) {

            model.addAttribute("coursesToWaive", customerService.getAllCoursesToWaive(customer));
            model.addAttribute("msg", " Courses Qualified to be waived ");
        } else {
            model.addAttribute("msg", " There is no course that you can waive");
        }

        return "waiverRequest";
    }

    @RequestMapping(value = "/waiverRequest/{id}", method = RequestMethod.POST)
    public String waiverForm(@PathVariable int id, Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("loggedUser");

        //    model.addAttribute("course", courseService.getCourseById(id));
        return "redirect:/waiverForm/{id}";
    }

    @RequestMapping(value = "/waiverForm/{id}", method = RequestMethod.GET)
    public String waiverForm(Model model, @PathVariable int id) {
        model.addAttribute("course", courseService.getCourseById(id));

        return "waiverForm";
    }

    @RequestMapping(value = "/waiverResult/{id}", method = RequestMethod.POST)
    public String waiverResult(@PathVariable int id, Model model, String text, String[] ids, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("loggedUser");
        Course course = courseService.getCourseById(id);

        courseService.requestWaiver(course, customer, text);

        model.addAttribute("msg", "Your Waiver request for course " + course.getCourseName() + " is successfuly saved");

        return "waiverResult";
    }

    /**
     * Enabling and disabling the user
     *
     * @param userId
     * @param operation
     * @return
     */
//    @RequestMapping(value = "/users/{id}/{operation}", method = RequestMethod.GET)
//    public String EnableDisable(@PathVariable("id") int userId, @PathVariable("operation") String operation) {
//
//        User u = userService.getUser(userId);
//        if ("enable".equalsIgnoreCase(operation)) {
//            System.out.println("enabled");
//            u.getUserCredential().setBlocked(true);
//            u.getUserCredential().setUsername(u.getUserCredential().getUsername());
//        } else {
//            System.out.println("disabled");
//            u.getUserCredential().setBlocked(false);
//        }
//        userService.updateUserInfo(userId, u);
//        return "redirect:/users";
//    }
    /**
     * Admin notification to bloggers by email
     *
     * @param model
     * @param text
     * @param ids
     * @return to settings page
     */
//    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
//    public String notifyUsers(Model model, String text, String[] ids) {
//        List<User> user = new ArrayList<User>();
//        if (ids != null) {
//            for (String id : ids) {
//                user.add(userService.getUser(Integer.parseInt(id)));
//            }
//        }
//        notificationService.notifyBlogger(user, text);
//        model.addAttribute("allusers", userService.getAllUsers());
//        return "settings";
//    }
    /**
     * In order to upload the users profile picture
     *
     * @param model
     * @param id
     * @param response
     */
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void getUserImage(Model model, @PathVariable int id, HttpServletResponse response) {
        try {
            Customer c = customerService.getCustomerById(id);
            if (c != null) {
                OutputStream out = response.getOutputStream();
                out.write(c.getProfilePicture());
                response.flushBuffer();
            }
        } catch (IOException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ModelAttribute("customer")
    public Customer loadEmptyModelBean() {
        Customer customer;
        customer = new Customer();
        return customer;
    }

    private void requestWaiver(Customer customer, String reason) {

    }

    private List<Course> getAllCoursesToWaive(Customer customer) {
        return customerService.getAllCoursesToWaive(customer);
    }

}
