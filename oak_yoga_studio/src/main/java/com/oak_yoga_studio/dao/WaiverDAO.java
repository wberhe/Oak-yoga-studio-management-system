/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Waiver;
import java.util.List;

/**
 *
 * @author Fetiya
 */
public interface WaiverDAO {
    
     public void addWaiver(Waiver waiver);
     
     public void updateWaiver(Waiver waiver);
     
     public Waiver getWaiverr(int id);
     
     public List<Waiver> getAllWaivers();
    
    
}
