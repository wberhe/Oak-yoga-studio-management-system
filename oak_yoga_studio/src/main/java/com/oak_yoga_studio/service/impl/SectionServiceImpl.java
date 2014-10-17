/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.dao.SectionDAO;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.service.ISectionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Senai
 */
public class SectionServiceImpl implements ISectionService {

    private SectionDAO sectionDAO;

    public SectionServiceImpl(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addSection(Section section) {
        try {
            sectionDAO.addSection(section);
        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateSection(Section section) {
        try {
            sectionDAO.updateSection(section);

        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Section getSectionById(int Id) {
        try {
            return sectionDAO.getSection(Id);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Section> getListOfSections() {
        try {
            return sectionDAO.getAllSections();
        } catch (Exception e) {
            return new ArrayList();
        }

    }

}
