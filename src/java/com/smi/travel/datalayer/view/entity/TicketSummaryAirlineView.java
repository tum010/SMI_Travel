/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jittima
 */
public class TicketSummaryAirlineView {
    //header
    private String headerdatefrom ;
    private String headerdateto;
    private String headerprintby;
    private String headertyperouting;
    private String headerair;
    private String headeragentname;
    private String headertermpay;
    private String headerprinton;
    private String headerroutingdetail;
    private String headerpassenger;
    private String headersalestaff;
    private String headerdepartment;
    
    //routing
    private String routingR;
    private String paxR;
    private String netsalesR;
    private String taxR;
    private String insR;
    private String commsR;
    private String amountwendyR;
    private String amountinboundR;
    private String diffR;
    
    //pax
    private String paxP;
    private String paymenttypeP;
    private String typeroutingP;
    private String netsalesP;
    private String taxP;
    private String insP;
    private String commsP;
    private String amountwendyP;
    private String amountinboundP;
    
    

    //detail
    private String invnoD;
    private String invdateD;
    private String departmentD;
    private String staffD;
    private String termpayD;
    private String passengerD;
    private String typepaymentD;
    private String typeroutingD;
    private String routingD;
    private String paxD;
    private String airD;
    private String ticketnoD;
    private String refnoD;
    private String issuedateD;
    private String netsalesD;
    private String taxD;
    private String insD;
    private String commsD;
    private String amountwendyD;
    private String amountinboundD;
    private String amtnoinvoiceD;
    private String amtbusinesstripD;
    private String amtannualleaveD;
    private String amtrefundD;
    private String remarksD;
    private String diffD;

    private String page;
    
    public String getHeaderdatefrom() {
        return headerdatefrom;
    }

    public void setHeaderdatefrom(String headerdatefrom) {
        this.headerdatefrom = headerdatefrom;
    }

    public String getHeaderdateto() {
        return headerdateto;
    }

    public void setHeaderdateto(String headerdateto) {
        this.headerdateto = headerdateto;
    }

    public String getHeaderprintby() {
        return headerprintby;
    }

    public void setHeaderprintby(String headerprintby) {
        this.headerprintby = headerprintby;
    }

    public String getHeadertyperouting() {
        return headertyperouting;
    }

    public void setHeadertyperouting(String headertyperouting) {
        this.headertyperouting = headertyperouting;
    }

    public String getHeaderair() {
        return headerair;
    }

    public void setHeaderair(String headerair) {
        this.headerair = headerair;
    }

    public String getHeaderagentname() {
        return headeragentname;
    }

    public void setHeaderagentname(String headeragentname) {
        this.headeragentname = headeragentname;
    }

    public String getHeadertermpay() {
        return headertermpay;
    }

    public void setHeadertermpay(String headertermpay) {
        this.headertermpay = headertermpay;
    }

    public String getHeaderprinton() {
        return headerprinton;
    }

    public void setHeaderprinton(String headerprinton) {
        this.headerprinton = headerprinton;
    }

    public String getHeaderroutingdetail() {
        return headerroutingdetail;
    }

    public void setHeaderroutingdetail(String headerroutingdetail) {
        this.headerroutingdetail = headerroutingdetail;
    }

    public String getHeaderpassenger() {
        return headerpassenger;
    }

    public void setHeaderpassenger(String headerpassenger) {
        this.headerpassenger = headerpassenger;
    }

    public String getHeadersalestaff() {
        return headersalestaff;
    }

    public void setHeadersalestaff(String headersalestaff) {
        this.headersalestaff = headersalestaff;
    }

    public String getHeaderdepartment() {
        return headerdepartment;
    }

    public void setHeaderdepartment(String headerdepartment) {
        this.headerdepartment = headerdepartment;
    }

    public String getRoutingR() {
        return routingR;
    }

    public void setRoutingR(String routingR) {
        this.routingR = routingR;
    }

    public String getPaxR() {
        return paxR;
    }

    public void setPaxR(String paxR) {
        this.paxR = paxR;
    }

    public String getNetsalesR() {
        return netsalesR;
    }

    public void setNetsalesR(String netsalesR) {
        this.netsalesR = netsalesR;
    }

    public String getTaxR() {
        return taxR;
    }

    public void setTaxR(String taxR) {
        this.taxR = taxR;
    }

    public String getInsR() {
        return insR;
    }

    public void setInsR(String insR) {
        this.insR = insR;
    }

    public String getCommsR() {
        return commsR;
    }

    public void setCommsR(String commsR) {
        this.commsR = commsR;
    }

    public String getAmountwendyR() {
        return amountwendyR;
    }

    public void setAmountwendyR(String amountwendyR) {
        this.amountwendyR = amountwendyR;
    }

    public String getAmountinboundR() {
        return amountinboundR;
    }

    public void setAmountinboundR(String amountinboundR) {
        this.amountinboundR = amountinboundR;
    }

    public String getDiffR() {
        return diffR;
    }

    public void setDiffR(String diffR) {
        this.diffR = diffR;
    }

    public String getPaxP() {
        return paxP;
    }

    public void setPaxP(String paxP) {
        this.paxP = paxP;
    }

    public String getPaymenttypeP() {
        return paymenttypeP;
    }

    public void setPaymenttypeP(String paymenttypeP) {
        this.paymenttypeP = paymenttypeP;
    }

    public String getTyperoutingP() {
        return typeroutingP;
    }

    public void setTyperoutingP(String typeroutingP) {
        this.typeroutingP = typeroutingP;
    }

    public String getNetsalesP() {
        return netsalesP;
    }

    public void setNetsalesP(String netsalesP) {
        this.netsalesP = netsalesP;
    }

    public String getTaxP() {
        return taxP;
    }

    public void setTaxP(String taxP) {
        this.taxP = taxP;
    }

    public String getInsP() {
        return insP;
    }

    public void setInsP(String insP) {
        this.insP = insP;
    }

    public String getCommsP() {
        return commsP;
    }

    public void setCommsP(String commsP) {
        this.commsP = commsP;
    }

    public String getAmountwendyP() {
        return amountwendyP;
    }

    public void setAmountwendyP(String amountwendyP) {
        this.amountwendyP = amountwendyP;
    }

    public String getAmountinboundP() {
        return amountinboundP;
    }

    public void setAmountinboundP(String amountinboundP) {
        this.amountinboundP = amountinboundP;
    }

    public String getInvnoD() {
        return invnoD;
    }

    public void setInvnoD(String invnoD) {
        this.invnoD = invnoD;
    }

    public String getInvdateD() {
        return invdateD;
    }

    public void setInvdateD(String invdateD) {
        this.invdateD = invdateD;
    }

    public String getDepartmentD() {
        return departmentD;
    }

    public void setDepartmentD(String departmentD) {
        this.departmentD = departmentD;
    }

    public String getStaffD() {
        return staffD;
    }

    public void setStaffD(String staffD) {
        this.staffD = staffD;
    }

    public String getTermpayD() {
        return termpayD;
    }

    public void setTermpayD(String termpayD) {
        this.termpayD = termpayD;
    }

    public String getPassengerD() {
        return passengerD;
    }

    public void setPassengerD(String passengerD) {
        this.passengerD = passengerD;
    }

    public String getTypepaymentD() {
        return typepaymentD;
    }

    public void setTypepaymentD(String typepaymentD) {
        this.typepaymentD = typepaymentD;
    }

    public String getTyperoutingD() {
        return typeroutingD;
    }

    public void setTyperoutingD(String typeroutingD) {
        this.typeroutingD = typeroutingD;
    }

    public String getRoutingD() {
        return routingD;
    }

    public void setRoutingD(String routingD) {
        this.routingD = routingD;
    }

    public String getPaxD() {
        return paxD;
    }

    public void setPaxD(String paxD) {
        this.paxD = paxD;
    }

    public String getAirD() {
        return airD;
    }

    public void setAirD(String airD) {
        this.airD = airD;
    }

    public String getTicketnoD() {
        return ticketnoD;
    }

    public void setTicketnoD(String ticketnoD) {
        this.ticketnoD = ticketnoD;
    }

    public String getIssuedateD() {
        return issuedateD;
    }

    public void setIssuedateD(String issuedateD) {
        this.issuedateD = issuedateD;
    }

    public String getNetsalesD() {
        return netsalesD;
    }

    public void setNetsalesD(String netsalesD) {
        this.netsalesD = netsalesD;
    }

    public String getTaxD() {
        return taxD;
    }

    public void setTaxD(String taxD) {
        this.taxD = taxD;
    }

    public String getInsD() {
        return insD;
    }

    public void setInsD(String insD) {
        this.insD = insD;
    }

    public String getCommsD() {
        return commsD;
    }

    public void setCommsD(String commsD) {
        this.commsD = commsD;
    }

    public String getAmountwendyD() {
        return amountwendyD;
    }

    public void setAmountwendyD(String amountwendyD) {
        this.amountwendyD = amountwendyD;
    }

    public String getAmountinboundD() {
        return amountinboundD;
    }

    public void setAmountinboundD(String amountinboundD) {
        this.amountinboundD = amountinboundD;
    }

    public String getRemarksD() {
        return remarksD;
    }

    public void setRemarksD(String remarksD) {
        this.remarksD = remarksD;
    }

    public String getDiffD() {
        return diffD;
    }

    public void setDiffD(String diffD) {
        this.diffD = diffD;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRefnoD() {
        return refnoD;
    }

    public void setRefnoD(String refnoD) {
        this.refnoD = refnoD;
    }

    public String getAmtnoinvoiceD() {
        return amtnoinvoiceD;
    }

    public void setAmtnoinvoiceD(String amtnoinvoiceD) {
        this.amtnoinvoiceD = amtnoinvoiceD;
    }

    public String getAmtbusinesstripD() {
        return amtbusinesstripD;
    }

    public void setAmtbusinesstripD(String amtbusinesstripD) {
        this.amtbusinesstripD = amtbusinesstripD;
    }

    public String getAmtannualleaveD() {
        return amtannualleaveD;
    }

    public void setAmtannualleaveD(String amtannualleaveD) {
        this.amtannualleaveD = amtannualleaveD;
    }

    public String getAmtrefundD() {
        return amtrefundD;
    }

    public void setAmtrefundD(String amtrefundD) {
        this.amtrefundD = amtrefundD;
    }
}
