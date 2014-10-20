/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.EnrollmentDAO;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Enrollment;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.service.impl.SectionServiceImpl;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fetiya
 */
public class EnrollmentDAOImpl implements EnrollmentDAO {

    SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addEnrollment(Enrollment enrollment) {

        sf.getCurrentSession().save(enrollment);

    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateCourse(Enrollment enrollment) {

        sf.getCurrentSession().saveOrUpdate(enrollment);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Enrollment getEnrollment(int id) {

        Enrollment enrollment = (Enrollment) sf.getCurrentSession().get(Enrollment.class, id);

        return enrollment;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Enrollment> getAllEnrollments() {

        List<Enrollment> enrollments;

        Query query = sf.getCurrentSession().createQuery("from Enrollment");
        enrollments = query.list();

        return enrollments;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Enrollment> getEnrollmentsByCustomer(Customer customer) {

        List<Enrollment> enrollments;

        Query query = sf.getCurrentSession().createQuery("select distinct e from Enrollment e where e.customer=:customer");
        query.setParameter("customer", customer);
        enrollments = query.list();

        return enrollments;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Enrollment> getEnrollmentsByCustomerID(int customerId) {

        List<Enrollment> enrollments;

        Query query = sf.getCurrentSession().createQuery("from Enrollment e where e.customer.id=customerId");
        enrollments = query.list();

        return enrollments;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Section> getSections(Course course) {

        List<Section> sections;

        Query query = sf.getCurrentSession().createQuery("from Section s where s.course=course");

        sections = query.list();

        return sections;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public boolean checkSeatAvailablity(int sectionID) {

        Query query = sf.getCurrentSession().createQuery("select availableSeat from Section s where s.id=:sectionID");
        query.setParameter("sectionID", sectionID);

        int availableSeats = 0;

        availableSeats = (Integer) query.uniqueResult();

        if (availableSeats > 0) {

            return true;
        } else {
            return false;
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Course> getAllCourses() {

        List<Course> courses;

        Query query = sf.getCurrentSession().createQuery("from Course c.");
        courses = query.list();

        return courses;

    }

    @Transactional(propagation = Propagation.MANDATORY)

    @Override
    public void saveEnrollment(Enrollment enrollment) {

        sf.getCurrentSession().saveOrUpdate(enrollment);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addWaitingListEnrollment(Enrollment enrollment) {

        enrollment.setStatus(Enrollment.statusType.ACTIVE);
        sf.getCurrentSession().saveOrUpdate(enrollment);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Course> getCoursesTaken(int customerID) {

        List<Course> courses;
        List<Customer> cust;
//e.status ='COMPLETED' AND
        System.out.println("checking enrollments for customer " + customerID);

        Query query = sf.getCurrentSession().createQuery("select distinct co from Customer c  join c.enrollments e "
                + "join e.section s join s.course co where  e.status='COMPLETED' AND c.id=:customerID");

        query.setParameter("customerID", customerID);

        courses = query.list();
        System.out.println("number of courses taken by customer is " + courses.size());
        return courses;

    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void withdraw(Customer customer, Section section) {

        Enrollment enrollment;
        Query query = sf.getCurrentSession().createQuery("select e from Enrollment e  Join e.customer cu"
                + "   join e.section where e.section=:section and e.customer=:customer");

        query.setParameter("section", section);
        query.setParameter("customer", customer);
        enrollment = (Enrollment) query.uniqueResult();

        enrollment.setStatus(Enrollment.statusType.WITHDRAWAL);

        sf.getCurrentSession().saveOrUpdate(enrollment);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Enrollment getTopWaitingList(int sectionId) {

        Enrollment enrollment = null;
        Query query = sf.getCurrentSession().createQuery("select  e from Enrollment e "
                + "join e.section s"
                + " where s.id=:sectionId and e.status='WAITINGLIST'");
        query.setParameter("sectionId", sectionId);
        List<Enrollment> en;
        en = query.list();
        if (!en.isEmpty()) {
            enrollment = en.get(0);
        }
        System.out.println("top enrllement waiting is " + en.size());

        return enrollment;

    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void changeEnrollmentStatus(String status) {

       // ??add enrollment parama
        //should do setEnrollmentStatus(status)
        // and update Enrollment
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void addEnrollment(Enrollment.statusType status, Customer customer, Section section) {

        Enrollment enrollment = new Enrollment();
        enrollment.setCustomer(customer);
        enrollment.setSection(section);
        enrollment.setEnrollmentDate(new Date());
        enrollment.setStatus(status);

        sf.getCurrentSession().save(enrollment);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isFirstTimeEnrollment(Customer customer) {

        List<Enrollment> enrollments;

        Query query = sf.getCurrentSession().createQuery("select distinct e from Enrollment e where e.customer=:customer"
                + " and ( e.status ='COMPLETED' OR e.status ='ACTIVE')  ");

        query.setParameter("customer", customer);
        enrollments = query.list();

        if (enrollments.isEmpty()) {

            return true;
        } else {

            return false;
        }
    }
}
