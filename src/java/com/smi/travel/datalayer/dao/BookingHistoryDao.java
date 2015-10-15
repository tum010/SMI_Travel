/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.HistoryBooking;
import java.util.List;

/**
 *
 * @author Jittima
 */
public interface BookingHistoryDao {
    public List<HistoryBooking> getHistoryFromRefno(String refno);
    public int insertHistoryBooking(HistoryBooking historyBooking);
    public int UpdateHistoryBooking(HistoryBooking historyBooking);
}
