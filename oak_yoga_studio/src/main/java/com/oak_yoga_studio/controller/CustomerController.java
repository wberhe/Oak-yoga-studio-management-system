/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.controller;

//
import com.oak_yoga_studio.domain.Address;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Credential;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.ShoppingCart;
import com.oak_yoga_studio.domain.Waiver;
import com.oak_yoga_studio.service.ICourseService;
import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.service.IEnrollmentService;
import com.oak_yoga_studio.service.INotificationService;
import com.oak_yoga_studio.service.impl.AddressServiceImpl;
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
 * @author Weldu
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

    @Resource
    private AddressServiceImpl addressService;

    @RequestMapping("/")
    public String redirectRoot() {
        return "redirect:/welcome";
    }

    @RequestMapping("/contact")
    public String redirectContact() {
        return "contact";
    }

    @RequestMapping("/about")
    public String redirectAbout() {
        return "about";
    }

    @RequestMapping("/welcome")
    public String redirectWelcome() {
        return "welcome";
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
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            customerService.addCustomer(customer);
            session.removeAttribute("credential");
            flashAttr.addFlashAttribute("successfulSignup", "Customer signed up succesfully. please  log in to proceed");

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

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String getUserDetail(@ModelAttribute("customerUpdate") Customer customerUpdate, Model model, HttpSession session) {//, @ModelAttribute("addressUpdate") Address addressUpdate) {

        Customer loggedCustomer = (Customer) session.getAttribute("loggedUser");
        model.addAttribute("customerDetail", customerService.getCustomerById(loggedCustomer.getId()));

        return "CustomerProfile";

    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateUser(@Valid Customer customerUpdate, BindingResult result, HttpSession session, RedirectAttributes flashAttr, @RequestParam("file") MultipartFile file) {
        String view = "redirect:/";

        if (!result.hasErrors()) {
            int Id = ((Customer) session.getAttribute("loggedUser")).getId();
            Customer customer = customerService.getCustomerById(Id);

            customer.setFirstName(customerUpdate.getFirstName());
            customer.setLastName(customerUpdate.getLastName());
            //System.out.println("Date of Birth" + customerUpdate.getDateOfBirth());
            //customer.setDateOfBirth(customerUpdate.getDateOfBirth());
            customer.setEmail(customerUpdate.getEmail());

            try {

                if (file.getBytes().length != 0) {
                    customer.setProfilePicture(file.getBytes());
                }
            } catch (IOException ex) {

            }

            customerService.updateCustomer(Id, customer);
        } else {
            for (FieldError err : result.getFieldErrors()) {
                System.out.println("Error from UpdateProfileController " + err.getField() + ": " + err.getDefaultMessage());
            }
            System.out.println("err");
        }
        return "redirect:/editProfile";
    }

    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public String updateAddress(@Valid Address addressUpdate, BindingResult result, HttpSession session, RedirectAttributes flashAttr) {
        String view = "redirect:/";

        if (!result.hasErrors()) {
            int Id = ((Customer) session.getAttribute("loggedUser")).getId();
            Address address = customerService.getCutomerAdress(Id);

            address.setStreet(addressUpdate.getStreet());
            address.setCity(addressUpdate.getCity());
            address.setZipCode(addressUpdate.getZipCode());
            address.setState(addressUpdate.getState());
            if (address.getId() == 0) {
                addressService.addAddress(address);
            } else {
                addressService.updateAddress(address);
            }
        } else {
            for (FieldError err : result.getFieldErrors()) {
                System.out.println("Error from UpdateProfileController " + err.getField() + ": " + err.getDefaultMessage());
            }
            System.out.println("err");
        }
        return "redirect:/editProfile";
    }

    @RequestMapping(value = "/requestWaiver", method = RequestMethod.GET)
    public String requestWaiver(Model model, HttpSession session) {

        //    model.addAttribute("courses", courseService.getListOfCourses());
        Customer customer = (Customer) session.getAttribute("loggedUser");

        List<Course> coursesToWaive = customerService.getAllCoursesToWaive(customer);
        if (!coursesToWaive.isEmpty()) {

            model.addAttribute("coursesToWaive", coursesToWaive);
            model.addAttribute("msg", " Courses Qualified to be waived ");
        } else {
            model.addAttribute("msg", " There are no courses that you can waive");
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
    public String sumbitRequest(@PathVariable int id, Model model, String text, String[] ids, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("loggedUser");
        Course course = courseService.getCourseById(id);

        courseService.requestWaiver(course, customer, text);

        model.addAttribute("msg", "Your Waiver request for course " + course.getCourseName() + " is successfuly saved");

        return "waiverResult";

    }

    @RequestMapping(value = "/viewWaivers", method = RequestMethod.GET)
    public String getCourses(Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("loggedUser");

        List<Waiver> allWaivers = customerService.getAllWaiversByCustomer(customer);
        System.out.println("waivers length is " + allWaivers.size());
        if (!allWaivers.isEmpty()) {

            model.addAttribute("waivers", allWaivers);
            model.addAttribute("msg", " courses with waiver status");
        } else {
            model.addAttribute("msg", " No records found ");
        }

        return "viewWaivers";
    }

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

    private List<Course> getAllCoursesToWaive(Customer customer) {
        return customerService.getAllCoursesToWaive(customer);
    }

    /**
     * Enabling and disabling the customer
     *
     * @param userId
     * @param operation
     * @return
     */
//    @RequestMapping(value = "/customers/{id}/{operation}", method = RequestMethod.GET)
//    public String EnableDisable(@PathVariable("id") int userId, @PathVariable("operation") String operation) {
//
//        User u = userService.getUser(userId);
//        if ("enable".equalsIgnoreCase(operation)) {
//            System.out.println("enabled");
//            u.getUserCredential().setBlocked(true);
//            u.getUserCredential().setUsername(u.getUserCredential().getUsername());
//        } else {
//            System.out.println("disabled");
//            u.getUserCredential().setActive(false);
//        }
//        userService.updateUserInfo(userId, u);
//        return "redirect:/";
//    }
    /**
     * Admin notification to All customers by email
     *
     * @param model
     * @param text
     * @param ids
     * @return to settings page
     */
//    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
//    public String notifyAllustomers(Model model, String text, String[] ids) {
//        List<Customer> customer = new ArrayList<Customer>();
//        if (ids != null) {
//            for (String id : ids) {
//                user.add(customerService.getCustomer(Integer.parseInt(id)));
//            }
//        }
//        notificationService.notifycustomer(customer, text);
//        model.addAttribute("allCustomers", customerService.getAllcustomers());
//        return "";
//    }
    /**
     *
     * @param customer
     * @param reason
     */
    private void requestWaiver(Customer customer, String reason) {

    }

    /**
     * In order to upload the users profile picture
     *
     * @param model
     * @param id
     * @param response
     */
    @RequestMapping(value = "/profileImage/{id}", method = RequestMethod.GET)
    public void getProfileImage(Model model, @PathVariable int id, HttpServletResponse response) {
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
}
