/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import com.smi.travel.datalayer.view.entity.BookingHeaderSummaryView;
import com.smi.travel.datalayer.view.entity.ConfirmSlipHeaderReport;
import com.smi.travel.datalayer.view.entity.OutboundStaffSummaryReport;
import java.util.List;
/**
 *
 * @author Surachai
 */
public interface BookingSummaryDao {
    public List<BookSummary>  getListBookSummary(String refno);
    public BookingHeaderSummaryView getBookingSummaryReport(String refno);
    public ConfirmSlipHeaderReport getConfirmSlipHeaderReport(String refno,String user);
    
    public OutboundStaffSummaryReport getOutboundStaffSummaryReport(String from,String to,String saleby,String currency,String detail,String user);
    public List getBookingInvoiceReport(String owner,String invto,String bookdatefrom,String bookdateto,String invdatefrom,String invdateto,String printby);
    public List getBookingNonInvoiceReport(String owner,String invsup,String bookdatefrom,String bookdateto,String paydatefrom,String paydateto,String printby);
}
