/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;
import com.smi.travel.datalayer.entity.Billable;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.Master;
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
    public String getDescriptionInvoiceAirTicket(String refno,int format);
    public String getDescriptionInvoiceOthers(String refno,int format);
    public String getDescriptionInvoiceLand(String refno,int format);
    public String getDescriptionInvoiceHotel(String refno,int format);
    public String getDescriptionInvoiceDayTour(String refno,int format);
    public String getDescriptionInvoiceAirAdditional(String refno,int format);   
    public String getDescriptionInvoiceOthersFromRefId(String refId);
    public String getDescriptionInvoiceDayTourFromRefId(String refId);
    public Billable getBillableBookingForTaxInvoice(String searchRefNo);
    public Invoice getInvoiceForTaxInvoice(Billable bill);
    public String printTicketOrder(String refNo);
    public String DeleteBillableDesc(String billdescId);
    public BillableDesc getBillableDescFromBillDescId(String billDescId);
    public String searchBillableType(Master master);

}
