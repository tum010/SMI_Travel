/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.report.model.HotelVoucher;
import com.smi.travel.datalayer.report.model.InvoiceSummary;
import com.smi.travel.datalayer.report.model.LandVoucher;
import com.smi.travel.datalayer.view.dao.AgentCommissionReportDao;
import com.smi.travel.datalayer.view.dao.AirlineSummaryDao;
import com.smi.travel.datalayer.view.dao.DaytourOtherDao;
import com.smi.travel.datalayer.view.dao.GuideCommissionReportDao;
import com.smi.travel.datalayer.view.dao.GuideJobDao;
import com.smi.travel.datalayer.view.dao.HotelInboundDao;
import com.smi.travel.datalayer.view.dao.HotelVoucherDao;
import com.smi.travel.datalayer.view.dao.InvoiceSummaryDao;
import com.smi.travel.datalayer.view.dao.LandVoucherDao;
import com.smi.travel.datalayer.view.dao.ReceiptEmailDao;
import com.smi.travel.datalayer.view.dao.ReceiptDao;
import com.smi.travel.datalayer.view.dao.ReceiveListDao;
import com.smi.travel.datalayer.view.dao.StaffSummaryDao;
import com.smi.travel.datalayer.view.dao.TicketOrderDao;
import com.smi.travel.datalayer.view.dao.TicketSaleProfitVolumnDao;
import com.smi.travel.datalayer.view.dao.TicketSummaryDao;
import com.smi.travel.datalayer.view.dao.TransferJobReportDao;
import com.smi.travel.datalayer.view.dao.InvoiceEmailDao;
import com.smi.travel.datalayer.view.dao.InvoiceDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class ReportService {
    
    private HotelVoucherDao hotelVoucherdao;
    private HotelInboundDao hotelInboundDao;
    private LandVoucherDao landVoucherdao;
    private TicketOrderDao ticketOrderdao;
    private TicketSummaryDao ticketsummaryDao;
    private InvoiceSummaryDao invoiceSummaryDao;
    private AirlineSummaryDao airlinesummaryDao;
    private StaffSummaryDao staffsummaryDao;
    private TicketSaleProfitVolumnDao ticketsaleprofitVolumnDao;
    private TransferJobReportDao transferJobReportdao;
    private GuideCommissionReportDao guideComissiondao;
    private GuideJobDao guideJobdao;
    private DaytourOtherDao daytourOtherdao;
    private AgentCommissionReportDao agentCommissiondao;
    private ReceiptEmailDao receiptEmailDao;
    private ReceiptDao receiptDao;
    private ReceiveListDao  receiveListDao;
    private InvoiceEmailDao invoiceEmaildao;
    private InvoiceDao invoicedao;
    
    public List getHotelVoucher(String hotelID,String name) {
        List data  = new ArrayList();
        data.add(hotelVoucherdao.getHotelVoucher(hotelID,name));
        return data;
    }
    
    public List getTicketSaleVolumn(String ticketFrom,String ticketType,String startDate,String endDate){
        return ticketsaleprofitVolumnDao.getTicketSaleVolumn(ticketFrom, ticketType, startDate, endDate);
    }
    public List getTicketProfitVolumn(String ticketFrom,String ticketType,String startDate,String endDate){
        return ticketsaleprofitVolumnDao.getTicketProfitVolumn(ticketFrom, ticketType, startDate, endDate);
    }
    
    public List getStaffSummary(String ticketfrom, String tickettype, String startdate, String enddate, String username) {
        return staffsummaryDao.getStaffSummary(ticketfrom, tickettype, startdate, enddate, username);
    }
    
    public List getAirlineSummary(String ticketfrom,String tickettype,String startdate,String enddate,String username){
        return airlinesummaryDao.getAirlineSummary(ticketfrom, tickettype, startdate, enddate, username);
    }
    
    public List getTicketSummary(String ticketfrom,String tickettype,String startdate,String enddate,String billto,String  passenger,String username){
        return ticketsummaryDao.getTicketSummary(ticketfrom, tickettype, startdate, enddate, billto, passenger,username);
    }
    
    public List getInvoiceSummary(String ticketfrom,String tickettype,String startdate,String enddate,String billto,String  passenger,String username){
        return invoiceSummaryDao.getInvoiceSummary(ticketfrom, tickettype, startdate, enddate, billto, passenger,username);
    }
    
    public List getHotelInboundVoucher(String hotelID){
        return hotelInboundDao.getHotelInboundVoucher(hotelID);
    }
    
    public List getLandVoucher(String refno,String name,String landId){
        List data  = new ArrayList();
        data.add(landVoucherdao.getLandVoucher(refno,name,landId));
        return data;
    }
    
    public List getTicketOrde(String refno,String pnrID){
        List data  = new ArrayList();
        data.add(ticketOrderdao.getTicketOrder(refno,pnrID));
        return data;
    }
    
    public List getTransferJobReport(String docno){
        return transferJobReportdao.getTransferJobReport(docno);
    }
    
    public List getGuideComissionReport(String datefrom,String dateto,String username,String guideid){
        return guideComissiondao.getGuideComissionReport(datefrom, dateto, username,guideid);
    }
    
    public List getDaytourOtherReport(String refno,String status){
        return daytourOtherdao.getDaytourOtherReport(refno,status);
    }
    
    public List getGuildJobReport(String tourdate,String tourID, String username){
        return guideJobdao.getGuildJobReport(tourdate, tourID, username);
    }
   
    public List getAgentCommissionReport(String datefrom,String dateto,String user,String agentid){
        List data  = new ArrayList();
        data.add(agentCommissiondao.getAgentCommissionReport(datefrom, dateto, user,agentid));
        return data;
    }
    
    public List getAgentCommissionReportSummary(String datefrom,String dateto,String user,String agentid){
        return agentCommissiondao.getAgentReportSummary(datefrom, dateto, user,agentid);
    }
    
    public List getAgentCommissionReportInfo(String datefrom,String dateto,String user,String agentid){
        return agentCommissiondao.getAgentReportInfo(datefrom, dateto, user,agentid);
    }
    
    public List getReceiptEmail(){
        List data  = new ArrayList();
        data.add(receiptEmailDao.getReceiptEmail());
        data.add(receiptEmailDao.getReceiptEmail());
        data.add(receiptEmailDao.getReceiptEmail());
        data.add(receiptEmailDao.getReceiptEmail());
        data.add(receiptEmailDao.getReceiptEmail());
        data.add(receiptEmailDao.getReceiptEmail());
        return data;
    }
    
     public List getReceipt(){
        List data  = new ArrayList();
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        data.add(receiptDao.getReceipt());
        return data;
    }
    
    public List getReceiveList(String datefrom,String dateto,String user,String agentid){
        return receiveListDao.getReceiveList(datefrom, agentid, dateto, dateto, dateto, agentid, user);
    }
    
    public List getInvoiceEmail(){
        List data  = new ArrayList();
        data.add(invoiceEmaildao.getInvoiceEmail());  
        return data;
    }
    
    public List getInvoice(){
        List data  = new ArrayList();
        data.add(invoicedao.getInvoice());
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        data.add(invoicedao.getInvoice());  
        return data;
    }

    public HotelVoucherDao getHotelVoucherdao() {
        return hotelVoucherdao;
    }

    public void setHotelVoucherdao(HotelVoucherDao hotelVoucherdao) {
        this.hotelVoucherdao = hotelVoucherdao;
    }

    public LandVoucherDao getLandVoucherdao() {
        return landVoucherdao;
    }

    public void setLandVoucherdao(LandVoucherDao landVoucherdao) {
        this.landVoucherdao = landVoucherdao;
    }

    public TicketOrderDao getTicketOrderdao() {
        return ticketOrderdao;
    }

    public void setTicketOrderdao(TicketOrderDao ticketOrderdao) {
        this.ticketOrderdao = ticketOrderdao;
    }

    public HotelInboundDao getHotelInboundDao() {
        return hotelInboundDao;
    }

    public void setHotelInboundDao(HotelInboundDao hotelInboundDao) {
        this.hotelInboundDao = hotelInboundDao;
    }

    public TicketSummaryDao getTicketsummaryDao() {
        return ticketsummaryDao;
    }

    public void setTicketsummaryDao(TicketSummaryDao ticketsummaryDao) {
        this.ticketsummaryDao = ticketsummaryDao;
    }

    public AirlineSummaryDao getAirlinesummaryDao() {
        return airlinesummaryDao;
    }

    public void setAirlinesummaryDao(AirlineSummaryDao airlinesummaryDao) {
        this.airlinesummaryDao = airlinesummaryDao;
    }

    public StaffSummaryDao getStaffsummaryDao() {
        return staffsummaryDao;
    }

    public void setStaffsummaryDao(StaffSummaryDao staffsummaryDao) {
        this.staffsummaryDao = staffsummaryDao;
    }

    public TicketSaleProfitVolumnDao getTicketsaleprofitVolumnDao() {
        return ticketsaleprofitVolumnDao;
    }

    public void setTicketsaleprofitVolumnDao(TicketSaleProfitVolumnDao ticketsaleprofitVolumnDao) {
        this.ticketsaleprofitVolumnDao = ticketsaleprofitVolumnDao;
    }

    public TransferJobReportDao getTransferJobReportdao() {
        return transferJobReportdao;
    }

    public void setTransferJobReportdao(TransferJobReportDao transferJobReportdao) {
        this.transferJobReportdao = transferJobReportdao;
    }

    public GuideCommissionReportDao getGuideComissiondao() {
        return guideComissiondao;
    }

    public void setGuideComissiondao(GuideCommissionReportDao guideComissiondao) {
        this.guideComissiondao = guideComissiondao;
    }

    public GuideJobDao getGuideJobdao() {
        return guideJobdao;
    }

    public void setGuideJobdao(GuideJobDao guideJobdao) {
        this.guideJobdao = guideJobdao;
    }

    public AgentCommissionReportDao getAgentCommissiondao() {
        return agentCommissiondao;
    }

    public void setAgentCommissiondao(AgentCommissionReportDao agentCommissiondao) {
        this.agentCommissiondao = agentCommissiondao;
    }

    public void setDaytourOtherdao(DaytourOtherDao daytourOtherdao) {
        this.daytourOtherdao = daytourOtherdao;
    }

    public DaytourOtherDao getDaytourOtherdao() {
        return daytourOtherdao;
    }

    public List getInvoiceSummary(String ticketfrom, String tickettype, String startdate, String enddate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ReceiptEmailDao getReceiptEmailDao() {
        return receiptEmailDao;
    }

    public void setReceiptEmailDao(ReceiptEmailDao receiptEmailDao) {
        this.receiptEmailDao = receiptEmailDao;
    }

    public ReceiptDao getReceiptDao() {
        return receiptDao;
    }

    public void setReceiptDao(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }
       
    public void setReceiveListDao(ReceiveListDao receiveListDao) {
        this.receiveListDao = receiveListDao;
    }

    public ReceiveListDao getReceiveListDao() {
        return receiveListDao;
    }
    
    public void setInvoiceSummaryDao(InvoiceSummaryDao invoiceSummaryDao) {
        this.invoiceSummaryDao = invoiceSummaryDao;
    }
    
    public InvoiceSummaryDao getInvoiceSummaryDao() {
        return invoiceSummaryDao;
    }

    public void setInvoiceEmaildao(InvoiceEmailDao invoiceEmaildao) {
        this.invoiceEmaildao = invoiceEmaildao;
    }

    public InvoiceEmailDao getInvoiceEmaildao() {
        return invoiceEmaildao;
    }

    public void setInvoicedao(InvoiceDao invoicedao) {
        this.invoicedao = invoicedao;
    }

    public InvoiceDao getInvoicedao() {
        return invoicedao;
    }
   
}
