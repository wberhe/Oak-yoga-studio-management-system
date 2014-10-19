/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.WaiverDAO;
import com.oak_yoga_studio.domain.Waiver;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fetiya
 */
public class WaiverDAOImpl implements WaiverDAO{

    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addWaiver(Waiver waiver) {
   
        sf.getCurrentSession().save(waiver);
    }
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateWaiver(Waiver waiver) {
        sf.getCurrentSession().saveOrUpdate(waiver);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Waiver getWaiverr(int id) {
        System.out.println("id "+ id);
        Waiver waiver=(Waiver)sf.getCurrentSession().get(Waiver.class,id);
        
        return waiver;
      
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Waiver> getAllWaivers() {
       
         List<Waiver> waivers=new ArrayList<Waiver>();
        
        Query query= sf.getCurrentSession().createQuery("from Waiver");
        waivers= query.list();
        
       return waivers;
    }
    
    
    
}
