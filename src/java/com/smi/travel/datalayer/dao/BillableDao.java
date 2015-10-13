/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;
import com.smi.travel.datalayer.entity.Billable;
import com.smi.travel.datalayer.entity.Invoice;
/**
 *
 * @author Surachai
 */
public interface BillableDao {
    public Billable getBillableBooking(String refno);
    public int insertBillableBooking(Billable bill);
    public int updateBillableBooking(Billable bill);
    public int updateBillStatusBooking(String refno,Billable bill);
    public String getMBillTypeName(String typeId);
    public String getDescriptionInvoiceAirTicket(String refno);
    public String getDescriptionInvoiceOthers(String refno);
    public String getDescriptionInvoiceLand(String refno);
    public String getDescriptionInvoiceHotel(String refno);
    public String getDescriptionInvoiceDayTour(String refno);
    public String getDescriptionInvoiceAirAdditional(String refno);   
    public String getDescriptionInvoiceOthersFromRefId(String refId);
    public String getDescriptionInvoiceDayTourFromRefId(String refId);
    public Billable getBillableBookingForTaxInvoice(String searchRefNo);
    public Invoice getInvoiceForTaxInvoice(String billDescId);
    public String printTicketOrder(String refNo);

}
