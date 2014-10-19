/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Waiver;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import org.hibernate.Session; 




/**
 *
 * @author weldu
 */

public class CustomerDAOImpl implements CustomerDAO {
    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addCustomer(Customer customer) {
        sf.getCurrentSession().save(customer);
    }
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateCustomer(Customer customer) {
        sf.getCurrentSession().saveOrUpdate(customer);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Customer getCustomer(int id) {
        
        Customer customer= (Customer) sf.getCurrentSession().get(Customer.class,id);
        return customer;    
            
     }
    /**
     * 
     * @returns only Customer users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers;        
        Query query= sf.getCurrentSession().createQuery("from Customer");
        customers= query.list();
        
       return customers;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Waiver> getApprovedWaiversByCustomerID ( int customerID)
    {
       
        List<Waiver> waivers ;
             
        Query query= sf.getCurrentSession().createQuery("from Waiver where customer.id=:customerID");
        
        query.setParameter("customerID", customerID);
        waivers= query.list();
        
       return waivers;
    
    }

    @Override
    public List<Course> getAllCoursesToWaive(Customer customer) {
        
         List<Course> courses;
         String sql =  ""
                    + "  select distinct co.* from Customer c  join  enrollment e on c.id=e.customer_id" 
                    + "  join section s on s.id=e.section_id "
                    + "  join course co on s.course_id= co.ID "
                    + "  where  e.status!='COMPLETED' AND c.id= " + customer.getId()
                    + "  AND c.id not in (select course_id from  waiver"
                    + "  where customer_id = " + customer.getId() +  ")" ;
   
      SQLQuery query = sf.getCurrentSession().createSQLQuery(sql);
      query.addEntity(Course.class);
        courses = query.list();

        System.out.println("number of courses taken by customer is "+ courses.size() );
       return courses;
       
    
    
    }
    
}
