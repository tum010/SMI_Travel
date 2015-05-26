/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MCountry;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MCountryDao {
    public List<MCountry> getListCountry(MCountry country,int option);
    public List<MCountry> getListCountry();
    public int insertCountry(MCountry country);
    public int updateCountry(MCountry country);
    public int DeleteCountry(MCountry country);   
}
