/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.domain.Address;
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

        Customer customer = (Customer) sf.getCurrentSession().get(Customer.class, id);
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
        Query query = sf.getCurrentSession().createQuery("from Customer");
        customers = query.list();

        return customers;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Waiver> getApprovedWaiversByCustomerID(int customerID) {

        List<Waiver> waivers;

        Query query = sf.getCurrentSession().createQuery("from Waiver where customer.id=:customerID");

        query.setParameter("customerID", customerID);
        waivers = query.list();

        return waivers;

    }

     @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Course> getAllCoursesToWaive(Customer customer) {

        List<Course> courses;
        String sql = "SELECT distinct co.* FROM COURSE co WHERE  co.id \n"
                + "               NOT IN\n"
                + "                (SELECT s.course_id FROM enrollment e  join customer c on c.id=e.customer_id\n"
                + "                  \n"
                + "                JOIN section s on s.id=e.section_id \n"
                + "                WHERE  c.id= " + customer.getId() + " AND e.status='COMPLETED'\n"
                + "                )\n"
                + "                AND co.id NOT IN ( SELECT waiverCourse_id from  waiver \n"
                + "                                  WHERE customer_id =" + customer.getId() + ")";

        SQLQuery query = sf.getCurrentSession().createSQLQuery(sql);
        query.addEntity(Course.class);
        courses = query.list();

        System.out.println("number of courses taken by customer is " + courses.size());
        return courses;

    }

    public List<Address> getCustomerAddresses(int customerId) {
        List<Address> addresses;

        SQLQuery query = sf.getCurrentSession().createSQLQuery("select * from Address where user_id=" + customerId);
        query.addEntity(Course.class);
        addresses = query.list();
        System.out.println("number of addresses of by customer is " + addresses.size());
        return addresses;
    }

       @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Waiver> getAllWaiversByCustomer(Customer customer) {

        List<Waiver> waivers;

        Query query = sf.getCurrentSession().createQuery(" from Waiver where customer=:customer");

        query.setParameter("customer", customer);
        waivers = query.list();
        
         System.out.println("Waivers length is " + waivers.size());

        return waivers;
    }

}
