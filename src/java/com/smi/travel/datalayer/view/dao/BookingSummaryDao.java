/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import com.smi.travel.datalayer.view.entity.BookingHeaderSummaryView;
import java.util.List;
/**
 *
 * @author Surachai
 */
public interface BookingSummaryDao {
    public List<BookSummary>  getListBookSummary(String refno);
    public BookingHeaderSummaryView getBookingSummaryReport(String refno);
}
