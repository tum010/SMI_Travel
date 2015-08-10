/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;
import com.smi.travel.datalayer.entity.Billable;
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
}
