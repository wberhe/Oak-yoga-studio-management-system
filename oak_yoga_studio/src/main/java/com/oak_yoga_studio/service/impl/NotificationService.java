/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.service.INotificationService;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author Weldu
 */
public class NotificationService implements INotificationService {
    
    private SimpleMailMessage toFacultiesTemplate;
    private SimpleMailMessage toCustomersTemplate;
    private SimpleMailMessage toAdvisorTemplate;
    private SimpleMailMessage toAdminTemplate;
    
    private JavaMailSenderImpl javaMailSender;

    public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public SimpleMailMessage getToFacultiesTemplate() {
        return toFacultiesTemplate;
    }

    public void setToFacultiesTemplate(SimpleMailMessage toFacultiesTemplate) {
        this.toFacultiesTemplate = toFacultiesTemplate;
    }

    public SimpleMailMessage getToCustomersTemplate() {
        return toCustomersTemplate;
    }

    public void setToCustomersTemplate(SimpleMailMessage toCustomersTemplate) {
        this.toCustomersTemplate = toCustomersTemplate;
    }

    public SimpleMailMessage getToAdvisorTemplate() {
        return toAdvisorTemplate;
    }

    public void setToAdvisorTemplate(SimpleMailMessage toAdvisorTemplate) {
        this.toAdvisorTemplate = toAdvisorTemplate;
    }

    public SimpleMailMessage getToAdminTemplate() {
        return toAdminTemplate;
    }

    public void setToAdminTemplate(SimpleMailMessage toAdminTemplate) {
        this.toAdminTemplate = toAdminTemplate;
    }
    
   /**
    * Begin email sending implementation, notify advisor
    * @param faculty
    * @param message 
    */ 

    @Override
    public void notifyAdvisor(Faculty faculty, String message) {
        
        SimpleMailMessage template= getToAdvisorTemplate();
        String emailMessage = String.format(template.getText(),message, faculty.getFirstName() + " " + faculty.getLastName());
        sendMail(faculty.getEmail(), template.getFrom(), template.getSubject(), emailMessage);
       
        
    }
    /**
     * Notify all Faculties by administrator
     * @param faculties
     * @param message 
     */
    @Override
    public void notifyFaculties(List<Faculty> faculties, String message) {
        
         SimpleMailMessage template = getToFacultiesTemplate();
        for (Faculty faculty : faculties) {
            String emailMessage = String.format(template.getText(), faculty.getFirstName() + " " + faculty.getLastName(),message);
            sendMail(template.getFrom(), faculty.getEmail(), template.getSubject(), emailMessage);
        }
        
    }
    /**
     * Notify customer
     * @param customer
     * @param message 
     */

    @Override
    public void notifyCustomer(Customer customer, String message) {
        SimpleMailMessage template=getToCustomersTemplate();
            String emailMessage = String.format(template.getText(), customer.getFirstName() + " " + customer.getLastName(),message);
            sendMail(template.getFrom(), customer.getEmail(), template.getSubject(), emailMessage);
        
    }
    /**
     * Contact administrator
     * @param faculty
     * @param message 
     */
    @Override
    public void contactAdmin(Faculty faculty, String message) {
        SimpleMailMessage template=getToAdminTemplate();
        String emailMessage = String.format(template.getText(), faculty.getFirstName() + " " + faculty.getLastName(),message);
        sendMail(template.getFrom(), faculty.getEmail(), template.getSubject(), emailMessage);
 
    }
    
    //Email sending implementation
    public void sendMail(String fromEmail, String toEmail, String emailSubject, String emailBody) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(emailSubject);
            helper.setText(emailBody);

            /*
             uncomment the following lines for attachment FileSystemResource
             file = new FileSystemResource("attachment.jpg");
             helper.addAttachment(file.getFilename(), file);
             */
            javaMailSender.send(mimeMessage);
            System.out.println("Mail sent successfully.");//debugging
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    
}
