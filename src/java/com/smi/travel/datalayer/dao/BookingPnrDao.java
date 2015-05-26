/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.BookingPnr;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface BookingPnrDao {
    public BookingPnr getBookingPnr(String PNR);
    public int insertBookingPnr(BookingPnr bPnr);
    public int updateBookingPnr(BookingPnr bPnr);
    public List<BookingPnr> getListBookingPnr();
}
