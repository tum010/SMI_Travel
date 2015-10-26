/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.PackageTourHotelDao;
import com.smi.travel.datalayer.dao.PaymentAirTicketDao;
import com.smi.travel.datalayer.dao.PaymentWendytourDao;
import com.smi.travel.datalayer.dao.SummaryTicketAdjustCostAndIncomeDao;
import com.smi.travel.datalayer.view.dao.APNirvanaDao;
import com.smi.travel.datalayer.view.dao.ARNirvanaDao;
import com.smi.travel.datalayer.view.dao.AgentCommissionReportDao;
import com.smi.travel.datalayer.view.dao.AirlineSummaryDao;
import com.smi.travel.datalayer.view.dao.BillAirAgentDao;
import com.smi.travel.datalayer.view.dao.CollectionNirvanaDao;
import com.smi.travel.datalayer.view.dao.CreditNoteReportDao;
import com.smi.travel.datalayer.view.dao.CreditNoteSummaryReportDao;
import com.smi.travel.datalayer.view.dao.DaytourOtherDao;
import com.smi.travel.datalayer.view.dao.GuideCommissionReportDao;
import com.smi.travel.datalayer.view.dao.GuideJobDao;
import com.smi.travel.datalayer.view.dao.HotelInboundDao;
import com.smi.travel.datalayer.view.dao.HotelVoucherDao;
import com.smi.travel.datalayer.view.dao.InvoiceEmailDao;
import com.smi.travel.datalayer.view.dao.InvoiceReportDao;
import com.smi.travel.datalayer.view.dao.InvoiceSummaryDao;
import com.smi.travel.datalayer.view.dao.LandVoucherDao;
import com.smi.travel.datalayer.view.dao.ReceiptDao;
import com.smi.travel.datalayer.view.dao.ReceiveListDao;
import com.smi.travel.datalayer.view.dao.RefundAirReportDao;
import com.smi.travel.datalayer.view.dao.StaffSummaryDao;
import com.smi.travel.datalayer.view.dao.TaxInvoiceEmailReportDao;
import com.smi.travel.datalayer.view.dao.TaxInvoiceReportDao;
import com.smi.travel.datalayer.view.dao.TaxInvoiceSummaryReportDao;
import com.smi.travel.datalayer.view.dao.TicketFareReportDao;
import com.smi.travel.datalayer.view.dao.TicketOrderDao;
import com.smi.travel.datalayer.view.dao.TicketSaleProfitVolumnDao;
import com.smi.travel.datalayer.view.dao.TicketSummaryCommissionDao;
import com.smi.travel.datalayer.view.dao.TicketSummaryDao;
import com.smi.travel.datalayer.view.dao.TransferJobReportDao;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.datalayer.view.entity.ListSummaryTicketAdjustCostAndIncome;
import com.smi.travel.report.GenerateReport;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;

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
    private ReceiptDao receiptDao;
    private ReceiveListDao  receiveListDao;
    private InvoiceEmailDao invoiceEmaildao;
    private InvoiceReportDao invoicedao;
    private GenerateReport genreport;
    private TaxInvoiceReportDao taxInvoiceDao;
    private TaxInvoiceEmailReportDao taxInvoiceEmailDao;
    private CreditNoteReportDao creditNoteReportdao;
    private InvoiceReportDao invoiceReportDao;
    private RefundAirReportDao refundAirReportDao;
    private TicketFareReportDao ticketFareReportDao;
    private TaxInvoiceSummaryReportDao taxInvoiceSummaryReportDao;
    private CreditNoteSummaryReportDao creditNoteSummaryReportDao;
    private BillAirAgentDao  billAirAgentDao;
    private ARNirvanaDao arNirvanaDao;
    private CollectionNirvanaDao collectionNirvanaDao;
    private APNirvanaDao apNirvanaDao;
    private PaymentAirTicketDao paymentAirTicketDao;
    private SummaryTicketAdjustCostAndIncomeDao summaryTicketAdjustCostAndIncomeDao;
    private PaymentWendytourDao paymentWendytourDao;
    private TicketSummaryCommissionDao ticketSummaryCommissionDao;
    private PackageTourHotelDao packageTourHotelDao;
    
    public List getInvoiceMonthly(String BillFrom,String BillTo,String ClientName,String Payment,String Accno,String vattype,String from,String to,String department){
        return invoiceReportDao.getInvoiceMonthly(BillFrom, BillTo, ClientName, Payment, Accno, vattype, from, to, department);
    }
    
    public List getHotelVoucher(String hotelID,String name) {
        List data  = new ArrayList();
        data.add(hotelVoucherdao.getHotelVoucher(hotelID,name));
        return data;
    }
    
    public String  printreport(String filename,String outputname,JRDataSource datasource){
        System.out.println("run report : "+ filename);
        String result = "";
        result = genreport.printReport(filename, outputname, datasource);
        
        return result;
    }
    
    public String[]  getPartReport(){
        String[] path = new String[3];
        path[0] = genreport.getExportpath();
        path[1] = genreport.getReportpath();
        return path;
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
    
//    public List getInvoiceSummary(String ticketfrom,String tickettype,String startdate,String enddate,String billto,String  passenger,String username){
//        return invoiceSummaryDao.getInvoiceSummary(ticketfrom, tickettype, startdate, enddate, billto, passenger,username);
//    }
    
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
    
    public List getGuideCommissionInfoReport(String datefrom,String dateto,String username,String guideid){
        List data  = new ArrayList();
        data.add(guideComissiondao.getGuideCommissionInfoReport(datefrom, dateto, username,guideid));
        return data;
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
    
    public List getReceiptEmail(String receiptId,int option){
        return receiptDao.getReceipt(receiptId,option);
    }
    
     public List getReceipt(String receiptId,int option){
        return receiptDao.getReceipt(receiptId,option);
    }
     
    public List getReceiptSummary(String dateFrom,String dateTo,String departmentRec,String recType,String status,String username){
        return receiptDao.getReceiptSummary(dateFrom,dateTo,departmentRec,recType,status,username);
    }
    
    public List getReceiveList(String datefrom,String dateto,String user,String agentid){
        return receiveListDao.getReceiveList(datefrom, agentid, dateto, dateto, dateto, agentid, user);
    }
    
    public List getInvoiceEmail(){
        List data  = new ArrayList();
        data.add(invoiceEmaildao.getInvoiceEmail());  
        return data;
    }
    
    public List getTaxInvoice(String taxInvId,int option){        
        return taxInvoiceDao.getTaxInvoice(taxInvId,option);
    }
    
    public List getTaxInvoiceEmail(String taxInvId,int option){
        return taxInvoiceDao.getTaxInvoice(taxInvId,option);
    }
    
    public List getInvoice(String InvoiceId,String BankId,String showStaff,String showLeader,String sign){
        return invoicedao.getInvoice(InvoiceId, BankId,showStaff,showLeader,sign);
    }
    
    public List getCreditNoteReport(String Cnid){
        return creditNoteReportdao.getCreditNoteReport(Cnid);
    }
    
    public List getRefundAirReport(String refundId){        
        return refundAirReportDao.getRefundAir(refundId);
    }
    
    public List getTicketFareReport(String ticketType,String ticketBuy,String airline,String airlineCode,String dateFrom,String dateTo,String department,String staff,String termPay,String printby,String invdateFrom,String invdateTo ){      
        return ticketFareReportDao.getTicketFareReport(ticketType,ticketBuy,airline,airlineCode,dateFrom,dateTo,department,staff,termPay,printby,invdateFrom,invdateTo);
    }
    
    public List getTicketFareSumAgentStaff(String ticketType,String ticketBuy,String airline,String airlineCode,String department,String staff,String termPay,String printby,String issuedateFrom,String issuedateTo,String invdateFrom,String invdateTo,String groupBy){
        return ticketFareReportDao.getTicketFareSumAgentStaff(ticketType, ticketBuy, airline, airlineCode, department, staff, termPay, printby, issuedateFrom, issuedateTo, invdateFrom, invdateTo,groupBy);
    }

    public List getTaxInvoiceSummaryReport(String from, String to, String department, String status, String systemuser){
        return taxInvoiceSummaryReportDao.getTaxInvoiceSummaryReport(from, to, department, status, systemuser);
    }
    
    public List getCreditNoteSummaryReport(String from, String to, String department, String status, String systemuser){
        return creditNoteSummaryReportDao.getCreditNoteSummaryReport(from, to, department, status, systemuser);
    }
    
    public List<ARNirvana> SearchArNirvanaFromFilter(String invtype,String department,String billtype,String from,String to,String status, String accno){
        return arNirvanaDao.SearchArNirvanaFromFilter(invtype, department, billtype, from, to, status, accno);
    }
    
    public List<CollectionNirvana> getCollectionNirvanaFromFilter(String department,String type,String status,String from,String to,String invno,String printby){
        return collectionNirvanaDao.getCollectionNirvanaFromFilter(department, type, status, from, to, invno, printby);
    }
    
    public List getApNirvanaReport(String paymentType,String producttype,String status,String from,String to,String printby) {
        return getApNirvanaDao().getApNirvanaReport(paymentType, producttype, status, from, to, printby);
    }
    
    public List getTicketFareSumAirline(String typeRouting,String routingDetail,String dateFrom,String dateTo,String invdateForm,String invdateTo,String airlineCode,String passenger,String agentId,String department,String saleBy,String termPay,String printby){
        return ticketFareReportDao.getTicketFareSumAirline(typeRouting, routingDetail, dateFrom, dateTo, invdateForm, invdateTo, airlineCode, passenger, agentId, department, saleBy, termPay, printby);
    }
    
    public List getSumAirlinePax(String typeRouting,String routingDetail,String dateFrom,String dateTo,String invdateForm,String invdateTo,String airlineCode,String passenger,String agentId,String department,String saleBy,String termPay,String printby){
        return airlinesummaryDao.getSumAirlinePax(typeRouting, routingDetail, dateFrom, dateTo, invdateForm, invdateTo, airlineCode, passenger, agentId, department, saleBy, termPay, printby);
    }
    
    public List getBillAirAgentReport(){
        return billAirAgentDao.getBillAirAgentReport();
    }
    
    public List getBillAirAgentReportSummary(String agentCode,String invoiceFromDate,String InvoiceToDate,String issueFrom,String issueTo,String refundFrom,String refundTo,String department,String salebyUser,String termPay,String printby,String paymentType){
        return billAirAgentDao.getBillAirAgentReportSummary(agentCode, invoiceFromDate, InvoiceToDate, issueFrom, issueTo, refundFrom, refundTo, department, salebyUser, termPay,printby,paymentType);
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

    public List getInvoiceSummary(String from, String to, String department, String type,String agent,String statusInvoice) {
        return invoiceSummaryDao.getInvoiceSummary(from, to, department, type,agent,statusInvoice);
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

    public InvoiceSummaryDao getInvoiceSummaryDao() {
        return invoiceSummaryDao;
    }

    public void setInvoiceSummaryDao(InvoiceSummaryDao invoiceSummaryDao) {
        this.invoiceSummaryDao = invoiceSummaryDao;
    }
   

    public void setInvoiceEmaildao(InvoiceEmailDao invoiceEmaildao) {
        this.invoiceEmaildao = invoiceEmaildao;
    }

    public InvoiceEmailDao getInvoiceEmaildao() {
        return invoiceEmaildao;
    }

    public void setInvoicedao(InvoiceReportDao invoicedao) {
        this.invoicedao = invoicedao;
    }

    public InvoiceReportDao getInvoicedao() {
        return invoicedao;
    }

    public GenerateReport getGenreport() {
        return genreport;
    }

    public void setGenreport(GenerateReport genreport) {
        this.genreport = genreport;
    }

    public TaxInvoiceReportDao getTaxInvoiceDao() {
        return taxInvoiceDao;
    }

    public void setTaxInvoiceDao(TaxInvoiceReportDao taxInvoiceDao) {
        this.taxInvoiceDao = taxInvoiceDao;
    }

    public TaxInvoiceEmailReportDao getTaxInvoiceEmailDao() {
        return taxInvoiceEmailDao;
    }

    public void setTaxInvoiceEmailDao(TaxInvoiceEmailReportDao taxInvoiceEmailDao) {
        this.taxInvoiceEmailDao = taxInvoiceEmailDao;
    }

    public CreditNoteReportDao getCreditNoteReportdao() {
        return creditNoteReportdao;
    }

    public void setCreditNoteReportdao(CreditNoteReportDao creditNoteReportdao) {
        this.creditNoteReportdao = creditNoteReportdao;
    }

    public void setInvoiceReportDao(InvoiceReportDao invoiceReportDao) {
        this.invoiceReportDao = invoiceReportDao;
    }

    public InvoiceReportDao getInvoiceReportDao() {
        return invoiceReportDao;
    }

    public RefundAirReportDao getRefundAirReportDao() {
        return refundAirReportDao;
    }

    public void setRefundAirReportDao(RefundAirReportDao refundAirReportDao) {
        this.refundAirReportDao = refundAirReportDao;
    }

    public TicketFareReportDao getTicketFareReportDao() {
        return ticketFareReportDao;
    }

    public void setTicketFareReportDao(TicketFareReportDao ticketFareReportDao) {
        this.ticketFareReportDao = ticketFareReportDao;
    }

    public TaxInvoiceSummaryReportDao getTaxInvoiceSummaryReportDao() {
        return taxInvoiceSummaryReportDao;
    }

    public void setTaxInvoiceSummaryReportDao(TaxInvoiceSummaryReportDao taxInvoiceSummaryReportDao) {
        this.taxInvoiceSummaryReportDao = taxInvoiceSummaryReportDao;
    }

    public CreditNoteSummaryReportDao getCreditNoteSummaryReportDao() {
        return creditNoteSummaryReportDao;
    }

    public void setCreditNoteSummaryReportDao(CreditNoteSummaryReportDao creditNoteSummaryReportDao) {
        this.creditNoteSummaryReportDao = creditNoteSummaryReportDao;
    }

    public void setBillAirAgentDao(BillAirAgentDao billAirAgentDao) {
        this.billAirAgentDao = billAirAgentDao;
    }

    public BillAirAgentDao getBillAirAgentDao() {
        return billAirAgentDao;
    }

    public ARNirvanaDao getArNirvanaDao() {
        return arNirvanaDao;
    }

    public void setArNirvanaDao(ARNirvanaDao arNirvanaDao) {
        this.arNirvanaDao = arNirvanaDao;
    }

    public CollectionNirvanaDao getCollectionNirvanaDao() {
        return collectionNirvanaDao;
    }

    public void setCollectionNirvanaDao(CollectionNirvanaDao collectionNirvanaDao) {
        this.collectionNirvanaDao = collectionNirvanaDao;
    }

    public APNirvanaDao getApNirvanaDao() {
        return apNirvanaDao;
    }

    public void setApNirvanaDao(APNirvanaDao apNirvanaDao) {
        this.apNirvanaDao = apNirvanaDao;
    }      
    
    public  List listSummaryAirline(){
        return airlinesummaryDao.listSummaryAirline();
    }
    
    public void setPaymentAirTicketDao(PaymentAirTicketDao paymentAirTicketDao) {
        this.paymentAirTicketDao = paymentAirTicketDao;
    }

    public PaymentAirTicketDao getPaymentAirTicketDao() {
        return paymentAirTicketDao;
    }
    
    public List getPaymentAirlineReport(String payno,String printby){
        List data  = new ArrayList();
        data.add(paymentAirTicketDao.getPaymentAirlineReport(payno,printby));
        return data;
//        return paymentAirTicketDao.getPaymentAirlineList(payno,printby);
    }
    
    public SummaryTicketAdjustCostAndIncomeDao getSummaryTicketAdjustCostAndIncomeDao() {
        return summaryTicketAdjustCostAndIncomeDao;
    }

    public List getSummaryTicketAdjustCostAndIncome(String reportType,String invoiceFromDate,String invoiceToDate,String issueFrom,String issueTo,String paymentType,String departmentt,String salebyUser,String termPayt,String printby) {
        return summaryTicketAdjustCostAndIncomeDao.getSummaryTicketAdjustCostAndIncome(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt,printby);
    }
    
    public List getSummaryTicketCostAndIncome(String reportType,String invoiceFromDate,String invoiceToDate,String issueFrom,String issueTo,String paymentType,String departmentt,String salebyUser,String termPayt,String printby){
        return summaryTicketAdjustCostAndIncomeDao.getSummaryTicketCostAndIncome(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt, printby);
    }
    
    public void setSummaryTicketAdjustCostAndIncomeDao(SummaryTicketAdjustCostAndIncomeDao summaryTicketAdjustCostAndIncomeDao) {
        this.summaryTicketAdjustCostAndIncomeDao = summaryTicketAdjustCostAndIncomeDao;
    }
    
     public List getTicketCommissionReceive(String reportType,String invoiceFromDate,String invoiceToDate,String issueFrom,String issueTo,String paymentType,String departmentt,String salebyUser,String termPayt,String printby) {
        return summaryTicketAdjustCostAndIncomeDao.getTicketCommissionReceive(reportType, invoiceFromDate, invoiceToDate, issueFrom, issueTo, paymentType, departmentt, salebyUser, termPayt,printby);
    }
     
     public List getRefundTicketDetail(String refundagent,String refundnameby,String passenger,String receivefrom,String receiveto,String paidfrom,String paidto,String typeprint,String printby,String refundby,String sectortoberef){
         return refundAirReportDao.getRefundTicketDetail(refundagent, refundnameby, passenger, receivefrom, receiveto, paidfrom, paidto, typeprint,printby,refundby,sectortoberef);
     }

    public List getTicketProfitLoss(String invoiceFromDate, String invoiceToDate, String printby) {
        return ticketFareReportDao.getTicketProfitLoss(invoiceFromDate,invoiceToDate,printby);
    }

    public List getPaymentTourHotelSummary(String from, String to, String pvtype, String status, String invSupCode, String printBy) {
        return getPaymentWendytourDao().getPaymentTourHotelSummary(from,to,pvtype,status,invSupCode,printBy);
    }

    public PaymentWendytourDao getPaymentWendytourDao() {
        return paymentWendytourDao;
    }

    public void setPaymentWendytourDao(PaymentWendytourDao paymentWendytourDao) {
        this.paymentWendytourDao = paymentWendytourDao;
    }

    public TicketSummaryCommissionDao getTicketSummaryCommissionDao() {
        return ticketSummaryCommissionDao;
    }

    public void setTicketSummaryCommissionDao(TicketSummaryCommissionDao ticketSummaryCommissionDao) {
        this.ticketSummaryCommissionDao = ticketSummaryCommissionDao;
    }
    
    public List getTicketSummaryCommission(String invoicefromdatePage,String invoicetodatePage,String issuefromdatePage,String issuetodatePage
            ,String overfromdatePage ,String overtodatePage
            ,String littlefromdatePage,String littletodatePage ,String agemtcomreceivefromdatePage,String agemtcomreceivetodatePage ,String comrefundfromdatePage 
            ,String comrefundtodatePage ,String addpayfromdatePage ,String addpaytodatePage ,String decreasepayfromdatePage,String decreasepaytodatePage
            ,String typeRoutingPage ,String routingDetailPage , String airlineCodePage ,String agentCodePage ,String agentNamePage ,String ticketnoPagePage 
            ,String departmentPage ,String salebyUserPage , String salebyNamePage , String termPayPage,String printby) {
        return ticketSummaryCommissionDao.searchTicketSummaryCommission(invoicefromdatePage, invoicetodatePage, issuefromdatePage, issuetodatePage, overfromdatePage, overtodatePage, littlefromdatePage, littletodatePage, agemtcomreceivefromdatePage, agemtcomreceivetodatePage, comrefundfromdatePage, comrefundtodatePage, addpayfromdatePage, addpaytodatePage, decreasepayfromdatePage, decreasepaytodatePage, typeRoutingPage, routingDetailPage, airlineCodePage, agentCodePage, agentNamePage, ticketnoPagePage, departmentPage, salebyUserPage, salebyNamePage, termPayPage, printby);
    }

    public PackageTourHotelDao getPackageTourHotelDao() {
        return packageTourHotelDao;
    }

    public void setPackageTourHotelDao(PackageTourHotelDao packageTourHotelDao) {
        this.packageTourHotelDao = packageTourHotelDao;
    }
    
     public List getHotelSummary(String from,String to ,String department){
        return packageTourHotelDao.getHotelSummary(from, to, department);
    }
}
