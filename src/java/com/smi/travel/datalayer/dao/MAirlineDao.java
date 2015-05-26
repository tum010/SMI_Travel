/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;
import com.smi.travel.datalayer.entity.MAirline;
import java.util.List;
/**
 *
 * @author Surachai
 */
public interface MAirlineDao {
    public List<MAirline> getListAirLine(MAirline airline,int option);
    public int insertAirLine(MAirline airline);
    public int updateAirLine(MAirline airline);
    public int DeleteAirLine(MAirline airline);
    
}
