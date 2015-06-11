/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MAirport;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MAirportDao {
    public List<MAirport> getListAirport(MAirport Airport,int option);
    public int insertAirport(MAirport Airport);
    public int updateAirport(MAirport Airport);
    public int DeleteAirport(MAirport Airport);
    public String getAirportName(String AirportCode);
    public List<MAirport> searchAirport(String Airportname);
}
