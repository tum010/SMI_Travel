package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.InvoiceService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class InvoiceController extends SMITravelController {
    private static final ModelAndView Invoice = new ModelAndView("Invoice");
    private static final ModelAndView Invoice_REFRESH = new ModelAndView(new RedirectView("Invoice.smi", true));
    private UtilityService utilityService;
    private InvoiceService invoiceService;
    private UtilityFunction utilty;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String callPageFrom = request.getParameter("type");
        String buttonVoid = request.getParameter("buttonVoid");
        String action = request.getParameter("action");
         
        if(buttonVoid != null){
            if(buttonVoid.equalsIgnoreCase("enable")){
                request.setAttribute("button", buttonVoid);     
            } else if (buttonVoid.equalsIgnoreCase("disable")){
                request.setAttribute("buttonVoid", buttonVoid);        
            }
        }
        
        if(callPageFrom != null){
           String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeInvoice", type[0]);  
        }
              
        //List Currency
        List<MCurrency> listCurrency = new ArrayList<MCurrency>();
        listCurrency = utilityService.getListMCurrency();
        if(listCurrency != null){
            request.setAttribute("listCurrency", listCurrency);
        }else{
            request.setAttribute("listCurrency", null);
        }
        //List Default Data
        MDefaultData defaultData = new MDefaultData();
        defaultData = utilityService.getMDefaultDataFromType("vat");
        if(defaultData != null){
            request.setAttribute("defaultData", defaultData);
        }else{
            request.setAttribute("defaultData", null);
        }     
        // List Agent feild Invoice To
        List<CustomerAgentInfo> listCustomerAgentInfo = new ArrayList<CustomerAgentInfo>();
        listCustomerAgentInfo = utilityService.getListCustomerAgentInfo();
        if(listCustomerAgentInfo != null){
            request.setAttribute("listCustomerAgentInfo", listCustomerAgentInfo);
        }else{
            request.setAttribute("listCustomerAgentInfo", null);
        } 
        
        //List Staff
        List<SystemUser> listStaff = new ArrayList<SystemUser>();
        listStaff = utilityService.getUserList();
        if(listStaff != null){
            request.setAttribute("listStaff", listStaff);
        }else{
            request.setAttribute("listStaff", null);
        } 
        //List Term Pay
        List<MAccterm> listTermPay = new ArrayList<MAccterm>();
        listTermPay = utilityService.getListMAccterm();
        if(listTermPay != null){
            request.setAttribute("listTermPay", listTermPay);
        }else{
            request.setAttribute("listTermPay", null);
        } 
        
        // Save Invoice And Update
        String invoiceId = request.getParameter("");
        String invoiceNo = request.getParameter("");
        String invoiceTo = request.getParameter("");
        String invoiceName = request.getParameter("");
        String invoiceAddress = request.getParameter("");
        String dueDate = request.getParameter("");
        String termPay = request.getParameter("");
        String department = request.getParameter("");
        String staffId =  request.getParameter("");
        String staffCode = request.getParameter("");
        String staffName = request.getParameter("");
        String arCode = request.getParameter("");
        String remark = request.getParameter("");
        String status = request.getParameter("");
        String invoiceType = request.getParameter("");
        String isGroup = request.getParameter("");
        String createBy = request.getParameter("");
        String createDate = request.getParameter("");
        String subDepartment = request.getParameter("");
        
        Invoice invoice = new Invoice();
        SystemUser staff = new SystemUser();
        MFinanceItemstatus mStatus = new MFinanceItemstatus();
                
        if(!invoiceId.equals("") && (invoiceId != null)){
            invoice.setId(invoiceId);
        }
        if(invoiceNo != null){
            invoice.setInvNo(invoiceNo);
        }
        if(invoiceTo != null){
            invoice.setInvTo(invoiceTo);
        }
        if(invoiceName != null){
            invoice.setInvName(invoiceName);
        }
        if(invoiceAddress != null){
            invoice.setInvAddress(invoiceAddress);
        }
        Date due = utilty.convertStringToDate(dueDate);
        if(dueDate != null){
            invoice.setDueDate(due);
        }
//        if(termPay !=  null){
//            
//        }
        if(department != null){
            invoice.setDeparement(department);
        }
        if(staffCode != null){
            staff.setUsername(staffCode);
        }
        if(staffName != null){
            staff.setName(staffName);
        }
        if(staffId != null){
            staff.setId(staffId);
            invoice.setStaff(staff);
        }
        Integer arC = Integer.parseInt(arCode);
        if(arCode != null){
            invoice.setArcode(arC);
        }
        if(remark != null){
            invoice.setRemark(remark);
        }
        if(invoiceType != null){
            invoice.setInvType(invoiceType);
        }
        Integer groupIs = Integer.parseInt(isGroup);
        if(isGroup != null){
            invoice.setIsGroup(groupIs);
        }
        if(createBy != null){
            invoice.setCreateBy(createBy);
        }
        Date createDa = utilty.convertStringToDate(createDate);
        if(createDate != null){
              invoice.setCreateDate(createDa);
        }
        
        
        
        return Invoice;
    }
    
    public List<InvoiceDetail> setInvoiceDetailList(HttpServletRequest request,Invoice invoice){
        List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
        String invoiceDetailRows = request.getParameter("counterTable");
        if (invoiceDetailRows == null) {
            return null;
        }
        int driverRows = Integer.parseInt(invoiceDetailRows);
        if (driverRows == 1) {
            return null;
        }
         for (int i = 1; i <= driverRows; i++) {
             InvoiceDetail invoiceDetail = new InvoiceDetail();
             String invoiceDetailId = request.getParameter(""+i);
             String description = request.getParameter(""+i);
             String cost = request.getParameter(""+i);
             String costLocal = request.getParameter(""+i);
             String costCurren = request.getParameter(""+i);
             String amount = request.getParameter(""+i);
             String amountLocal = request.getParameter(""+i);
             String amountCurren = request.getParameter(""+i);
             String vat = request.getParameter(""+i);
             String isVat = request.getParameter(""+i);
         }
        return listInvoiceDetail;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }
}
