/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.AddressDAO;
import com.oak_yoga_studio.domain.Address;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weldino
 */
public class AddressDAOImpl implements AddressDAO {

    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addAddress(Address address) {
        sf.getCurrentSession().save(address);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateAddress(Address address) {
        sf.getCurrentSession().saveOrUpdate(address);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Address getAddress(int id) {
        Address address = (Address) sf.getCurrentSession().get(Address.class, id);
        return address;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Address> getAllAddresses() {
        Query q = sf.getCurrentSession().createQuery("from Address");
        List<Address> ads = q.list();

        return ads;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void removeAddress(Address address) {
        sf.getCurrentSession().delete(address);
    }

}
