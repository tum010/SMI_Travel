/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.BookingPassengerDao;
import com.smi.travel.datalayer.entity.BookingPassenger;
import com.smi.travel.datalayer.entity.BookingPnr;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class BookingPassengerImpl extends HibernateDaoSupport implements BookingPassengerDao{
    private static final String BOOOKTICKETLIST = "from BookingPassenger BP where BP.bookingPnr=?";
    @Override
    public List<BookingPassenger> getBookingPassenger(String PNR_ID) {
       BookingPnr pnr = new BookingPnr();
       pnr.setId(PNR_ID);
       List<BookingPassenger> list = getHibernateTemplate().find(BOOOKTICKETLIST,pnr);
       if(list.isEmpty()){
           return null;
       }
       return list; 
    }
    
}
