/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.BookingFlightDao;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.BookingPnr;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class BookingFlightImpl extends HibernateDaoSupport implements BookingFlightDao{
    private static final String BOOOKFLIGHTLIST = "from BookingFlight BF where BF.bookingPnr=?";
    
    @Override
    public List<BookingFlight> getBookingFlight(String PNR_ID) {
       BookingPnr pnr = new BookingPnr();
       pnr.setId(PNR_ID);
       List<BookingFlight> list = getHibernateTemplate().find(BOOOKFLIGHTLIST,pnr);
       if(list.isEmpty()){
           return null;
       }
       return list;  
    }
    
}
