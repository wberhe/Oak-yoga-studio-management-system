/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Section;
import java.util.List;

/**
 *
 * @author Senai
 */
public interface ISectionService {
    public void addSection(Section section);

    public void updateSection(Section section);

    public Section getSectionById(int Id);
        
    public List<Section> getListOfSections();
}
