/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao.impl;

import com.oak_yoga_studio.dao.SectionDAO;
import com.oak_yoga_studio.domain.Course;
import com.oak_yoga_studio.domain.Section;
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
public class SectionDAOImpl implements SectionDAO {

    
    private SessionFactory sf;

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
    
    
    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addSection(Section section) {
             
        sf.getCurrentSession().save(section);
    }

    
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void updateSection(Section section) {
       
        sf.getCurrentSession().saveOrUpdate(section);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Section getSection(int id) {
        
        Section section=(Section) sf.getCurrentSession().get(Section.class, id);
        
        return section;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Section> getAllSections() {
        
        List<Section> sections= new ArrayList<Section>();
    
        Query query= sf.getCurrentSession().createQuery("from Section");
    
        sections=query.list();
    
        return sections;
        
    }

    @Override
    public List<Section> getCourseSections(Course course) {
        List<Section> sections;
        Query q=sf.getCurrentSession().createQuery(" select s from Section s join s.course c where c=:course");
        
        q.setParameter("course", course);
        sections=q.list();
    
        return sections;
    }
    
}
