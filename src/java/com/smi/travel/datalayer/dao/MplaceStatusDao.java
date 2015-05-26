/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MPlacestatus;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MplaceStatusDao {
    public List<MPlacestatus> getListStatus();
}
