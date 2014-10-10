/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Section;
import java.util.List;

/**
 *
 * @author Fetiya
 */
public interface SectionDAO {
    
      public void addUseSection(SectionDAO section);
     
     public void updateSection(SectionDAO section);
     
     public Section getSection(int id);
     
     public List<Section> getAllSections();
    
}
