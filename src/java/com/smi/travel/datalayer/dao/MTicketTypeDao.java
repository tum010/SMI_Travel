/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MTicketType;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MTicketTypeDao {
    public List<MTicketType> getListTicketType(MTicketType tickettype,int option);
    public int insertTicketType(MTicketType tickettype);
    public int updateTicketType(MTicketType tickettype);
    public int DeleteTicketType(MTicketType tickettype);    
}
